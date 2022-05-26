package com.bkap.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkap.Entities.Product;
@Repository
public interface ProductRepos extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	@Query(value="Select * from Product where name like '%?1%'",nativeQuery=true)
	public Page<Product> getByName(String name,PageRequest p);
	@Query(value="Select Product.id,Product.name,Product.des,Product.rate,Product.status,Product.color,Product.spec,Product.stock,Product.price,Product.image,category.id as cateId, Category.name as category from Product left join Category on Product.cateId = Category.id",nativeQuery=true)
	public List<Object> getProduct();
	@Query(value="exec updateOnDeleteCategory ?1",nativeQuery=true)
	public List<Product> updateOnDeleteCategory(int cateId);
}
