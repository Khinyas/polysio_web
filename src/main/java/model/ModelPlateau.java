package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelPlateau {

    private List<ModelCase> listeCases;
    private List<ModelPropriete> listeProprietes;
    private List<ModelJoueur> listeJoueurs;
    private String contextPath;

    private Map<String, String> emojiMap = new HashMap<>();

    public ModelPlateau(List<ModelCase> listeCasesP, List<ModelPropriete> listeProprieteP, String contextPathP, List<ModelJoueur> joueurs) {
        this.listeCases = listeCasesP;
        this.listeProprietes = listeProprieteP;
        this.contextPath = contextPathP;
        this.listeJoueurs = joueurs;
    }

    public String initialiserPlateau(ModelJoueur joueurActuel) {
        StringBuilder html = new StringBuilder();
        html.append("<div class='jeu-container'>");

        html.append("<div class='plateau-grid'>");
        html.append(genererCasesHTML());         // private uniquement appelé ici
        html.append(genererJoueursHTML()); // private
        html.append("</div>");

        html.append(genererFichesDetailsHTML());  // private  modales cachées

        html.append("</div>");
        return html.toString();
    }

/**** Les méthodes internes du plateau qui sont private ***/
    private String genererCasesHTML() {
        StringBuilder html = new StringBuilder();
        for (ModelCase casePlateau : this.listeCases) {

            if (casePlateau.getNom().equals("centre")) {
                html.append("""
                    <div id='%s' class='case'
                         style='grid-column:2 / span 9; grid-row:2 / span 9;'>
                        <img src='%s' alt='%s' width='100%%' height='100%%'/>
                    </div>
                    """.formatted(
                        casePlateau.getIdCSS(),
                        contextPath + casePlateau.getCheminSvg(),
                        casePlateau.getNom()
                ));
            } else {
                // C'est une propriété
                String lienOuvrirDetail = (casePlateau instanceof ModelPropriete)
                        ? "href='#detail-" + casePlateau.getId() + "'"
                        : "";

                html.append("""
                    <div id='%s' class='case case-%s'
                         style='grid-column:%d; grid-row:%d;'
                         data-id='%d'>
                        <a %s>
                            <img src='%s' alt='%s' width='100%%' height='100%%'/>
                        </a>
                    </div>
                    """.formatted(
                        casePlateau.getIdCSS(),
                        casePlateau.getTypeCase(),
                        casePlateau.getPositionX() + 1,
                        casePlateau.getPositionY() + 1,
                        casePlateau.getId(),
                        lienOuvrirDetail,
                        contextPath + casePlateau.getCheminSvg(),
                        casePlateau.getNom()
                ));
            }
        }
        return html.toString();
    }

    private String genererJoueursHTML() {
        if (this.listeJoueurs == null || this.listeJoueurs.isEmpty()) return "";

        StringBuilder html = new StringBuilder();

        for (ModelJoueur j : this.listeJoueurs) {
            ModelCase caseActuelle = getCaseParPosition(j.getPosition());
            
            if (caseActuelle != null) {
                // On ajoute un pion pour chaque joueur
                // On peut utiliser j.getCouleur() ou j.getId() pour varier l'apparence
                html.append("""
                    <div class='pion pion-%s' 
                         title='%s'
                         style='grid-column:%d; grid-row:%d;'>
                        %s
                    </div>
                    """.formatted(
                            j.getPseudonyme(),
                            j.getPseudonyme(),
                            caseActuelle.getPositionX() + 1,
                            caseActuelle.getPositionY() + 1,
                        j.getCouleur()
                    ));
            }
        }
        return html.toString();
    }

    private String genererFichesDetailsHTML() {
        StringBuilder html = new StringBuilder();
        for (ModelCase casePlateau : this.listeCases) {
            if (casePlateau instanceof ModelPropriete propriete) {
                html.append(String.format("""
                    <div id='detail-%d' class='modal-overlay'>
                        <div class='modal-content'>
                            <a href='#' class='close'>&times;</a>
                            <h2>%s</h2>
                            <img src='%s%s' width='200'/>
                            <ul>
                                <li>Prix d'achat : %d pts</li>
                                <li>Loyer de base : %d pts</li>
                                <li>Avec bâtiment : %d pts</li>
                            </ul>
                            <p>Propriétaire : %s</p>
                        </div>
                    </div>
                    """,
                        propriete.getId(),
                        propriete.getNom(),
                        contextPath, propriete.getCheminSvgFiche(),
                        propriete.getPrix(),
                        propriete.getLoyerNu(),
                        propriete.getLoyerBatiment(),
                        propriete.getProprietaire() != null ? propriete.getProprietaire() : "Aucun"
                ));
            }
        }
        return html.toString();
    }

/*** Méthodes Public que la vue pourra apeller en dynamique ***/
    public String genererInventaireHTML(ModelJoueur joueurP) {
        if (joueurP == null || joueurP.getMesProprietes().isEmpty()) {
            return "<p style='font-size:0.8em; color:gray;'>Aucune propriété</p>";
        }
        
        StringBuilder html = new StringBuilder("<div class='inventaire' style='display:flex; flex-wrap:wrap; gap:8px;'>");
        for (ModelPropriete prop : joueurP.getMesProprietes()) {
            html.append(String.format("""
                <div class='carte-inventaire'>
                    <a href='#detail-%d'>
                        <img src='%s%s' title='%s' width='60' style='border-radius:4px; border:1px solid #444;'/>
                    </a>
                </div>
                """,
                prop.getId(), // ID de la case (ex: 24) pour ouvrir la bonne modale
                this.contextPath,
                prop.getCheminSvgFiche(), // Ce sera img_fiche grâce au ControllerCase
                prop.getNom()
            ));
        }
        html.append("</div>");
        return html.toString();
    }

    public ModelCase getCaseParPosition(int positionP) {
        for (ModelCase modelCase : listeCases) {
            if (modelCase.getId() == positionP) return modelCase;
        }
        return null;
    }

    public ModelPropriete getProprieteParPosition(int positionP) {
        for (ModelPropriete propriete : this.listeProprietes) {
            if (propriete.getId() == positionP) return propriete;
        }
        return null;
    }

    public void payer(ModelJoueur joueur, int montant) {
        int ancienSolde = joueur.getPointsCompetences();
        int nouveauSolde = ancienSolde - montant;
        joueur.setPointsCompetences(nouveauSolde);
        System.out.println("[ECONOMIE] " + joueur.getPseudonyme()
                + " : " + ancienSolde + " → " + nouveauSolde);
    }


    // GETTERS / SETTERS

    public List<ModelCase> getListeCases() { return listeCases; }
    public void setListeCases(List<ModelCase> listeCases) { this.listeCases = listeCases; }
    public List<ModelPropriete> getListeProprietes() { return listeProprietes; }
    public void setListeProprietes(List<ModelPropriete> listeProprietes) { this.listeProprietes = listeProprietes; }
    public List<ModelJoueur> getListeJoueurs() { return listeJoueurs; }
    public void setListeJoueurs(ArrayList<ModelJoueur> listeJoueurs) { this.listeJoueurs = listeJoueurs; }
}