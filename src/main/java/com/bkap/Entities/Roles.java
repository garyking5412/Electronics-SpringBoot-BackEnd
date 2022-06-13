package com.bkap.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Roles")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<Users> Users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Users> getUsers() {
		return Users;
	}

	public void setUsers(List<Users> users) {
		Users = users;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(int id, String name, List<com.bkap.Entities.Users> users) {
		super();
		this.id = id;
		this.name = name;
		Users = users;
	}

}
