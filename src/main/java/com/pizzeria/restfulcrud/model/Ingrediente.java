package com.pizzeria.restfulcrud.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="INGREDIENTE")


@NamedNativeQueries({ @NamedNativeQuery(name = "@SQL_GET_ALL_INGREDIENTE", 
query = "select id_ingrediente, nome from Ingrediente") })

public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingrediente")
	private long id;
	
	@Column(name="nome")
	private String nome;


	
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


	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return id == other.id && Objects.equals(nome, other.nome);
	}
	
	
}

