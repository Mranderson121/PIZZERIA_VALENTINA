package com.pizzeria.restfulcrud.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="IMPASTO")
@XmlRootElement(name = "impasto")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedNativeQueries({ @NamedNativeQuery(name = "@SQL_GET_ALL_IMPASTI", 
query = "select id_impasto, nome from Impasto") })


public class Impasto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_impasto")
	private long id;
	
	@Column(name="nome")
	private String nome;
/*
	@OneToMany(mappedBy="impasto", fetch = FetchType.EAGER)
	private Set<Pizza> pizza;

	
	public Set<Pizza> getPizza() {
		return pizza;
	}

	public void setPizza(Set<Pizza> pizza) {
		this.pizza = pizza;
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
