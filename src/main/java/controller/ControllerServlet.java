package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ControllerServlet
 */

@WebServlet(urlPatterns = {"/index", "/accueil", "/connexion"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getServletPath();
		String vue = "";
		
		switch (path) {
		
			case "/index":
				request.setAttribute("message", "Bienvenue sur l'index");
				vue = "/WEB-INF/index.jsp";
			case "/accueil":
				request.setAttribute("message", "Page connexion");
				vue = "/WEB-INF/connexion.jsp";
			case "/connexion":
				request.setAttribute("message", "Bienvenue sur le plateau");
				vue = "/WEB-INF/plateau.jsp";
			
			
				
			default:
				vue = "/WEB-INF/404.jsp";
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
