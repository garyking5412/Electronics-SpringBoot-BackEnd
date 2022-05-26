package com.bkap.Services;

import java.util.List;

import com.bkap.Filters.ProductFilter;
import org.springframework.data.domain.Page;

import com.bkap.Entities.Product;

public interface ProductService {

	List<Product> getAll();
	List<Object> getProduct();
	Page<Product> getByName(String name,int pageNumber);
//	Page<Product> getByCate(String name,int pageNumber);
	Product getById(int id);
	Product save(Product p);
	void merge(Product p);
	void remove(Product p);
//	Page<Employee>paginations(EmployeeFilter filter);
	Page<Product>getAllPaginated(int pageNumber);
	List<Product> updateOnDeleteCategory(int cateId);
	List<Product> filter(ProductFilter p);
}
