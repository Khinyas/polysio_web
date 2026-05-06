package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;

import java.io.IOException;

/**
 * Servlet implementation class ControllerProfil
 */
@WebServlet("/ControllerProfil")
public class ControllerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 // Dans ControllerAccueil.java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. On récupère l'objet stocké en session
        ModelUser user = (ModelUser) request.getSession().getAttribute("userSession");

        // 2. Si l'utilisateur est connecté, on prépare les données pour la JSP
        if (user != null) {
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
        }
        
        afficherPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // On appelle la même méthode d'affichage
        afficherPage(request, response);
    }

    // Méthode utilitaire pour centraliser le forward
    private void afficherPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
    }

}
