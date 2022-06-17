package com.bkap.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bkap.DTOs.ProductDto;
import com.bkap.Entities.Product;
import com.bkap.Entities.page;
import com.bkap.Filters.ProductFilter;
import com.bkap.Repositories.ProductRepos;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepos repos;
	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public List<ProductDto> getAll() {
		// TODO Auto-generated method stub
		List<Product> products = repos.findAll();
		List<ProductDto> dtos = new ArrayList<ProductDto>();
		for (Product product : products) {
			ProductDto dto = mapper.map(product, ProductDto.class);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Page<Product> getByName(String name, int pageNumber) {
		// TODO Auto-generated method stub
		PageRequest p = PageRequest.of(pageNumber, 8, Sort.by("name").ascending());
		return repos.getByName(name, p);
	}

	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		Integer _id = Integer.valueOf(id);
		return repos.getById(_id);
	}

	@Override
	public Product save(Product p) {
		// TODO Auto-generated method stub
		return repos.save(p);
	}

	@Override
	public void merge(Product p) {
		// TODO Auto-generated method stub
//		Product res = repos.save(p);
		repos.save(p);
//		if(res!=null) {
//			return "Success!";
//		}
//		else {
//			return "Failed!";
//		}
	}

	@Override
	public void remove(Product p) {
		// TODO Auto-generated method stub
		repos.delete(p);

	}

	@Override
	public Page<Product> getAllPaginated(int pageNumber) {
		// TODO Auto-generated method stub
		return repos.findAll(PageRequest.of(pageNumber, 6, Sort.by("id").ascending()));
	}

	public Page<Product> getTrending(int pageNumber) {
		// TODO Auto-generated method stub
		return repos.findAll(PageRequest.of(pageNumber, 6, Sort.by("rate").descending()));
	}
	@Override
	@Transactional
	public List<Object> getProduct() {
		// TODO Auto-generated method stub
		List<Object> listResult = repos.getProduct();
		return listResult;
	}

	@Override
	@Transactional
	public List<Product> updateOnDeleteCategory(int cateId) {
		// TODO Auto-generated method stub
//		PageRequest p = PageRequest.of(0, 8, Sort.by("id").ascending());
		return repos.updateOnDeleteCategory(cateId);
	}

	@Override
	@Transactional
	public page<ProductDto> filter(ProductFilter filter, int pageNumber, String sort) {
		Pageable page = PageRequest.of(pageNumber, 6, Sort.by(sort).ascending());
		Page<Product> re = repos.findAll(new Specification<Product>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (filter.getId() != 0) {
					predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
				}
				if (filter.getName() != null && !filter.getName().isEmpty()) {
					predicates.add(criteriaBuilder.or(
							criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"),
							criteriaBuilder.like(root.get("category").get("name"), "%" + filter.getName() + "%")));
				}
				if (filter.getCateId() != 0) {
					predicates.add(criteriaBuilder.equal(root.get("category").get("id"), filter.getCateId()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, page);
		List<ProductDto>dtos =new ArrayList<ProductDto>();
		for (Product product :re.getContent()) {
			ProductDto dto = mapper.map(product, ProductDto.class);
			dtos.add(dto);
		}
		page<ProductDto> pageDto = new page<ProductDto>(re.getNumber(), re.getTotalElements(),
				re.getSize(), dtos);
		return pageDto;
	}

	@Override
	public List<ProductDto> getTopSell() {
		// TODO Auto-generated method stub
		List<Product> products = repos.getTopSelling();
		List<ProductDto> dtos = new ArrayList<ProductDto>();
		for (Product product : products) {
			ProductDto dto = mapper.map(product, ProductDto.class);
			dtos.add(dto);
		}
		return dtos;
	}

}
