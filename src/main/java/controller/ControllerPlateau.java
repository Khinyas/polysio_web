package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ControllerConstructeurPlateau
 */
@WebServlet("/ControllerPlateau")
public class ControllerPlateau extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPlateau() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        //response.getWriter().append("Served at: ").append(request.getContextPath());
        String param = request.getParameter("action");
        if (param == null) {
            param = (String) request.getAttribute("action"); // ← ajout
        }
        if (param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action manquante");
            return;
        }
        if (param != null) {
            System.out.println("test valeur null param");
            switch (param) {
                case "jouer": {
                	// Partie création joueur : à corriger si il faut
                	Integer nbJoueurs = (Integer) request.getSession().getAttribute("nbJoueursConfig");
                    int nbFinalJoueur = (nbJoueurs != null) ? nbJoueurs : 2;
                    List<ModelJoueur> joueurs = new ArrayList<>();
                    for (int i = 0; i < nbFinalJoueur; i++) {
                  
                        joueurs.add(new ModelJoueur(i, 0, 1500, ModelJoueurCouleur.values()[i], "Joueur " + (i+1)));
                    }
                    
                	// partie liste case
                    List<ModelCase> listeCases = new ArrayList<>();
                    listeCases = ControllerCase.plateauBuilder();
                    
                    // partie liste propriété
                    List<ModelPropriete> listeProprietes = new ArrayList<>();
                    listeProprietes = ControllerPropriete.proprietePlateau();
                    
                    
                    // partie chrono pour compter le temps de partie
                    Integer tempsEnMinutes = (Integer) request.getSession().getAttribute("tempsJeuConfig");
                    if (tempsEnMinutes == null) tempsEnMinutes = 30; // Valeur par défaut

                    // On calcule le temps de fin en millisecondes (timestamp) pour plus de précision
                    long tempsFinPartie = System.currentTimeMillis() + (tempsEnMinutes * 60 * 1000);
                    request.getSession().setAttribute("tempsFinPartie", tempsFinPartie);
                    request.getSession().setAttribute("tempsInitial", tempsEnMinutes * 60);

                    // Todo Liste de joueur à rajouter en parametre (et du constructeur ModelPlateau)
                    ModelPlateau modelPlateau = new ModelPlateau(listeCases, listeProprietes, request.getContextPath(), joueurs);
                    
                    modelPlateau.setListeJoueurs((ArrayList<ModelJoueur>) joueurs);
                    if (!joueurs.isEmpty()) {
                        request.getSession().setAttribute("joueurCourant", joueurs.get(0));
                    }
                    
                    request.getSession().setAttribute("plateau", modelPlateau);

                    request.getRequestDispatcher("/ViewPlateau").forward(request, response);
                    return;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + param);
                }
            }
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
