package com.pizzeria.restfulcrud.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pizzeria.restfulcrud.DTOClass.ImpastoDTO;
import com.pizzeria.restfulcrud.DTOClass.PizzaDTO;
import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.Ingrediente;

@Path("/pizze")
public class PizzaService {
	// URI:
			// /contextPath/servletPath/pizze
			@GET
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public List<PizzaDTO> getPizze_JSON() {
				List<PizzaDTO> listOfCountries = ClassDao.GetAllPizzeDTO();
				return listOfCountries;
			}
			

			// URI:
			// /contextPath/servletPath/impasti/{id}
			@GET
			@Path("/{id}")
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public PizzaDTO getImpasto(@PathParam("id") String id) {
				return ClassDao.findPizzaByID_DTO(id);
			}
			

}
