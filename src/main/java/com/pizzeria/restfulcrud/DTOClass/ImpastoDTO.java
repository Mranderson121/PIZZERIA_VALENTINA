package com.pizzeria.restfulcrud.DTOClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "impasto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImpastoDTO {

	private long id;
	private String nome;

	public ImpastoDTO() {
	}

	public ImpastoDTO(long id, String nome) {
		this.id = id;
		this.nome = nome;
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

}
