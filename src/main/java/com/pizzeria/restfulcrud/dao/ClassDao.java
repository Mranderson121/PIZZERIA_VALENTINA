package com.pizzeria.restfulcrud.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.pizzeria.restfulcrud.DTOClass.ImpastoDTO;
import com.pizzeria.restfulcrud.DTOClass.PizzaDTO;
import com.pizzeria.restfulcrud.DTOClass.UtenteDTO;
import com.pizzeria.restfulcrud.model.Impasto;
import com.pizzeria.restfulcrud.model.Ingrediente;
import com.pizzeria.restfulcrud.model.Pizza;
import com.pizzeria.restfulcrud.model.Utente;
import com.pizzeria.restfulcrud.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ClassDao {

	private static boolean loginError = false;
	private static Utente utenteAttivo = null;

	public static Utente getUtenteAttivo() {
		return utenteAttivo;
	}

	public static void setUtenteAttivo(Utente utenteAttivo) {
		ClassDao.utenteAttivo = utenteAttivo;
	}

	public static boolean isLoginError() {
		return loginError;
	}

	public static void setLoginError(boolean loginError) {
		ClassDao.loginError = loginError;
	}

	/* INTERROGAZIONI AL DB */
	private static EntityManager entityManager = null;
	private static EntityTransaction entityTransaction = null;

	public static void openSession() {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

	}

	public static void closeSession() {
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	public static void SaveData() {
		entityManager.getTransaction().commit();
	}

	public static Utente FindUtenteByUsernamePassword(String username, String password) {
		openSession();

		List<Utente> listaEmp = new ArrayList<Utente>();
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u from Utente u where u.password = :password and u.username = :username", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<Utente> empList = query.getResultList();

		for (Utente emp : empList) {
			listaEmp.add(emp);
		}

		closeSession();
		return listaEmp.isEmpty() ? null : listaEmp.get(0);
	}

	public static List<Ingrediente> GetAllIngredienti() {
		openSession();
		List<Ingrediente> listaEmp = new ArrayList<Ingrediente>();
		TypedQuery<Ingrediente> query = /*
										 * entityManager.createNamedQuery("@SQL_GET_ALL_INGREDIENTE",
										 * Ingrediente.class);
										 */
				entityManager.createQuery("select u from Ingrediente u order by u.nome", Ingrediente.class);
		List<Ingrediente> empList = query.getResultList();

		for (Ingrediente emp : empList) {
			listaEmp.add(emp);
		}
		closeSession();
		return listaEmp;
	}

	public static List<Impasto> GetAllImpasti() {
		openSession();
		List<Impasto> listaEmp = new ArrayList<Impasto>();
		TypedQuery<Impasto> query = /* entityManager.createNamedQuery("@SQL_GET_ALL_IMPASTI", Impasto.class); */
				entityManager.createQuery("select u from Impasto u order by u.nome", Impasto.class);
		List<Impasto> empList = query.getResultList();

		for (Impasto emp : empList) {
			listaEmp.add(emp);
		}
		closeSession();
		return listaEmp;
	}

	public static void insertNewPizza(String nomePizza, Impasto impasto, Utente utente,
			Set<Ingrediente> ingredientiSet) {
		openSession();
		Pizza pizza = new Pizza(nomePizza, utente, impasto);

		Set<Pizza> pizzaSetU = new HashSet<Pizza>();
		Set<Pizza> pizzaSetI = new HashSet<Pizza>();
		Set<Ingrediente> ingredienteSetM2M = new HashSet<Ingrediente>();

		if (utente.getPizza() != null)
			pizzaSetU.addAll(utente.getPizza());
		pizzaSetU.add(pizza);

		if (impasto.getPizza() != null)
			pizzaSetI.addAll(impasto.getPizza());
		pizzaSetI.add(pizza);

		if (pizza.getIngrediente() != null)
			ingredienteSetM2M.addAll(pizza.getIngrediente());
		ingredienteSetM2M.addAll(ingredientiSet);

		impasto.setPizza(pizzaSetI);
		utente.setPizza(pizzaSetU);
		pizza.setIngrediente(ingredienteSetM2M);

		entityManager.persist(pizza);
		closeSession();
	}

	public static Impasto findImpastoByID(String id) {
		openSession();
		Impasto student = entityManager.find(Impasto.class, Long.valueOf(id));
		closeSession();
		return student;
	}

	public static List<Pizza> GetAllPizzeUtente(Utente utente) {
		openSession();
		List<Pizza> listaEmp = new ArrayList<Pizza>();

		TypedQuery<Pizza> query = /* .getNamedQuery("SQL_GET_ALL_PIZZE_UTENTE"); */
				entityManager.createQuery("select p from Pizza p  where p.id_pizza = :id order by p.nome", Pizza.class);
		query.setParameter("id", String.valueOf(utente.getId()));
		List<Pizza> empList = query.getResultList();

		for (Pizza emp : empList) {
			listaEmp.add(emp);
		}
		closeSession();
		return listaEmp;

	}

	public static Set<Ingrediente> StringArrayToIngredienteSet(String[] selectedIngrediente) {
		openSession();
		Set<Ingrediente> empSet = new HashSet<Ingrediente>();

		for (String s : selectedIngrediente) {
			Ingrediente ingrediente = entityManager.find(Ingrediente.class, Long.valueOf(s));
			empSet.add(ingrediente);
			ingrediente = null;
		}

		closeSession();
		return empSet;
	}

	public static void DeletePizzaByID(String idPizza) {
		openSession();
		Pizza p = entityManager.find(Pizza.class, Long.valueOf(idPizza));

		if (p != null) {
			p.getImpasto().getPizza().remove(p);
			p.getUtente().getPizza().remove(p);
			p.setIngrediente(null);
			setUtenteAttivo(p.getUtente());
			entityManager.remove(p);

		}
		closeSession();
	}

	public static Pizza FindPizzaByID(String id) {
		openSession();
		Pizza p = entityManager.find(Pizza.class, Long.valueOf(id));
		closeSession();
		return p;
	}

	public static void UpdatePizza(String idPizza, String nomePizza, Impasto impasto, Utente utente,
			Set<Ingrediente> ingredientiSet) {
		openSession();
		Pizza p = entityManager.find(Pizza.class, Long.valueOf(idPizza));

		if (p != null) {

			p.getUtente().getPizza().remove(p);
			p.getImpasto().getPizza().remove(p);

			p.setId(Long.valueOf(idPizza));
			p.setNome(nomePizza);
			p.setImpasto(impasto);
			p.setUtente(utente);
			p.setIngrediente(ingredientiSet);

			p.getUtente().getPizza().add(p);
			p.getImpasto().getPizza().add(p);

			setUtenteAttivo(p.getUtente());

		}

		closeSession();

	}

	/* DTO METHODS */
	public static List<ImpastoDTO> GetAllImpastiDTO() {
		openSession();
		List<ImpastoDTO> listaEmp = new ArrayList<ImpastoDTO>();
		TypedQuery<ImpastoDTO> query = entityManager.createQuery(
				"select new com.pizzeria.restfulcrud.DTOClass.ImpastoDTO(i.id, i.nome) from Impasto i",
				ImpastoDTO.class);

		listaEmp = query.getResultList();

		closeSession();
		return listaEmp;
	}

	public static ImpastoDTO findImpastoByID_DTO(String id_impasto) {
		openSession();
		Impasto impasto = entityManager.find(Impasto.class, Long.valueOf(id_impasto));
		ImpastoDTO dto = new ImpastoDTO(impasto.getId(), impasto.getNome());
		closeSession();
		return dto;
	}

	public static ImpastoDTO addImpasto_DTO(String nome) {
		openSession();

		Impasto emp = new Impasto(nome);
		emp.setPizza(null);

		entityManager.persist(emp);
		
		closeSession();
		return new ImpastoDTO(emp.getId(), emp.getNome());

	}

	public static Impasto updateImpasto_DTO(String id, String nome) {
		openSession();
		Impasto impasto = entityManager.find(Impasto.class, Long.valueOf(id));
		impasto.setNome(nome);
		closeSession();
		return impasto;
	}

	public static void deleteImpasto(String id) {
		openSession();
		Impasto impasto = entityManager.find(Impasto.class, Long.valueOf(id));
		entityManager.remove(impasto);
		closeSession();
	}

	public static List<Ingrediente> GetAllIngredientiDTO() {
		openSession();
		
		List<Ingrediente> listaEmp = new ArrayList<Ingrediente>();
		TypedQuery<Ingrediente> query = 
				entityManager.createQuery("select u from Ingrediente u", Ingrediente.class);
		listaEmp = query.getResultList();
		
		closeSession();
		return listaEmp;
	}

	public static Ingrediente findIngredienteByID_DTO(String id) {
		openSession();
		Ingrediente emp = entityManager.find(Ingrediente.class, Long.valueOf(id));
		closeSession();
		return emp;
	}

	public static Ingrediente addIngrediente_DTO(String nome) {
		openSession();

		Ingrediente emp = new Ingrediente(nome);

		entityManager.persist(emp);
		closeSession();
		return emp;

	}

	public static Ingrediente updateIngrediente_DTO(String id, String nome) {
		openSession();
		Ingrediente emp = entityManager.find(Ingrediente.class, Long.valueOf(id));
		emp.setNome(nome);
		closeSession();
		return emp;
	}

	public static void deleteIngrediente_DTO(String id) {
		openSession();
		Ingrediente emp = entityManager.find(Ingrediente.class, Long.valueOf(id));
		entityManager.remove(emp);
		closeSession();
		
	}

	public static List<PizzaDTO> GetAllPizzeDTO() {
		openSession();
		List<PizzaDTO> listaEmp = new ArrayList<PizzaDTO>();
		
		/*
			TypedQuery<PizzaDTO> query = entityManager.createQuery(
				"select new com.pizzeria.restfulcrud.DTOClass.PizzaDTO(p.id, p.nome,"
				+ " (new com.pizzeria.restfulcrud.DTOClass.UtenteDTO(p.utente.getId(), p.utente.getUsername(), p.utente.getPassword())),"
				+ " (new com.pizzeria.restfulcrud.DTOClass.ImpastoDTO(p.impasto.getId(), p.impasto.getNOme())) from Pizza p",
				PizzaDTO.class);
		 */
		
		TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p ", Pizza.class);
		List<Pizza> empList = query.getResultList();

		for(Pizza emp : empList) {
			listaEmp.add(new PizzaDTO(emp.getId(), emp.getNome(), 
					new UtenteDTO(emp.getUtente().getId(), emp.getUtente().getUsername(), emp.getUtente().getPassword()),
					new ImpastoDTO(emp.getImpasto().getId(), emp.getImpasto().getNome()),
					emp.getIngrediente()));
		}
		closeSession();
		return listaEmp;
	
	}

	public static PizzaDTO findPizzaByID_DTO(String id) {
		openSession();
		Pizza emp = entityManager.find(Pizza.class, Long.valueOf(id));
		PizzaDTO dto = new PizzaDTO(emp.getId(), emp.getNome(), 
				new UtenteDTO(emp.getUtente().getId(), emp.getUtente().getUsername(), emp.getUtente().getPassword()),
				new ImpastoDTO(emp.getImpasto().getId(), emp.getImpasto().getNome()),
				emp.getIngrediente());
		closeSession();
		return dto;
	}
	
	public static List<UtenteDTO> GetAllUtentiDTO() {
		openSession();
		List<UtenteDTO> listaEmp = new ArrayList<UtenteDTO>();
		TypedQuery<UtenteDTO> query = entityManager.createQuery(
				"select new com.pizzeria.restfulcrud.DTOClass.UtenteDTO(u.id, u.username, u.password) from Utente u",
				UtenteDTO.class);

		listaEmp = query.getResultList();

		closeSession();
		return listaEmp;
	}

	public static UtenteDTO findUtenteByID_DTO(String id_utente) {
		openSession();
		List<UtenteDTO> listaEmp = new ArrayList<UtenteDTO>();
		TypedQuery<UtenteDTO> query = entityManager.createQuery(
				"select new com.pizzeria.restfulcrud.DTOClass.UtenteDTO(u.id, u.username, u.password) from Utente u where u.id = :id",
				UtenteDTO.class);

		query.setParameter("id", Long.valueOf(id_utente));
		listaEmp = query.getResultList();

		closeSession();
		return listaEmp.get(0);
	}

	public static Utente addUtente_DTO(String username, String password) {
		openSession();

		Utente emp = new Utente(username, password);

		entityManager.persist(emp);
		closeSession();
		return emp;
	}

	public static Utente updateUtente_DTO(String id, String username, String password) {
		openSession();
		Utente emp = entityManager.find(Utente.class, Long.valueOf(id));
		emp.setUsername(username);
		emp.setPassword(password);
		closeSession();
		return emp;
	}

	public static void deleteUtente_DTO(String id) {
		openSession();
		Utente emp = entityManager.find(Utente.class, Long.valueOf(id));
		entityManager.remove(emp);
		closeSession();
	}



}
