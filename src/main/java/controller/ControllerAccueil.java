package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;

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
 // Dans ControllerAccueil.java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. On récupère l'objet stocké en session
        ModelUser user = (ModelUser) request.getSession().getAttribute("userSession");

        // 2. Si l'utilisateur est connecté, on prépare les données pour la JSP
        if (user != null) {
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
        }
        
        
        
        
        
        request.setAttribute("servletAttribute", 1);
        String param = request.getParameter("action");
        if (param != null) {
        	System.out.println("test valeur null param");
        	switch (param) {
            	case "jouer" : {
            		response.sendRedirect(request.getContextPath() + "/ControllerConstructeurPlateau?action=jouer");
            		return;
            	}
            	case "choisirPartie" : {
                    request.getRequestDispatcher("/ControllerChoixPartie").forward(request, response);
                    return;
                }
            	
            	default : {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + param);
                    return;
            	}
        	}
        }
        //afficherPage(request, response);
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // On appelle la même méthode d'affichage
        afficherPage(request, response);
    }

    // Méthode utilitaire pour centraliser le forward
    private void afficherPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

}
