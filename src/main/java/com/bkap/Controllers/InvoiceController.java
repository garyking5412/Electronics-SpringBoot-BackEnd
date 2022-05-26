package com.bkap.Controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.Entities.Invoice;
import com.bkap.Entities.InvoiceDetail;
import com.bkap.Entities.LoginRequest;
import com.bkap.Services.InvoiceServiceImpl;

@RestController
@RequestMapping(value="/invoiceController")
public class InvoiceController {

	@Autowired
	private InvoiceServiceImpl service;
	@GetMapping(value="/getAllInvoice")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Invoice> getAll(){
		return service.getAllInvoice();
	}
	@GetMapping(value="/getInvoiceByDate")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Invoice> getInvoiceByDate(@RequestBody LoginRequest request) throws ParseException{
		Date startDate  = request.getStartDate(); 
		Date endDate =  request.getEndDate(); 
		return service.getInvoiceByDate(startDate, endDate);
	}
	@GetMapping(value="default/getInvoiceById/{id}")
	@CrossOrigin(value="*",methods = RequestMethod.GET)
	public Invoice getInvoiceById(@PathVariable("id") int id) {
		return service.getById(id);
	}
	@PostMapping(value="default/invoice")
	@CrossOrigin(value="*",methods = RequestMethod.POST)
	public Invoice saveInvoice(@RequestBody Invoice invoice) {
		return service.save(invoice);
	}
	@PutMapping(value="/invoice/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void putInvoice(@PathVariable("id") int id,@RequestBody Invoice invoice) {
		invoice.setId(id);
//		return service.merge(p);
		service.merge(invoice);
	}
	@DeleteMapping(value="/invoice/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public void deleteInvoice(@PathVariable("id") int id) {
		Invoice invoice = service.getById(id);
		service.remove(invoice);
	}
	@GetMapping(value="default/getInvoiceDetails/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<InvoiceDetail> getInvoiceDetails(@PathVariable("id") int invoiceId){
		return service.getInvoiceDetails(invoiceId);
	}
	@GetMapping(value="default/getDetailsByInvoiceId/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Object> getDetailsByInvoiceId(@PathVariable("id") int invoiceId){
		return service.getDetailsByInvoiceId(invoiceId);
	}
	@GetMapping(value="/getInvoiceDetailsById/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public InvoiceDetail getInvoiceDetailsById(@PathVariable("id") int id){
		return service.getInvoiceDetailById(id);
	}
	@PostMapping(value="default/invoiceDetail")
	@CrossOrigin(value="*",methods = RequestMethod.POST)
	public InvoiceDetail saveInvoiceDetails(@RequestBody InvoiceDetail invoiceDetail) {
		return service.save(invoiceDetail);
	}
	@PutMapping(value="/invoiceDetail/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void putInvoiceDetail(@PathVariable("id") int id,@RequestBody InvoiceDetail invoiceDetail) {
		invoiceDetail.setId(id);
//		return service.merge(p);
		service.merge(invoiceDetail);
	}
	@DeleteMapping(value="/invoiceDetail/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public void deleteInvoiceDetail(@PathVariable("id") int id) {
		InvoiceDetail invoiceDetail = service.getInvoiceDetailById(id);
		service.remove(invoiceDetail);
	}
	@DeleteMapping(value="/fk/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	public void removeDetailByInvoice(@PathVariable("id") int invoiceId) {
		service.removeDetailByInvoice(invoiceId);
	}
	@GetMapping(value="default/getAllInvoiceByStatus/{status}")
	@CrossOrigin(value="*",methods = RequestMethod.GET)
	public List<Invoice> getInvoiceByStatus(@PathVariable("status") String status) {
		return service.getByStatus(status);
	}
	@GetMapping(value="default/getAllInvoiceByCustomer")
	@CrossOrigin(value="*",methods = RequestMethod.GET)
	public List<Invoice> getInvoiceByStatus(@RequestBody  LoginRequest request) {
		String customer = request.getCustomer();
		int contact =  request.getContact();
		return service.getByCustomer(customer, contact);
	}
}
