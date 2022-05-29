package com.bkap.Controllers;

import java.util.List;

import com.bkap.Filters.InvoiceFilter;
import com.bkap.Filters.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.Entities.Category;
import com.bkap.Entities.Invoice;
import com.bkap.Entities.InvoiceDetail;
import com.bkap.Entities.LoginRequest;
import com.bkap.Entities.Product;
import com.bkap.Entities.Request;
import com.bkap.Entities.UserDetail;
import com.bkap.Entities.UserInfo;
import com.bkap.Entities.Users;
import com.bkap.Services.CategoryServiceImpl;
import com.bkap.Services.InvoiceServiceImpl;
import com.bkap.Services.ProductServiceImpl;
import com.bkap.Services.UserServiceImpl;
import com.bkap.Utils.JWTUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl us;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CategoryServiceImpl cateService;
    @Autowired
    private ProductServiceImpl prodService;
    @Autowired
    private InvoiceServiceImpl invService;

    //tìm kiếm sản phẩm/cate
    @PostMapping(value = "/product/filter/{sort}/{pageNumber}")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public Page<Product> filter(@RequestBody ProductFilter filter,@PathVariable("sort") String sort,@PathVariable("pageNumber") int pageNumber) {
        return prodService.filter(filter,pageNumber,sort);
    }

    //		xem sản phẩm/cate
    @GetMapping(value = "/getAllCategories")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<Category> getAllCategory() {
        return cateService.getAll();
    }

    @GetMapping(value = "/getAllProducts")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return prodService.getAll();
    }

    @GetMapping(value = "/getAllProducts/{pageNumber}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Page<Product> getAllProduct(@PathVariable("pageNumber") int pageNumber) {
        return prodService.getAllPaginated(pageNumber);
    }

    //		xem detail sản phẩm/cate
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

    //		xem giỏ hàng/invoice
    @GetMapping(value="/invoiceFilter")
    @CrossOrigin(value="*",methods=RequestMethod.GET)
    public List<Invoice> filterInvoice(@RequestBody InvoiceFilter filter){
        return invService.filter(filter);
    }
//      mua hàng
//		đăng ký
    @PostMapping("/register")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public String register(@RequestBody Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        try {
            us.loadUserByUsername(user.getUsername());
            return "Duplicate!";
        } catch (Exception e) {
            // TODO: handle exception
            if (us.save(user) != null) {
                if (us.triggerOnRegister(user.getUsername(), "USER") != 0) ;
                return "Success!";
            } else return "Failed:" + e.getMessage();
        }
    }

    //		đăng nhập
    @PostMapping("/api/validateUser")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public List<String> validateUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        if (us.loadUserByUsername(username) == null) {
            return null;
        }
        List<String> roles = us.getRoleByUser(username);
//		for (Integer integer : roles) {
//			if(integer == 1) {
//				return 1;
//			}
//		}
//		return 2;
        return roles;
    }

    @PostMapping("/api/token")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetail detail = (UserDetail) authentication.getPrincipal();

        // Trả về jwt cho người dùng.
        String jwt = jwtUtil.generateToken(detail);
        return ResponseEntity.ok(jwt);
    }

    //		xem danh sách hóa đơn
    @PostMapping(value = "/getInvoiceByCustomer")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public List<Invoice> getInvoiceByCustomer(@RequestBody Request request) {
        String customer = request.getCustomer();
        int phone = request.getContact();
        return invService.getByCustomer(customer, phone);

    }

    //		check out/order (thêm hóa đơn mới)
    @GetMapping("/getUserInfoByName/{username}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public UserInfo getUserInfoByName(@PathVariable("username") String username) {
        return us.getUserInfoByUsername(username);
    }

    @PostMapping(value = "/order")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public Invoice order(@RequestBody Invoice invoice) {
        return invService.save(invoice);
    }

    @PostMapping(value = "/orderDetails")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public InvoiceDetail orderDetails(@RequestBody InvoiceDetail invoiceDetail) {
        return invService.save(invoiceDetail);
    }


}
