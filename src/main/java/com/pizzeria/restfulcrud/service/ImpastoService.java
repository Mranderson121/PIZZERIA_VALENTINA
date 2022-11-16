package com.pizzeria.restfulcrud.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.Impasto;


@Path("/impasti")
public class ImpastoService {

	// URI:
		// /contextPath/servletPath/employees
		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<Impasto> getImpasti_JSON() {
			List<Impasto> listOfCountries = ClassDao.GetAllImpasti();
			return listOfCountries;
		}
}
