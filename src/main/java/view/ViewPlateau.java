package view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelCase;
import model.ModelJoueur;
import model.ModelPlateau;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewPlateau
 */
@WebServlet("/ViewPlateau")
public class ViewPlateau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPlateau() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		ModelPlateau plateau = (ModelPlateau) request.getSession().getAttribute("plateau");
		ModelJoueur joueur = (ModelJoueur) request.getSession().getAttribute("joueurActif");

		if (plateau == null) {
			response.sendRedirect(request.getContextPath() + "/accueil");
			return;
		}

		// Le HTML du plateau est déjà construit dans ModelPlateau
		String plateauHTML = plateau.getPlateauHTML();

		// Infos joueur (null-safe)
		String nomJoueur = (joueur != null) ? joueur.getPseudonyme() : "—";
		int argent = (joueur != null) ? joueur.getPointsCompetences() : 0;

		PrintWriter out = response.getWriter();

		// 1. Header en premier
		request.getRequestDispatcher("/WEB-INF/header.jsp").include(request, response);

		// 2. Reste de la page
		out.print("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>Plateau de jeu</title>
            <link rel="stylesheet" href="%s/css/style.css">
            <style>
                *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
                html, body { height: 100%%; overflow: hidden; }
                .page-wrapper { display: flex; flex-direction: column; height: 100vh; }
                .game-area {
                    flex: 1; display: flex; align-items: center;
                    justify-content: flex-start; gap: 16px; padding: 12px; min-height: 0;
                }
                .plateau-wrapper { height: 100%%; aspect-ratio: 1/1; flex-shrink: 0; }
                .plateau-grid {
                    display: grid;
                    grid-template-columns: 12.5%% repeat(9, 8.3%%) 12.5%%;
                    grid-template-rows:    12.5%% repeat(9, 8.3%%) 12.5%%;
                    width: 80vmin; height: 80vmin;
                }
                .case {
                    border: 1px solid #333; display: flex; flex-direction: column;
                    align-items: center; justify-content: center; overflow: hidden;
                }
                .case img { width: 100%%; height: 100%%; object-fit: fill; display: block; }
                .side-panel {
                    flex: 1; min-width: 200px; height: 100%%;
                    background: #f5f0e8; border: 1px solid #ccc;
                    border-radius: 8px; padding: 16px;
                    display: flex; flex-direction: column; gap: 12px; overflow-y: auto;
                }
            </style>
        </head>
        <body>
            <div class="page-wrapper">
                <div class="game-area">

                    <div class="plateau-wrapper">
                        %s
                    </div>

                    <div class="side-panel">
                        <h3>%s</h3>
                        <p>💰 %d pts</p>
                    </div>

                </div>
            </div>
        </body>
        </html>
        """.formatted(
				request.getContextPath(),
				plateauHTML,          // ← HTML déjà construit par ModelPlateau
				nomJoueur,
				argent
		));

		out.flush();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}