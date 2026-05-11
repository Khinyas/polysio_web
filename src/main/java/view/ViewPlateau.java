package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelCase;
import model.ModelJoueur;
import model.ModelPlateau;
import model.ModelPropriete;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ViewPlateau")
public class ViewPlateau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// 1. RÉCUPÉRATION SESSION
		HttpSession session = request.getSession();
		ModelJoueur joueur = (ModelJoueur) session.getAttribute("joueurCourant");
		ModelPlateau plateau = (ModelPlateau) session.getAttribute("plateau");
		
		StringBuilder listeJoueursHTML = new StringBuilder();
		
		if (plateau != null && plateau.getListeJoueurs() != null) {
		    for (ModelJoueur j : plateau.getListeJoueurs()) {
		        listeJoueursHTML.append("<div class='joueur-info'>");
		        listeJoueursHTML.append("<h4>").append(j.getPseudonyme()).append("</h4>");
		        listeJoueursHTML.append("<p>💰 ").append(j.getPointsCompetences()).append(" pts</p>");
		        listeJoueursHTML.append("</div><hr>");
		    }
		}

		// 2. TRAITEMENT DES ACTIONS
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "lancerDe" -> {
					int de = (int)(Math.random() * 6) + 1;
					joueur.avancer(de);
				}
				case "acheter" -> {
					ModelCase casePlateau = plateau.getCaseParPosition(joueur.getPosition());
					if (casePlateau instanceof ModelPropriete propriete) {
						joueur.ajouterPropriete(propriete); // ← corrigé : "prop" → "propriete"
					}
				}
				case "finirTour" -> {
					// TODO : passer au joueur suivant
				}
				case "construire" -> {
					// TODO : ajouter une maison
				}
				default -> System.out.println("Action inconnue : " + action);
			}

			// Post-Redirect-Get : évite de rejouer l'action sur F5
			response.sendRedirect(request.getContextPath() + "/ViewPlateau");
			return;
		}

		// 3. RENDU HTML (uniquement si pas d'action)
		String nomJoueur = (joueur != null) ? joueur.getPseudonyme() : "—";
		int argent      = (joueur != null) ? joueur.getPointsCompetences() : 0;
		String plateauHTML = plateau.initialiserPlateau(joueur);

		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/WEB-INF/header.jsp").include(request, response);
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
                    .modal-overlay {
                        display: none;
                        position: fixed;
                        top: 0; left: 0; width: 100%%; height: 100%%;
                        background: rgba(0,0,0,0.7);
                    }
                    .modal-overlay:target {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                    }
                    .btn {
                        display: inline-block; padding: 8px 16px;
                        background: #4a7c59; color: white; border-radius: 4px;
                        text-decoration: none;
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
                            

                            <a href="?action=lancerDe"  class="btn">🎲 Lancer les dés</a>
                            <a href="?action=acheter"   class="btn">🏠 Acheter</a>
                            <a href="?action=construire" class="btn">🔨 Construire</a>
                            <a href="?action=finirTour" class="btn">⏭ Finir le tour</a>

                            <h4>Mes propriétés</h4>
                            %s
                        </div>

                    </div>
                </div>

                %s

            </body>
            </html>
        """.formatted(
				request.getContextPath(),
				plateauHTML,
			//	nomJoueur,
			//	argent,
				listeJoueursHTML.toString(),
				plateau.genererInventaireHTML(joueur)  // inventaire dans le panneau// modales cachées en bas du body
		));

		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}