package com.bkap.Services;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
import javax.transaction.Transactional;

import com.bkap.DTOs.RoleDto;
import com.bkap.DTOs.UserDto;
import com.bkap.Entities.*;
import com.bkap.Repositories.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bkap.Repositories.UserRepos;
import com.bkap.Repositories.UserRoleRepos;
@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepos repos;
	@Autowired
	private UserRoleRepos urepos;
	@Autowired
	private RolesRepository roleRepos;
	@Autowired
	private ModelMapper mapper;
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
	public UserDto loadUserDtoById(int id) throws UsernameNotFoundException {
		Users user = repos.getById(id);
		if (user == null)
			throw new UsernameNotFoundException(Integer.toString(id));
		return mapper.map(user,UserDto.class);
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
	public void merge(Users user){
		 repos.save(user);
	}
	public void delete(int id){
		int _id =  Integer.valueOf(id);
		Users user = repos.getById(_id);
		repos.delete(user);
	}
	public Roles saveRole(Roles role) {
		return roleRepos.save(role);
	}
	public void mergeRole(Roles roles){
		roleRepos.save(roles);
	}
	public void removeRole(int id) {
		int _id = Integer.valueOf(id);
		UserRole ur = urepos.getById(_id);
		urepos.delete(ur);
	}
	public void deleteRole(int id){
		int _id =  Integer.valueOf(id);
		List<UserRole> roles = urepos.getUserRoleByRoleId(_id);
		roles.stream().forEach(role->urepos.delete(role));
		Roles role = roleRepos.getById(_id);
		roleRepos.delete(role);
	}
	public void deleteUser(int id){
		int _id =  Integer.valueOf(id);
		List<UserRole> roles = urepos.getUserRoleByUserId(_id);
		roles.stream().forEach(role->urepos.delete(role));
		Users user = repos.getById(_id);
		repos.delete(user);
	}
	@Transactional
	public int triggerOnRegister(String username, String rolename) {
		int listResult = urepos.triggerOnRegister(username, rolename);
		return listResult;
		
	}
	public List<UserDto> getAllUsers(){
		List<Users> users = repos.findAll();
		List<UserDto>dtos=new ArrayList<>();
		users.stream().forEach(user->{
			UserDto dto = mapper.map(user,UserDto.class);
			dtos.add(dto);
		});
		return dtos;
//		return repos.findAll();
	}
	public List<RoleDto> getAllRoles(){
		List<Roles> roles = roleRepos.findAll();
		List<RoleDto>dtos=new ArrayList<>();
		roles.stream().forEach(role->{
			RoleDto dto = mapper.map(role,RoleDto.class);
			dtos.add(dto);
		});
		return dtos;
//		return repos.findAll();
	}

}
