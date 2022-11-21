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

import com.pizzeria.restfulcrud.DTOClass.UtenteDTO;
import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.Utente;

@Path("/utenti")
public class UtenteService {

	// URI:
		// /contextPath/servletPath/impasti
		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<UtenteDTO> getIngredienti_JSON() {
			List<UtenteDTO> listOfCountries = ClassDao.GetAllUtentiDTO();
			return listOfCountries;
		}
		
		// URI:
		// /contextPath/servletPath/impasti/{id}
		@GET
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public UtenteDTO getImpasto(@PathParam("id") String id) {
			return ClassDao.findUtenteByID_DTO(id);
		}


		// URI:
		// /contextPath/servletPath/employees
		@POST
		@Path("/{username}/{password}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Utente addEmployee(@PathParam("username") String username,
									@PathParam("password") String password) {
			return ClassDao.addUtente_DTO(username, password);
		}

		// URI:
		// /contextPath/servletPath/employees
		@PUT
		@Path("/{id}/{username}/{password}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Utente updateImpastoName(@PathParam("id") String id,
											@PathParam("username") String username,
											@PathParam("password") String password){
			return ClassDao.updateUtente_DTO(id,username, password);
		}

		@DELETE
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void deleteEmployee(@PathParam("id") String id) {
			ClassDao.deleteUtente_DTO(id);
		}

}
