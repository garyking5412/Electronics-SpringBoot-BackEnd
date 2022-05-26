package com.bkap.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bkap.Entities.Category;
import com.bkap.Repositories.CategoryRepos;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepos repos;
	@Override
	@Transactional
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return repos.findAll();
	}

	@Override
	public Page<Category> getByName(String name, int pageNumber) {
		// TODO Auto-generated method stub
		PageRequest p = PageRequest.of(pageNumber, 8, Sort.by("name").ascending());
		return repos.getByName(name, p);
	}

	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
		Integer _id = Integer.valueOf(id);
		return repos.getById(_id);
	}

	@Override
	public Category save(Category p) {
		// TODO Auto-generated method stub
		return repos.save(p);
	}

	@Override
	public void merge(Category p) {
		// TODO Auto-generated method stub
//		if(repos.save(p)!=null)
//			return "Success!";
//		else
//			return "Failed!";
		repos.save(p);
	}

	@Override
	public void remove(Category p) {
		// TODO Auto-generated method stub
		repos.delete(p);
	}

	@Override
	public Page<Category> pagination(int pageNumber) {
		// TODO Auto-generated method stub
		return repos.findAll(PageRequest.of(pageNumber, 4, Sort.by("id").ascending()));

	}

}
