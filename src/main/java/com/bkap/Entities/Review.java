package com.bkap.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Review")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="content")
	private String content;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private Users user;
	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName = "id")
	private Product product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Review(int id, String content, Users user, Product product) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.product = product;
	}
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
