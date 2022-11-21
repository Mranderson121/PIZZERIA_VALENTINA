package com.pizzeria.restfulcrud.service;


import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.Ingrediente;


@Path("/ingredienti")
public class IngredienteService {

	// URI:
		// /contextPath/servletPath/impasti
		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<Ingrediente> getIngredienti_JSON() {
			List<Ingrediente> listOfCountries = ClassDao.GetAllIngredientiDTO();
			return listOfCountries;
		}
		
		// URI:
		// /contextPath/servletPath/impasti/{id}
		@GET
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Ingrediente getImpasto(@PathParam("id") String id_impasto) {
			return ClassDao.findIngredienteByID_DTO(id_impasto);
		}

		

		// URI:
		// /contextPath/servletPath/employees
		@POST
		@Path("/{nome}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Ingrediente addEmployee(@PathParam("nome") String nome) {
			return ClassDao.addIngrediente_DTO(nome);
		}

		// URI:
		// /contextPath/servletPath/employees
		@PUT
		@Path("/{id}/{nome}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Ingrediente updateImpastoName(@PathParam("id") String id,
											@PathParam("nome") String nome){
			return ClassDao.updateIngrediente_DTO(id,nome);
		}

		@DELETE
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void deleteEmployee(@PathParam("id") String id) {
			ClassDao.deleteIngrediente_DTO(id);
		}

}
