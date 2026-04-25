package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import connexion.DAOUser;
import model.ModelUser;

@WebServlet("/ControllerConnexion")
public class ControllerConnexion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("username");
        String pass = request.getParameter("password");

        // Utilisation de ta méthode DAO
        ModelUser userConnecte = DAOUser.connexionUtilisateur(pseudo, pass);

        if (userConnecte != null) {
            // 1. Créer une session pour mémoriser l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", userConnecte);
            
            // 2. Rediriger vers l'accueil
            response.sendRedirect(request.getContextPath() + "/accueil");
        } else {
            // 3. Retour à la page avec un message d'erreur
            response.sendRedirect(request.getContextPath() + "/ControllerConnexion?erreur=identifiants");
        }
    }
}
