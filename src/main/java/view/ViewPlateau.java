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

		int tempsRestantSec = (session.getAttribute("tempsInitial") != null) ? (int)session.getAttribute("tempsInitial") : 1800;
		
		String plateauHTMLRaw = plateau.initialiserPlateau(joueur);
		String inventaireHTMLRaw = plateau.genererInventaireHTML(joueur);
		String listeJoueursHTMLRaw = listeJoueursHTML.toString();

		// ON DOUBLE LES % POUR ÉVITER LE CONFLIT AVEC LE FORMATED DE LA VUE
		String plateauHTML = (plateauHTMLRaw != null) ? plateauHTMLRaw.replace("%", "%%") : "";
		String inventaireHTML = (inventaireHTMLRaw != null) ? inventaireHTMLRaw.replace("%", "%%") : "";
		String listeJoueursFinal = (listeJoueursHTMLRaw != null) ? listeJoueursHTMLRaw.replace("%", "%%") : "";
		
		String chronoHTML = """
			    <div id="chrono-container" style="background: #333; color: #ffeb3b; padding: 10px; margin-bottom:10px; text-align: center; border-radius: 5px; font-family: monospace; font-size: 1.5rem;">
			        ⏱️ <span id="timer">00:00</span>
			    </div>
			""";

		PrintWriter out = response.getWriter();
		out.print("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Plateau de jeu</title>
               
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
            
				<script>
            
            let tempsInitial = %s; 
            let urlAccueil = "%s";
            
            function startTimer() {
                const display = document.querySelector('#timer');
                let timer = tempsInitial;

                const interval = setInterval(function () {
                    let minutes = parseInt(timer / 60, 10);
                    let seconds = parseInt(timer %% 60, 10); 

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    if(display) display.textContent = minutes + ":" + seconds;

                    if (--timer < 0) {
                        clearInterval(interval);
                        alert("⏰ TEMPS ÉCOULÉ ! Fin de la partie.");
                        window.location.href = urlAccueil + "/accueil";
                    }
                }, 1000);
            }
            window.onload = startTimer;
				    </script>
            
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
                            
				                <hr>
				                <h4>Joueurs</h4>
			                    %s 
			                    <hr>
				                <h4>Mes propriétés</h4>
				                %s
                        </div>

                    </div>
                </div>

                %s

            </body>
            </html>
        """.formatted(
        		tempsRestantSec,
				request.getContextPath(),
				plateauHTML,
				chronoHTML,
				// plateau.genererInventaireHTML(joueur),  // inventaire dans le panneau// modales cachées en bas du body
				listeJoueursFinal,
				inventaireHTML                       
			    
				
		));

		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}