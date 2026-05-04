package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelCase;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param = request.getParameter("action");

		// ← Si pas de paramètre, on considère que c'est "jouer" par défaut
		if (param == null) {
			param = "jouer";
		}

		switch (param) {
			case "jouer" : {
				List<ModelCase> listeCases = ControllerCase.plateauBuilder();
				request.setAttribute("listeCasesBdd", listeCases);
				request.getRequestDispatcher("/ViewPlateau").forward(request, response);
				return;
			}
			default : {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + param);
			}
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique pour traiter le formulaire
       // String nom = request.getParameter("username"); // Recupere la donnee du formulaire inscription.jsp
     //   request.setAttribute("NomRecu", nom); // Pour l'envoyer a l'accueil
        
        // On appelle la même méthode d'affichage
       // afficherPage(request, response);
    }

   /* // Méthode utilitaire pour centraliser le forward
    private void afficherPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    } */

}
