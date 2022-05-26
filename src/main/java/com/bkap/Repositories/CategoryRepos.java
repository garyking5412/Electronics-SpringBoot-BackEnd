package com.bkap.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.Category;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Integer> {

	@Query(value="Select * from Category where name like '%?1%'",nativeQuery=true)
	public Page<Category> getByName(String name,PageRequest p);
}
