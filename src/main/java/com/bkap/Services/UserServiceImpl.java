package com.bkap.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bkap.Entities.UserDetail;
import com.bkap.Entities.UserInfo;
import com.bkap.Entities.UserRole;
import com.bkap.Entities.Users;
import com.bkap.Repositories.UserRepos;
import com.bkap.Repositories.UserRoleRepos;
@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepos repos;
	@Autowired
	private UserRoleRepos urepos;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repos.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new UserDetail(user);
	}
	public UserDetails loadUserById(int id) throws UsernameNotFoundException {
		Users user = repos.getById(id);
		if (user == null)
			throw new UsernameNotFoundException(Integer.toString(id));
		return new UserDetail(user);
	}
	public UserInfo getUserInfoByUsername(String username) {
		Users user = repos.findByUsername(username);
		UserInfo info = new UserInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(),user.getPhone());
		return info;
	}
	public List<String>getRoleByUser(String username){
		return urepos.getRoleByUser(username);
	}
	public Users save(Users user) {
		return repos.save(user);
	}
	public UserRole saveUserRole(UserRole ur) {
		return urepos.save(ur);
	}
	@Transactional
	public int triggerOnRegister(String username, String rolename) {
		int listResult = urepos.triggerOnRegister(username, rolename);
		return listResult;
		
	}
}
