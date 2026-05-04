package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ControllerConstructeurPlateau
 */
@WebServlet("/ControllerConstructeurPlateau")
public class ControllerConstructeurPlateau extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerConstructeurPlateau() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameter("parametre") == null) {
	    }
        String param = request.getParameter("action");	
        if (param != null) {
        	System.out.println("test valeur null param");
        	switch (param) {
	        	case "jouer" : { 
	        		List<ModelCase> listeCases = new ArrayList<>();
	        		request.setAttribute("listeCases", listeCases)
	        		response.sendRedirect(request.getContextPath() + "/ViewPlateau");
	        		return;
	        	}
        	}
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
