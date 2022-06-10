package com.bkap.Controllers;

import java.util.List;

import com.bkap.DTOs.RoleDto;
import com.bkap.DTOs.UserDto;
import com.bkap.DTOs.UserRoleDto;
import com.bkap.Entities.*;
import com.bkap.Services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.bkap.DTOs.ProductDto;
import com.bkap.Services.CategoryServiceImpl;
import com.bkap.Services.InvoiceServiceImpl;
import com.bkap.Services.ProductServiceImpl;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private CategoryServiceImpl cateService;
    @Autowired
    private ProductServiceImpl prodService;
    @Autowired
    private InvoiceServiceImpl invService;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModelMapper mapper;

    //	xem sản phẩm/cate
    @GetMapping(value = "/getAllCategories")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<Category> getAllCategory() {
        return cateService.getAll();
    }

    @GetMapping(value = "/getAllProducts")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<ProductDto> getAllProduct() {
        return prodService.getAll();
    }

    @GetMapping(value = "/getAllProducts/{pageNumber}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Page<Product> getAllProduct(@PathVariable("pageNumber") int pageNumber) {
        return prodService.getAllPaginated(pageNumber);
    }

    @GetMapping(value = "/getCategory/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Category getCateById(@PathVariable("id") int cateId) {
        return cateService.getById(cateId);
    }

    @GetMapping(value = "/getProduct/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Product getProdById(@PathVariable("id") int prodId) {
        return prodService.getById(prodId);
    }

    //	crud sản phẩm/cate
    @PostMapping("/addCategory")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category) {
        return cateService.save(category);
    }

    @PostMapping("/addProduct")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        return prodService.save(product);
    }

    @PutMapping("/updateCategory/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        category.setId(id);
        cateService.merge(category);
    }

    @PutMapping("/updateProduct/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        product.setId(id);
        Category cates = cateService.getById(product.getCategory().getId());
        if (cates == null) {
            System.out.println("Category not available!");
        }
        prodService.merge(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") int id) {
        Product p = prodService.getById(id);
        prodService.remove(p);
    }

    @DeleteMapping("/deleteCategory/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") int id) {
        Category cate = cateService.getById(id);
        prodService.updateOnDeleteCategory(id);
        cateService.remove(cate);
    }

    //	crud cate
//	crud hóa đơn
    @PostMapping(value = "/addInvoice")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return invService.save(invoice);
    }

    @GetMapping(value = "/getInvoiceByStatus/{status}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<Invoice> getInvoiceByStatus(@PathVariable("status") String status) {
        return invService.getByStatus(status);
    }
    @PostMapping(value = "/getInvoiceByCustomer")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public List<Invoice> getInvoiceByStatus(@RequestBody Users user) {
        String customer =  user.getLastName()+' '+user.getFirstName();
        int contact = user.getPhone();
        return invService.getByCustomer(customer,contact);
    }
    @GetMapping(value = "/getInvoiceById/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable("id") int id) {
        return invService.getById(id);
    }

    @GetMapping(value = "/getAllInvoice")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<Invoice> getAllInvoice() {
        return invService.getAllInvoice();
    }

    @PutMapping(value = "/updateInvoice/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable("id") int id) {
        invoice.setId(id);
        invService.merge(invoice);
    }

    @DeleteMapping(value = "/deleteInvoice/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable("id") int id) {
        invService.removeDetailByInvoice(id);
    }

    //	crud detail hóa đơn
    @PostMapping(value = "/addInvoiceDetail")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public InvoiceDetail addInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
        return invService.save(invoiceDetail);
    }

    @GetMapping(value = "/getInvoiceDetailById/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public InvoiceDetail getInvoiceDetailById(@PathVariable("id") int detailId) {
        return invService.getInvoiceDetailById(detailId);
    }

    @GetMapping(value = "/getInvoiceDetailByProductId/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<InvoiceDetail> getInvoiceDetailByProductId(@PathVariable("id") int producId) {
        return invService.getDetailsByProductId(producId);
    }

    @PutMapping(value = "/updateInvoiceDetail/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateInvoiceDetail(@PathVariable("id") int detailId, @RequestBody InvoiceDetail detail) {
        detail.setId(detailId);
        invService.merge(detail);
    }

    @DeleteMapping(value = "/deleteDetail/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.DELETE)
    public void deleteInvoiceDetail(@PathVariable("id") int id) {
        InvoiceDetail detail = invService.getInvoiceDetailById(id);
        invService.remove(detail);
    }

    //	crud giỏ hàng
//	crud users
    @GetMapping(value = "/getAllUsers")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping(value = "/getAllRoles")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<RoleDto> getAllRoles() {
        return userService.getAllRoles();
    }
    @GetMapping(value = "/getAllUsers/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public UserDto getUserDetails(@PathVariable("id")int id) {
        return userService.loadUserDtoById(id);
    }
    @PostMapping(value = "/saveUser")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public UserDto saveUser(@RequestBody Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        try {
            userService.loadUserByUsername(user.getUsername());
            UserDto dto =  new UserDto();
            dto.setUsername("Duplicate");
            return dto;
        } catch (Exception e) {
            // TODO: handle exception
            if (userService.save(user) != null) {
                if (userService.triggerOnRegister(user.getUsername(), "USER") != 0) ;
                UserDto userDto = mapper.map(userService.save(user), UserDto.class);
                return userDto;
            } else return null;
        }

    }
    @PostMapping(value = "/saveUserRole")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public UserRoleDto saveRole(@RequestBody UserRole ur) {
        UserRoleDto urDto = mapper.map(userService.saveUserRole(ur),UserRoleDto.class);
        return urDto;
    }
    @PostMapping(value = "/saveRole")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public RoleDto saveRole(@RequestBody Roles role) {
        RoleDto roleDto = mapper.map(userService.saveRole(role),RoleDto.class);
        return roleDto;
    }
    @DeleteMapping(value = "/removeRole")
    @CrossOrigin(value = "*", methods = RequestMethod.DELETE)
    public void removeRole(@RequestBody UserRoleDto dto) {
    	userService.removeRole(dto.getId());
    }
    @PutMapping(value = "/updateUser/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") int userId, @RequestBody Users user) {
        user.setId(userId);
        userService.merge(user);
    }
    @PutMapping(value = "/updateRole/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateRole(@PathVariable("id") int roleId, @RequestBody Roles role) {
        role.setId(roleId);
        userService.mergeRole(role);
    }
    @PutMapping(value = "/updateUserRole/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    public void updateUserRole(@PathVariable("id") int id, @RequestBody UserRole ur) {
        ur.setId(id);
        userService.saveUserRole(ur);
    }
    @DeleteMapping(value="/deleteUser/{id}")
    @CrossOrigin(value="*",methods = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
    @DeleteMapping(value="/deleteRole/{id}")
    @CrossOrigin(value="*",methods = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") int id){
        userService.deleteRole(id);
    }
}
