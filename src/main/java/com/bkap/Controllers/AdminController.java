package com.bkap.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.Entities.Category;
import com.bkap.Entities.Invoice;
import com.bkap.Entities.InvoiceDetail;
import com.bkap.Entities.Product;
import com.bkap.Services.CategoryServiceImpl;
import com.bkap.Services.InvoiceServiceImpl;
import com.bkap.Services.ProductServiceImpl;

@RestController
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private CategoryServiceImpl cateService;
	@Autowired
	private ProductServiceImpl prodService;
	@Autowired
	private InvoiceServiceImpl invService;
//	xem sản phẩm/cate
	@GetMapping(value="/getAllCategories")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Category> getAllCategory(){
		return cateService.getAll();
	}
	@GetMapping(value="/getAllProducts")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Product> getAllProduct(){
		return prodService.getAll();
	}
	@GetMapping(value="/getAllProducts/{pageNumber}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public Page<Product> getAllProduct(@PathVariable("pageNumber") int pageNumber){
		return prodService.getAllPaginated(pageNumber);
	}
	@GetMapping(value="/getCategory/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public Category getCateById (@PathVariable("id") int cateId){
		return cateService.getById(cateId);
	}
	@GetMapping(value="/getProduct/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public Product getProdById (@PathVariable("id") int prodId){
		return prodService.getById(prodId);
	}
//	crud sản phẩm/cate
	@PostMapping("/addCategory")
	@CrossOrigin(value="*",methods=RequestMethod.POST)
	public Category addCategory (@RequestBody Category category) {
		return cateService.save(category);
	}
	@PostMapping("/addProduct")
	@CrossOrigin(value="*",methods=RequestMethod.POST)
	public Product addProduct (@RequestBody Product product) {
		return prodService.save(product);
	}
	@PutMapping("/updateCategory/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void updateCategory (@PathVariable("id") int id, @RequestBody Category category) {
		category.setId(id);
		cateService.merge(category);
	}
	@PutMapping("/updateProduct/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void updateProduct (@PathVariable("id") int id, @RequestBody Product product) {
		product.setId(id);
		Category cates =  cateService.getById(product.getCategory().getId());
		if(cates == null) {
			System.out.println("Category not available!");
		}
		prodService.merge(product);
	}
	@DeleteMapping("/deleteProduct/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	public void deleteProduct (@PathVariable("id") int id) {
		Product p = prodService.getById(id);
		prodService.remove(p);
	}
	@DeleteMapping("/deleteCategory/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	public void deleteCategory (@PathVariable("id") int id) {
		Category cate = cateService.getById(id);
		prodService.updateOnDeleteCategory(id);
		cateService.remove(cate);
	}
//	crud cate
//	crud hóa đơn
	@PostMapping(value="/addInvoice")
	@CrossOrigin(value="*",methods=RequestMethod.POST)
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		return invService.save(invoice);
	}
	@GetMapping(value="/getInvoiceByStatus/{status}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Invoice> getInvoiceByStatus(@PathVariable("status") String status) {
		return invService.getByStatus(status);
	}
	@GetMapping(value="/getInvoiceById/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public Invoice getInvoiceById(@PathVariable("id") int id) {
		return invService.getById(id);
	}
	@GetMapping(value="/getAllInvoice")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<Invoice> getAllInvoice() {
		return invService.getAllInvoice();
	}
	@PutMapping(value="/updateInvoice/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void updateInvoice(@RequestBody Invoice invoice,@PathVariable("id") int id) {
		invoice.setId(id);
		invService.merge(invoice);
	}
	@DeleteMapping(value="/deleteInvoice/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	public void deleteInvoice(@PathVariable("id") int id) {
		invService.removeDetailByInvoice(id);
	}
//	crud detail hóa đơn
	@PostMapping(value="/addInvoiceDetail")
	@CrossOrigin(value="*",methods=RequestMethod.POST)
	public InvoiceDetail addInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
		return invService.save(invoiceDetail);
	}
	@GetMapping(value="/getInvoiceDetailById/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public InvoiceDetail getInvoiceDetailById(@PathVariable("id") int detailId) {
		return invService.getInvoiceDetailById(detailId);
	}
	@GetMapping(value="/getInvoiceDetailByProductId/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.GET)
	public List<InvoiceDetail> getInvoiceDetailByProductId(@PathVariable("id") int producId) {
		return invService.getDetailsByProductId(producId);
	}
	@PutMapping(value="/updateInvoiceDetail/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.PUT)
	public void updateInvoiceDetail(@PathVariable("id") int detailId,@RequestBody InvoiceDetail detail) {
		detail.setId(detailId);
		invService.merge(detail);
	}
	@DeleteMapping(value="/deleteDetail/{id}")
	@CrossOrigin(value="*",methods=RequestMethod.DELETE)
	public void deleteInvoiceDetail(@PathVariable("id") int id) {
		InvoiceDetail detail = invService.getInvoiceDetailById(id);
		invService.remove(detail);
	}
//	crud giỏ hàng
//	crud users
}
