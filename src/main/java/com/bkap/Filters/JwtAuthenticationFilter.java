package com.bkap.Filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bkap.Services.UserServiceImpl;
import com.bkap.Utils.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserServiceImpl userService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// Lấy jwt từ request
            String jwt = getJwtFromRequest(request);
            
            if (StringUtils.hasText(jwt) && jwtUtil.validateToken(jwt)) {
            	
            	// lấy id user
            	Integer userId = jwtUtil.getUserByIdfromJWT(jwt);
            	
            	UserDetails user = userService.loadUserById(userId);
            	
            	if(user != null) {
            		// Nếu người dùng hợp lệ, set thông tin cho Security Context
            		UsernamePasswordAuthenticationToken authentication = 
            				new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            		SecurityContextHolder.getContext().setAuthentication(authentication);
            	}
            }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
 		filterChain.doFilter(request, response);
		
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}