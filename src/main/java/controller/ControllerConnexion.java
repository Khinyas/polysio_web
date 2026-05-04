package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;
import service.Securite;

import java.io.IOException;

import connexion.DAOUser;

/**
 * Servlet implementation class ControllerConnexion
 */
@WebServlet("/ControllerConnexion")
public class ControllerConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Juste afficher le formulaire
        request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("username");
        String passwordSaisi = request.getParameter("password");

        DAOUser dao = new DAOUser();
        ModelUser user = dao.trouverParPseudo(pseudo);

        // Securite.verifierPassword doit comparer le clair saisi et le hash en base
        if (user != null && Securite.verifyPassword(passwordSaisi, user.getPassword())) {
            request.getSession().setAttribute("userSession", user);
            response.sendRedirect("ControllerAccueil");
        } else {
            request.setAttribute("msgErreur", "Identifiants incorrects.");
            request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        }
    }

}
