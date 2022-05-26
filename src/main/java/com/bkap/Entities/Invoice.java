package com.bkap.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Invoice")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "customer")
	private String customer;
	@Column(name = "date")
	private Date date;
	@Column(name = "shippingMethod")
	private String ship;
	@Column(name = "paymentMethod")
	private String paymentMethod;
	@Column(name = "customerContact")
	private int customerContact;
	@Column(name = "shippingAddress")
	private String shippingAddress;
	@OneToMany(mappedBy = "invoice",fetch = FetchType.EAGER)
	private List<InvoiceDetail> invoiceDetails;
	@Column(name="status")
	private String status;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int id, String customer, Date date, String ship, String paymentMethod, int customerContact,
			String shippingAddress, List<InvoiceDetail> invoiceDetails, String status) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.ship = ship;
		this.paymentMethod = paymentMethod;
		this.customerContact = customerContact;
		this.shippingAddress = shippingAddress;
		this.invoiceDetails = invoiceDetails;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getShip() {
		return ship;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(int customerContact) {
		this.customerContact = customerContact;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}
	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
