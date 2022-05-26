package com.bkap.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.bkap.Entities.Invoice;
import com.bkap.Entities.InvoiceDetail;

public interface InvoiceService {

	List<Invoice> getAllInvoice();
	List<Invoice> getInvoiceByDate(Date startDate,Date endDate);
	Invoice getById(int id);
	Invoice save(Invoice p);
	void merge(Invoice p);
	void remove(Invoice p);
	List<Invoice> getByStatus(String status);
	List<Invoice> getByCustomer(String customer,int contact);
	List<InvoiceDetail>getDetailsByProductId(int productId);
	Page<Invoice>pagination(int pageNumber);
	List<InvoiceDetail> getInvoiceDetails(int invoiceId);
	List<Object> getDetailsByInvoiceId(int invoiceId);
	InvoiceDetail getInvoiceDetailById(int id);
	InvoiceDetail save(InvoiceDetail p);
	void merge(InvoiceDetail p);
	void remove(InvoiceDetail p);
	void removeDetailByInvoice (int invoiceId);
//	Page<Employee>paginations(EmployeeFilter filter);
}
