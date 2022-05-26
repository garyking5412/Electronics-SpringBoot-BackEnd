package com.bkap.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Users")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private int phone;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties
	private List<UserRole> UserRoles;
	
	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
	private List<Review> reviews;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public List<UserRole> getUserRoles() {
		return UserRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		UserRoles = userRoles;
	}


	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Users(int id, String firstName, String lastName, String username, String password, String address,
			String email, int phone, List<UserRole> userRoles, List<Review> reviews) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
		UserRoles = userRoles;
		this.reviews = reviews;
	}

	@Transient
	public List<GrantedAuthority> getAuth(){
		List<GrantedAuthority> auths= new ArrayList<GrantedAuthority>();
		for (UserRole ur: UserRoles) {
			auths.add(new SimpleGrantedAuthority(ur.getRole().getName()));
		}
		return auths;
	}
}
