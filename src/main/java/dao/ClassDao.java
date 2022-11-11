package dao;

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

import util.JPAUtil;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

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


	/*  INTERROGAZIONI AL DB */
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
		TypedQuery<Ingrediente> query = /*entityManager.createNamedQuery("@SQL_GET_ALL_INGREDIENTE", Ingrediente.class);*/
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
		TypedQuery<Impasto> query =/* entityManager.createNamedQuery("@SQL_GET_ALL_IMPASTI", Impasto.class);*/
				entityManager.createQuery("select u from Impasto u order by u.nome", Impasto.class);
		List<Impasto> empList = query.getResultList();

		for (Impasto emp : empList) {
			listaEmp.add(emp);
		}
		closeSession();
		return listaEmp;
	}

	public static void insertNewPizza(String nomePizza, Impasto impasto, Utente utente, Set<Ingrediente> ingredientiSet) {
		openSession();
		Pizza pizza = new Pizza(nomePizza, utente, impasto);
		
		Set<Pizza> pizzaSetU = new HashSet<Pizza>();
		Set<Pizza> pizzaSetI = new HashSet<Pizza>();
		Set<Ingrediente> ingredienteSetM2M = new HashSet<Ingrediente>();
		
		if(utente.getPizza() != null)pizzaSetU.addAll(utente.getPizza());
		pizzaSetU.add(pizza);
		
		if(impasto.getPizza() != null)pizzaSetI.addAll(impasto.getPizza());
		pizzaSetI.add(pizza);
		
		
		if(pizza.getIngrediente()!= null)ingredienteSetM2M.addAll(pizza.getIngrediente());
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
		
		TypedQuery<Pizza> query = /*.getNamedQuery("SQL_GET_ALL_PIZZE_UTENTE");*/
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
		
		for(String s : selectedIngrediente) {
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
		
		if(p != null) {
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

	public static void UpdatePizza(String idPizza, String nomePizza, Impasto impasto, Utente utente, Set<Ingrediente> ingredientiSet) {
		openSession();
		Pizza p = entityManager.find(Pizza.class, Long.valueOf(idPizza));
		
		if(p != null) {
			
			p.getUtente().getPizza().remove(p);
			p.getImpasto().getPizza().remove(p);
			
			p.setId(Long.valueOf(idPizza));
			p.setNome(nomePizza);
			p.setImpasto(impasto);
			p.setUtente(utente);
			p.setIngrediente(ingredientiSet);
			
			/*
			p.getUtente().getPizza().add(p);
			p.getImpasto().getPizza().add(p);*/
			
			setUtenteAttivo(p.getUtente());
			
		}
		
		closeSession();
		
	}

	

	
	
	
}
