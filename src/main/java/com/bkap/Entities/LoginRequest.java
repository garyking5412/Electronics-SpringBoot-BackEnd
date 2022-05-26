package com.bkap.Entities;

import java.util.Date;

public class LoginRequest {

	private String username;
	public LoginRequest(String username, String password, Date startDate, Date endDate) {
		super();
		this.username = username;
		this.password = password;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private String password;
	private Date startDate;
	private Date endDate;
	public LoginRequest(String username, String password, Date startDate, Date endDate, String customer, int contact) {
		super();
		this.username = username;
		this.password = password;
		this.startDate = startDate;
		this.endDate = endDate;
		Customer = customer;
		this.contact = contact;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	private String Customer;
	private int contact;
	public LoginRequest() {
		super();
	}

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};
}
