package com.bkap.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.Users;
@Repository
public interface UserRepos extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);
	@Query(value="update Users set roleId = 2 where roleId = ?1",nativeQuery = true)
	void triggerOnRoleDelete(int id);
	@Query(value="Select * from Users where roleId = ?1",nativeQuery = true)
	List<Users> loadUserByRole(int id);
}
