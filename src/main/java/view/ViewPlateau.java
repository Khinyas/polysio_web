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

		// --- AJOUTE CE FILET DE SÉCURITÉ ICI ---
		if (plateau == null || joueur == null) {
		    // Si on n'a rien, on force le passage par le contrôleur de création
		    response.sendRedirect(request.getContextPath() + "/ControllerPlateau?action=jouer");
		    return; // Stop l'exécution ici !
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
	                    // On vérifie si elle n'appartient à personne (Optionnel selon ton Model)
	                    if (propriete.getProprietaire() == null || propriete.getProprietaire().equals("Aucun")) {
	                        joueur.ajouterPropriete(propriete);
	                        System.out.println("[ACHAT] " + joueur.getPseudonyme() + " achète " + propriete.getNom());
	                    }
	                }
	            }
	            case "finirTour" -> {
	                // Logique pour changer de joueur à implémenter ici
	            }
	            case "construire" -> {
	                // Logique pour ajouter un bâtiment
	            }
	            default -> System.out.println("Action inconnue : " + action);
	        }

	        // CRUCIAL : On ré-enregistre le joueur modifié dans la session
	        session.setAttribute("joueurCourant", joueur);

	        // Post-Redirect-Get : On redirige pour "nettoyer" l'URL et éviter les doubles clics
	        response.sendRedirect(request.getContextPath() + "/ViewPlateau");
	        return; 
	    }

		// 3. RENDU HTML (uniquement si pas d'action)
		String nomJoueur = (joueur != null) ? joueur.getPseudonyme() : "—";
		int argent      = (joueur != null) ? joueur.getPointsCompetences() : 0;
		String plateauHTML = plateau.initialiserPlateau(joueur);

		PrintWriter out = response.getWriter();
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
                            <h3>%s</h3>
                            <p>💰 %d pts</p>

                            <a href="?action=lancerDe"  class="btn">🎲 Lancer les dés</a>
                            <a href="?action=acheter"   class="btn">🏠 Acheter</a>
                            <a href="?action=construire" class="btn">🔨 Construire</a>
                            <a href="?action=finirTour" class="btn">⏭ Finir le tour</a>

                            <h4>Mes propriétés</h4>
                            %s
                        </div>

                    </div>
                </div>


            </body>
            </html>
	""".formatted(
	        request.getContextPath(),             // 1 : Pour le CSS
	        plateauHTML,                          // 2 : Le plateau
	        nomJoueur,                            // 3 : Titre <h3>
	        argent,                               // 4 : Argent 💰
	        plateau.genererInventaireHTML(joueur) // 5 : Liste des cartes
	        //,plateau.genererFichesDetailsHTML()    // 6 : LES MODALES (Manquant !)
	    ));

		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}