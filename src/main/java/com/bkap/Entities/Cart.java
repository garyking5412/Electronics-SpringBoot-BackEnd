package com.bkap.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Cart")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="username")
	private String username;
	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName = "id")
	@JsonIgnore
	private Product product;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int id, String username, Product product) {
		super();
		this.id = id;
		this.username = username;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
