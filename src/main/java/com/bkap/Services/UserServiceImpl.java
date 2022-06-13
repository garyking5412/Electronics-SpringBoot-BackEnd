package com.bkap.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bkap.DTOs.RoleDto;
import com.bkap.DTOs.UserDto;
import com.bkap.Entities.Roles;
import com.bkap.Entities.UserDetail;
import com.bkap.Entities.UserInfo;
import com.bkap.Entities.Users;
import com.bkap.Repositories.RolesRepository;
import com.bkap.Repositories.UserRepos;
@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepos repos;
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
	public String loadRoleByUser(String username) throws UsernameNotFoundException {
		Users user = repos.findByUsername(username);
		return user.getRole().getName();
	}
	public RoleDto loadRoleDtoById(int id) {
		Roles role = roleRepos.getById(id);
		return mapper.map(role,RoleDto.class);
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
	public Users save(Users user) {
		return repos.save(user);
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
	public void deleteRole(int id){
		int _id =  Integer.valueOf(id);
		if(repos.loadUserByRole(_id).size()>0) {
			repos.triggerOnRoleDelete(_id);
		}
		Roles role = roleRepos.getById(_id);
		roleRepos.delete(role);
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

//	@Transactional
//	public int triggerOnRegister(String username, String rolename) {
//		int listResult = urepos.triggerOnRegister(username, rolename);
//		return listResult;
//		
//	}

}
