package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ControllerChoixPartie
 */
@WebServlet("/ControllerChoixPartie")
public class ControllerChoixPartie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerChoixPartie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/ChoixPartie.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nbJoueurs = request.getParameter("nbJoueurs");
		String tempsJeu = request.getParameter("tempsJeu");
		
		if(nbJoueurs != null && tempsJeu != null) {
			request.getSession().setAttribute("nbJoueurConfig", Integer.parseInt(nbJoueurs));
			request.getSession().setAttribute("nbJoueurConfig", Integer.parseInt(tempsJeu));
			
			response.sendRedirect(request.getContextPath() + "/ControllerPlateau?action=jouer");
		}
		
		doGet(request, response);
		
		
	}

}
