package com.bkap.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bkap.DTOs.ProductDto;
import com.bkap.Entities.Product;
import com.bkap.Entities.page;
import com.bkap.Filters.ProductFilter;

public interface ProductService {

	List<ProductDto> getAll();

	List<Object> getProduct();

	Page<Product> getByName(String name, int pageNumber);

//	Page<Product> getByCate(String name,int pageNumber);
	Product getById(int id);

	Product save(Product p);

	void merge(Product p);

	void remove(Product p);

//	Page<Employee>paginations(EmployeeFilter filter);
	Page<Product> getAllPaginated(int pageNumber);

	List<Product> updateOnDeleteCategory(int cateId);

	page<ProductDto> filter(ProductFilter p, int pageNumber, String sort);

	List<ProductDto> getTopSell();
}
