package com.pizzeria.restfulcrud.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UTENTE")
public class Utente {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utente")
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	@OneToMany(mappedBy="utente", fetch = FetchType.EAGER)
	private Set<Pizza> pizza;

	public Utente() {}
	
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;	
	}
	
	public Set<Pizza> getPizza() {
		return pizza;
	}

	public void setPizza(Set<Pizza> pizza) {
		this.pizza = pizza;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	
	
}
