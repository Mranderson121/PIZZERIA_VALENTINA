package com.pizzeria.restfulcrud.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pizzeria.restfulcrud.dao.ClassDao;
import com.pizzeria.restfulcrud.model.*;


/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/GoToUpdatePage")
public class GoToUpdatePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToUpdatePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPizza = request.getParameter("idPizza");
		
		Pizza pizzaTrovata = ClassDao.FindPizzaByID(idPizza);
		
		if(pizzaTrovata != null) {
			List<Ingrediente> ingredienti = ClassDao.GetAllIngredienti();
			List<Impasto> impasti = ClassDao.GetAllImpasti();
			
			request.setAttribute("listaImpasti", impasti);
			request.setAttribute("listaIngredienti", ingredienti);
			request.setAttribute("pizza", pizzaTrovata);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}