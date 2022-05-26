package com.bkap.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bkap.Entities.Category;

public interface CategoryService {

	List<Category> getAll();
	Page<Category> getByName(String name,int pageNumber);
	Category getById(int id);
	Category save(Category p);
	void merge(Category p);
	void remove(Category p);
//	Page<Employee>paginations(EmployeeFilter filter);
	Page<Category>pagination(int pageNumber);
}
