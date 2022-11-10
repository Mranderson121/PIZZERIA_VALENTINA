package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomePizza = request.getParameter("nomePizza");
		String selectedImpasto = request.getParameter("selectedImpasto");
		String[] selectedIngrediente = request.getParameterValues("selectedIngrediente");
		
		Utente utente = ClassDao.getUtenteAttivo();
		
		
		if(utente != null) {
			
			Impasto impasto =  ClassDao.findImpastoByID(selectedImpasto);
			if(impasto != null && nomePizza!= null && selectedIngrediente != null) {
				// pizza da caricare
				Set<Ingrediente> ingredientiSet = ClassDao.StringArrayToIngredienteSet(selectedIngrediente);
				ClassDao.insertNewPizza(nomePizza, impasto, utente, ingredientiSet);
			}
			
			
			List<Pizza> pizzeUtente = null;
			pizzeUtente = ClassDao.GetAllPizzeUtente(utente);
			List<Ingrediente> ingredienti = ClassDao.GetAllIngredienti();
			
			List<Impasto> impasti = ClassDao.GetAllImpasti();
			
			request.setAttribute("pizzeUtente", pizzeUtente);
			request.setAttribute("listaImpasti", impasti);
			request.setAttribute("listaIngredienti", ingredienti);
			request.setAttribute("user", utente);
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else 
			response.sendRedirect("login.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
