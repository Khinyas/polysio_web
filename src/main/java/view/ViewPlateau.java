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

		// ── 1. RÉCUPÉRATION SESSION ──────────────────────────────────────
		HttpSession session  = request.getSession();
		ModelJoueur joueur   = (ModelJoueur)  session.getAttribute("joueurCourant");
		ModelPlateau plateau = (ModelPlateau) session.getAttribute("plateau");

		// ── 2. TRAITEMENT DES ACTIONS ────────────────────────────────────
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "lancerDe" -> {
					int de = (int)(Math.random() * 6) + 1;
					joueur.avancer(de);
					session.setAttribute("dernierDe", de);
				}
				case "acheter" -> {
					ModelCase casePlateau = plateau.getCaseParPosition(joueur.getPosition());
					if (casePlateau instanceof ModelPropriete propriete) {
						joueur.ajouterPropriete(propriete);
					}
				}
				case "finirTour"  -> { /* TODO */ }
				case "construire" -> { /* TODO */ }
				default -> System.out.println("Action inconnue : " + action);
			}
			// Post-Redirect-Get : évite de rejouer l'action sur F5
			response.sendRedirect(request.getContextPath() + "/ViewPlateau");
			return;
		}

		// ── 3. CHRONO ────────────────────────────────────────────────────
		long maintenant  = System.currentTimeMillis();
		Long finPartieMs = (Long) session.getAttribute("finPartieMs");
		if (finPartieMs == null) {
			finPartieMs = maintenant + (1800L * 1000); // 30 minutes
			session.setAttribute("finPartieMs", finPartieMs);
		}
		long secondesRestantes = Math.max(0, (finPartieMs - maintenant) / 1000);

		// ── 4. ON PRÉPARE LES DONNÉES POUR LE JSP ───────────────────────
		request.setAttribute("plateauHTML",     plateau.initialiserPlateau(joueur));
		request.setAttribute("inventaireHTML",  plateau.genererInventaireHTML(joueur));
		request.setAttribute("listeJoueurs",    plateau.getListeJoueurs());
		request.setAttribute("tempsRestantSec", (int) secondesRestantes);

		// Dé : on consomme le message stocké en session
		Integer dernierDe = (Integer) session.getAttribute("dernierDe");
		if (dernierDe != null) {
			session.removeAttribute("dernierDe");
			request.setAttribute("dernierDe", dernierDe);
		}

		// ── 5. ON ENVOIE VERS LE JSP ─────────────────────────────────────
		request.getRequestDispatcher("/WEB-INF/plateau.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}