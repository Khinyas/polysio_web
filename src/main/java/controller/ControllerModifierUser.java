package controller;

import java.io.IOException;

import connexion.DAOUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifierUser")
public class ControllerModifierUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Appel DAO
        DAOUser.modifierUtilisateur(id, pseudo, email, role);

        // Retour panel admin
        response.sendRedirect(
            request.getContextPath()
            + "/accueil?afficher=true"
        );
    }
}