package com.bkap.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.UserRole;
@Repository
public interface UserRoleRepos extends JpaRepository<UserRole, Integer> {
	@Query(value="select name from Roles where id in (select UserRole.roleId from UserRole where UserRole.userId in (select id from Users where username = ?1))",nativeQuery = true)
	List<String>getRoleByUser(String username);
	@Query(value="exec addUserRole ?1,?2",nativeQuery = true)
	Integer triggerOnRegister(String username,String rolename);
}
