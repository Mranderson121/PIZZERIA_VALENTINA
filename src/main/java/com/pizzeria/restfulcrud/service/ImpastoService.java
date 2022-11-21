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

import com.pizzeria.restfulcrud.DTOClass.ImpastoDTO;
import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.Impasto;


@Path("/impasti")
public class ImpastoService {

	// URI:
		// /contextPath/servletPath/impasti
		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<ImpastoDTO> getImpasti_JSON() {
			List<ImpastoDTO> listOfCountries = ClassDao.GetAllImpastiDTO();
			return listOfCountries;
		}
		
		// URI:
		// /contextPath/servletPath/impasti/{id}
		@GET
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public ImpastoDTO getImpasto(@PathParam("id") String id_impasto) {
			return ClassDao.findImpastoByID_DTO(id_impasto);
		}

		

		// URI:
		// /contextPath/servletPath/employees
		@POST
		@Path("/{nome}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public ImpastoDTO addEmployee(@PathParam("nome") String nome) {
			return ClassDao.addImpasto_DTO(nome);
		}

		// URI:
		// /contextPath/servletPath/employees
		@PUT
		@Path("/{id}/{nome}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Impasto updateImpastoName(@PathParam("id") String id,
											@PathParam("nome") String nome){
			return ClassDao.updateImpasto_DTO(id,nome);
		}

		@DELETE
		@Path("/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void deleteEmployee(@PathParam("id") String id) {
			ClassDao.deleteImpasto(id);
		}

}
