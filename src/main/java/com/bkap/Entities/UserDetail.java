package com.bkap.Entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
//@AllArgsConstructor 	
public class UserDetail implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Users user;
	
	public UserDetail() {
	}
	public UserDetail(Users user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getAuth();
//		return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
}

