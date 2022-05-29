package com.bkap.Filters;

import java.io.Serializable;
import java.util.Date;

public class InvoiceFilter implements Serializable {
    public static final long serialVersionUID = 1L;
    private int id;
    private String customer;
    private Date startDate;
    private Date endDate;
    private int customerContact;

    public InvoiceFilter(int id, String customer, Date startDate, Date endDate, int customerContact) {
        this.id = id;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerContact = customerContact;
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

    public int getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(int customerContact) {
        this.customerContact = customerContact;
    }
}
