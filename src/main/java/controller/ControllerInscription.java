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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Sécurité : Vérifier si les paramètres sont null avant de faire .isEmpty()
        if (username == null || email == null || password == null) {
            // Si on arrive ici sans données, on renvoie simplement au formulaire
            doGet(request, response);
            return;
        }

        String erreur = null;

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            erreur = "Tous les champs sont obligatoires.";
        } 
        
        // Dans ControllerInscription.java
        else if (!username.matches("^[a-zA-Z0-9]+$")) {
            erreur = "Le pseudo ne doit contenir que des lettres et des chiffres.";
        }
        
        else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_-])(?=\\S+$).{8,}$")) {
            erreur = "Le mot de passe doit contenir 8 caractères, Maj, Min, Chiffre et Symbole.";
        }
        else if (!password.equals(confirmPassword)) {
            erreur = "Les mots de passe ne correspondent pas.";
        }
        else if (new DAOUser().existeDeja(username, email)) {
            erreur = "Le nom d'utilisateur ou l'email est déjà pris.";
        }

        if (erreur != null) {
            request.setAttribute("msgErreur", erreur);
            request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
        } else {
            new DAOUser().creerUtilisateur(new ModelUser(username, email, password));
            // UNE SEULE REDIRECTION ICI
            request.getRequestDispatcher("ControllerConnexion").forward(request, response);
        }
        // SURTOUT PAS DE CODE ICI APRÈS LE ELSE
    }

}
