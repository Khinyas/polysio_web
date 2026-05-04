package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet implementation class ControllerAccueil
 */
@WebServlet("/ControllerAccueil")
public class ControllerAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("servletAttribute", 1);
        String param = request.getParameter("action");	
        if (param != null) {
        	System.out.println("test valeur null param");
        	switch (param) {
	        	case "jouer" : { 
	        		response.sendRedirect(request.getContextPath() + "/ControllerConstructeurPlateau");
	        		return;
	        	}
        	}
        }
        afficherPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique pour traiter le formulaire
        String nom = request.getParameter("pseudo"); // Recupere la donnee du formulaire inscription.jsp
        request.setAttribute("NomRecu", nom); // Pour l'envoyer a l'accueil
        
        // On appelle la même méthode d'affichage
        afficherPage(request, response);
    }

    // Méthode utilitaire pour centraliser le forward
    private void afficherPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

}
