package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelUser;
import model.ModelUserRole;

import java.io.IOException;
import java.util.ArrayList;

import connexion.DAOUser;

/**
 * Servlet implementation class ControllerServlet
 */



@WebServlet(urlPatterns = { "/connexion",  "/index", "/accueil", "/inscription","/profil", "/ChoixPartie", "/admin"})

public class ControllerServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// On aiguille au niveau de l'URL, la page de connexion sera la première page qui ouvre ici.

		String path = request.getServletPath();

		if (path.endsWith(".css") || path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".js")) {
			return;
		}


		String vue = "";


		ModelUser user = (ModelUser) request.getSession().getAttribute("userSession");

		// Ici, c'est les if pour éviter les intrusions, il faudra peut être en mettre plus

		if (path.equals("/admin")) {
			if (user == null || user.getRole() != ModelUserRole.ADMIN) {
				request.getSession().setAttribute("flashMessage", "Accès refusé : Identifiants insuffisants.");
				response.sendRedirect(request.getContextPath() + "/accueil");
				return;
			}
		}


		if (path.equals("/connexion") || path.equals("/inscription")) {
			if (user != null) {
				// L'utilisateur est déjà connecté, on le renvoie vers l'accueil
				response.sendRedirect(request.getContextPath() + "/accueil");
				return;
			}
		}

		if (path.equals("/profil")) {
			if (user == null) {
				System.out.println("DEBUG: Accès refusé, redirection accueil");
				response.sendRedirect(request.getContextPath() + "/connexion");
				return;
			}
		}


		switch (path) {


			case "/index":

				request.setAttribute("message", "Bienvenue sur l'index");
				vue = "/WEB-INF/index.jsp";
				break;


			case "/admin":

				ArrayList<ModelUser> utilisateurs = DAOUser.reqListeTousLesUtilisateurs();
				request.setAttribute("utilisateurs", utilisateurs);
				vue = "/WEB-INF/admin.jsp";
				break;


			case "/connexion":
				request.setAttribute("message", "Bienvenue sur le plateau");
				vue = "/WEB-INF/connexion.jsp";
				break;


			case "/inscription":
				request.setAttribute("message", "Page d'inscription");
				vue = "/WEB-INF/inscription.jsp";
				break;


			case "/accueil":
				String flash = (String) request.getSession().getAttribute("flashMessage");
				if (flash != null) {
					request.setAttribute("message", flash); // ← "message" comme le JSP attend
					request.getSession().removeAttribute("flashMessage");
				} else {
					request.setAttribute("message", "Page d'accueil");
				}
				vue = "/WEB-INF/accueil.jsp";
				break;

			case "/profil":
				request.setAttribute("message", "Page de profil");
				vue = "/WEB-INF/profil.jsp";
				break;


			case "/ChoixPartie":
				request.setAttribute("message", "Choix de partie");
				vue = "/WEB-INF/ChoixPartie.jsp";
				break;


			default:
				vue = "/WEB-INF/404.jsp";
				break;


		}
		request.getRequestDispatcher(vue).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		int id = Integer.parseInt(request.getParameter("id"));
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		DAOUser.modifierUtilisateur(id, pseudo, email, role);
		response.sendRedirect(request.getContextPath() + "/accueil?afficher=true");
		return;

	}

}

