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
@Table(name="InvoiceDetails")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class InvoiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="invoiceId",referencedColumnName = "id")
	private Invoice invoice;
	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName = "id")
	private Product product;
	@Column(name="quantity")
	private int quantity;
	public InvoiceDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoiceDetail(int id, Invoice invoice, Product product, int quantity) {
		super();
		this.id = id;
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvoice() {
		return invoice.getId();
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
