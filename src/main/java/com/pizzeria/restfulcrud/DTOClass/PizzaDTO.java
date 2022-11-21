package com.pizzeria.restfulcrud.DTOClass;

import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.pizzeria.restfulcrud.model.Impasto;
import com.pizzeria.restfulcrud.model.Ingrediente;
import com.pizzeria.restfulcrud.model.Utente;

@XmlRootElement(name = "pizza")
@XmlAccessorType(XmlAccessType.FIELD)
public class PizzaDTO {

	private long id;
	private String nome;
	private UtenteDTO utente;
	private ImpastoDTO impasto;
	private Set<Ingrediente> ingrediente;
	
	public PizzaDTO() {}
	
	public PizzaDTO(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public PizzaDTO(long id, String nome, UtenteDTO utente, ImpastoDTO impasto, Set<Ingrediente> ingrediente) {
		super();
		this.id = id;
		this.nome = nome;
		this.utente = utente;
		this.impasto = impasto;
		this.ingrediente = ingrediente;
	}


	public Set<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Set<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}

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

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public ImpastoDTO getImpasto() {
		return impasto;
	}

	public void setImpasto(ImpastoDTO impasto) {
		this.impasto = impasto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, impasto, ingrediente, nome, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaDTO other = (PizzaDTO) obj;
		return id == other.id && Objects.equals(impasto, other.impasto)
				&& Objects.equals(ingrediente, other.ingrediente) && Objects.equals(nome, other.nome)
				&& Objects.equals(utente, other.utente);
	}
	
	
	
}
