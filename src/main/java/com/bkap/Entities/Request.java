package com.bkap.Entities;

public class Request {

	private String customer;
	private int contact;
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public Request(String customer, int contact) {
		super();
		this.customer = customer;
		this.contact = contact;
	}
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
