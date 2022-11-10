package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
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
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	private static Transaction tx = null;
	
	
	public static void openSession() {
		
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		
	}
	
	public static void closeSession() {
		tx.commit();
		//closing hibernate resources
		sessionFactory.close();	
		
	}
	
	public static void SalvaData() {
		tx.commit();
		
	}
	
	
	
	public static Utente FindUtenteByUsernamePassword(String username, String password) {
		openSession();
		List<Utente> listaEmp = new ArrayList<Utente>();
		Query query = session.getNamedQuery("SQL_GET_UTENTE_BY_USERNAME_PASSWORD");
		query.setString("username", username);
		query.setString("password", password);
		List<Utente> empList = query.list();
		
		for (Utente emp : empList) {
			listaEmp.add(emp);
		}
		
		//tx.commit();
		return listaEmp.isEmpty() ? null : listaEmp.get(0);
	}

	public static List<Ingrediente> GetAllIngredienti() {
		List<Ingrediente> listaEmp = new ArrayList<Ingrediente>();
		Query query = session.getNamedQuery("SQL_GET_ALL_INGREDIENTI");
		List<Ingrediente> empList = query.list();

		for (Ingrediente emp : empList) {
			listaEmp.add(emp);
		}
		
		//closeSession();
		
		return listaEmp;
		
	}

	public static List<Impasto> GetAllImpasti() {
		List<Impasto> listaEmp = new ArrayList<Impasto>();
		Query query = session.getNamedQuery("SQL_GET_ALL_IMPASTI");
		List<Impasto> empList = query.list();

		for (Impasto emp : empList) {
			listaEmp.add(emp);
		}
		//closeSession();
		return listaEmp;
	}

	public static void insertNewPizza(String nomePizza, Impasto impasto, Utente utente, Set<Ingrediente> ingredientiSet) {
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
		
		session.save(pizza);
	}


	public static Impasto findImpastoByID(String id) {
		//openSession();
		List<Impasto> listaEmp = new ArrayList<Impasto>();
	
		Query query = session.getNamedQuery("SQL_GET_IMPASTO_BY_ID");
		query.setString("id", id);
		List<Impasto> empList = query.list();
		
		for (Impasto emp : empList) {
			listaEmp.add(emp);
		}
		//closeSession();
		return listaEmp.isEmpty() ? null : listaEmp.get(0);
		
	}

	public static List<Pizza> GetAllPizzeUtente(Utente utente) {
		List<Pizza> listaEmp = new ArrayList<Pizza>();
		
		Query query = session.getNamedQuery("SQL_GET_ALL_PIZZE_UTENTE");
		query.setString("id", String.valueOf(utente.getId()));
		List<Pizza> empList = query.list();
		
		for (Pizza emp : empList) {
			listaEmp.add(emp);
		}
		//closeSession();
		return listaEmp;
		
	}

	public static Set<Ingrediente> StringArrayToIngredienteSet(String[] selectedIngrediente) {
		Set<Ingrediente> empSet = new HashSet<Ingrediente>();
		List<Ingrediente> empList = new ArrayList<Ingrediente>();
		
		for(String s : selectedIngrediente) {
			Query query = session.getNamedQuery("SQL_GET_INGREDIENTE_BY_ID");
			query.setString("id", s);
			empList = query.list();
			if(!empList.isEmpty()) empSet.add(empList.get(0));
			empList = null;
		}
		
		return empSet;
	}

	public static void DeletePizzaByID(String idPizza) {
		Pizza p = (Pizza) session.load(Pizza.class, Long.valueOf(idPizza));
		
		if(p != null) {
			p.getImpasto().getPizza().remove(p);
			p.getUtente().getPizza().remove(p);
			p.setIngrediente(null);
;			session.delete(p);
			
		}tx.commit();
		openSession();
	}

	public static Pizza FindPizzaByID(String id) {
		List<Pizza> listaEmp = new ArrayList<Pizza>();
		
		Query query = session.getNamedQuery("SQL_GET_PIZZA_BY_ID");
		query.setString("id", id);
		List<Pizza> empList = query.list();
		
		for (Pizza emp : empList) {
			listaEmp.add(emp);
		}
		//closeSession();
		return listaEmp.isEmpty() ? null : listaEmp.get(0);
	}

	public static void UpdatePizza(String idPizza, String nomePizza, Impasto impasto, Utente utente, Set<Ingrediente> ingredientiSet) {
		
		Pizza p = (Pizza) session.load(Pizza.class, Long.valueOf(idPizza));
		if(p != null) {
			
			p.getUtente().getPizza().remove(p);
			p.getImpasto().getPizza().remove(p);
			
			p.setId(Long.valueOf(idPizza));
			p.setNome(nomePizza);
			p.setImpasto(impasto);
			p.setUtente(utente);
			p.setIngrediente(ingredientiSet);
			
			p.getUtente().getPizza().add(p);
			p.getImpasto().getPizza().add(p);
			
			session.update(p);
			
		}tx.commit();
		openSession();
		
	}

	

	
	
	
}
