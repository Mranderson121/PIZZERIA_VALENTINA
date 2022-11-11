package servlet;

import java.io.IOException;
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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		String idPizza = request.getParameter("idPizza");
		
		Utente utente = ClassDao.getUtenteAttivo();
		
		
		if(utente != null) {
			
			Impasto impasto =  ClassDao.findImpastoByID(selectedImpasto);
			if(impasto != null && nomePizza!= null && selectedIngrediente != null) {
				// pizza da caricare
				Set<Ingrediente> ingredientiSet = ClassDao.StringArrayToIngredienteSet(selectedIngrediente);
				ClassDao.UpdatePizza(idPizza, nomePizza, impasto, utente, ingredientiSet);
				
				
				response.sendRedirect("HomeServlet");	
			}
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
