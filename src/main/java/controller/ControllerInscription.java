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
        request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // On utilise la méthode statique qui existe déjà dans ton DAOUser
        Boolean succes = DAOUser.insererUser(name, password, email);
        
     // --- CONTRÔLE DE SAISIE ---
        if (name == null || name.trim().length() < 3) {
            response.sendRedirect("inscription.jsp?erreur=pseudo_court");
            return;
        }
        if (!email.contains("@")) {
            response.sendRedirect("inscription.jsp?erreur=email_invalide");
            return;
        }
        
        if (succes) {
            // Inscription réussie : on redirige vers la connexion
            // On ajoute getContextPath() pour éviter les erreurs 404 sur Tomcat
            response.sendRedirect(request.getContextPath() + "/ControllerConnexion");
        } else {
            // Échec (ex: pseudo déjà pris) : on renvoie au formulaire
            response.sendRedirect(request.getContextPath() + "/ControllerInscription?erreur=1");
        }
    }

}
