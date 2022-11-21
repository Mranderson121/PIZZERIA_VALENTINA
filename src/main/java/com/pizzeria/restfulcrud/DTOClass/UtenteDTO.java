package com.pizzeria.restfulcrud.DTOClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "utente")
@XmlAccessorType(XmlAccessType.FIELD)
public class UtenteDTO {


	private long id;
	private String username;
	private String password;

	
	public UtenteDTO() {	}

	public UtenteDTO(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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
