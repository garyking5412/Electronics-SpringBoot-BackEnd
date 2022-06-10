package com.bkap.DTOs;

public class ProductDto {

	private int id;
	private String name;
	private String des;
	private int rate;
	private int status;
	private String color;
	private String spec;
	private int stock;
	private float price;
	private String image;
	private CategoryDto categoryDto;
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
	public CategoryDto getCategoryDto() {
		return categoryDto;
	}
	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(int id, String name, String des, int rate, int status, String color, String spec, int stock,
			float price, String image, CategoryDto categoryDto) {
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
		this.categoryDto = categoryDto;
	}
	
}
