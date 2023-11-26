package com.bkap.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name="Product")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@Data
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "des")
	private String des;
	@Column(name = "rate")
	private int rate;
	@Column(name = "status")
	private int status;
	@Column(name = "color")
	private String color;
	@Column(name = "spec")
	private String spec;
	@Column(name = "stock")
	private int stock;
	@Column(name = "price")
	private float price;
	@Column(name = "image")
	private String image;
	@Getter
	@OneToMany(mappedBy = "product",fetch=FetchType.LAZY)
	@Transient
	@JsonIgnore
	private List<InvoiceDetail> InvoiceDetails;
	@OneToMany(mappedBy = "product",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Cart> carts;
	@OneToMany(mappedBy = "product",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Review> reviews ;
	@ManyToOne
	@JoinColumn(name="cateId",referencedColumnName = "id")
	private Category category;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
}
