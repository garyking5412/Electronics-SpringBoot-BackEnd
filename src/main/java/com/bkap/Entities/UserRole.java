package com.bkap.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="UserRole")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private Users user;
	@ManyToOne
	@JoinColumn(name="roleId",referencedColumnName = "id")
	@JsonIgnore
	private Roles role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRole(int id, Users user, Roles role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	
}