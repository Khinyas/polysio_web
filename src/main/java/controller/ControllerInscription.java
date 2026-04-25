package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;


import java.io.IOException;

import connexion.DAOUser;

/**
 * Servlet implementation class ControllerInscription
 */
@WebServlet("/ControllerInscription")
public class ControllerInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Juste afficher le formulaire
        request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("username");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	ModelUser nouvelUtilisateur = new ModelUser(name,email,password);
    	DAOUser dao = new DAOUser();
    	dao.creerUtilisateur(nouvelUtilisateur);
    	
        // On redirige
    	response.sendRedirect("/ControllerAccueil");
    }

}
