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

@Entity
@Table(name="Product")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
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
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
//	public List<Integer> getInvoiceDetails() {
//		List<Integer> invoiceDetails = new ArrayList<Integer>();
//		for (InvoiceDetail detail : InvoiceDetails) {
//			invoiceDetails.add(detail.getId());
//		}
//		return invoiceDetails;
//	}
	public List<InvoiceDetail> getInvoiceDetails() {
		return InvoiceDetails;
	}
	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		InvoiceDetails = invoiceDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category=category;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public Product(int id, String name, String des, int rate, int status, String color, String spec, int stock,
			float price, String image, List<InvoiceDetail> invoiceDetails, List<Cart> carts, List<Review> reviews,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
		this.rate = rate;
		this.status = status;
		this.color = color;
		this.spec = spec;
		this.stock = stock;
		this.price = price;
		this.image = image;
		InvoiceDetails = invoiceDetails;
		this.carts = carts;
		this.reviews = reviews;
		this.category = category;
	}

	
}
