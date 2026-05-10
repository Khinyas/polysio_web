package model;

import java.util.ArrayList;
import java.util.List;

// On utilise StackPane pour pouvoir empiler le background ET le plateau par dessus
public class ModelPlateau  {
    private List<ModelCase> listeCases;
    private List<ModelPropriete> listeProprietes;
    private ArrayList<ModelJoueur> listeJoueurs;
    private String contextPath;
    private String plateauHTML;

    public ModelPlateau(List<ModelCase> listeCasesP, List<ModelPropriete> listeProprieteP, String contextPathP) {
        this.listeCases = listeCasesP;
        this.listeProprietes = listeProprieteP;
        this.contextPath = contextPathP;
        this.plateauHTML = initialiserPlateau();
    }

    public List<ModelCase> getListeCases() {
        return listeCases;
    }

    public List<ModelPropriete> getListePropriete() {
        return listeProprietes;
    }

    public String getPlateauHTML() {
        return plateauHTML;
    }

    public void setPlateauHTML(String plateauHTML) {
        this.plateauHTML = plateauHTML;
    }

    public ArrayList<ModelJoueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(ArrayList<ModelJoueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public void setListeCases(List<ModelCase> listeCases) {
        this.listeCases = listeCases;
    }

    public List<ModelPropriete> getListeProprietes() {
        return listeProprietes;
    }

    public void setListeProprietes(List<ModelPropriete> listeProprietes) {
        this.listeProprietes = listeProprietes;
    }

    public String initialiserPlateau() {
       StringBuilder cases = new StringBuilder();
       cases.append("<div class='plateau-grid'>");

       for (ModelCase casePlateau : this.listeCases) {
           // On convertit les positions 0-10 en positions CSS 1-11
           int cssX = casePlateau.getPositionX() + 1;
           int cssY = casePlateau.getPositionY() + 1;

           if (casePlateau.getNom().equals("centre")) {
               cases.append("""
                    <div id='%s' class='case'
                         style='grid-column:%d / span 9; grid-row:%d / span 9;'>
                        <img src='%s' alt='%s' width='100%%' height='100%%'/>
                    </div>
                    """.formatted(
                       casePlateau.getIdCSS(),
                       2,
                       2,
                       contextPath + casePlateau.getCheminSvg(),
                       casePlateau.getNom()
               ));
           } else {
               cases.append("""
                    <div id='%s' class='case case-%s'
                         style='grid-column:%d; grid-row:%d;'
                         data-id='%d'>
                        <img src='%s' alt='%s' width='100%%' height='100%%'/>
                    </div>
                    """.formatted(
                       casePlateau.getIdCSS(),
                       casePlateau.getTypeCase(),
                       casePlateau.getPositionX() + 1,
                       casePlateau.getPositionY() + 1,
                       casePlateau.getId(),
                       contextPath + casePlateau.getCheminSvg(),
                       casePlateau.getNom()
               ));
           }
       }

       cases.append("</div>");

       return cases.toString();
   }



    public ModelCase getCaseParPosition(int positionP) {
        for (ModelCase modelCase : listeCases) {
            if (modelCase.getId() == positionP) return modelCase;
        }
        return null;
    }

    public ModelPropriete getProprieteParPosition(int positionP) {
        for (ModelPropriete propriete : this.listeProprietes) {
            if (propriete.getCasePlateau() == positionP) return propriete;
        }
        return null;
    }

    public void payer(ModelJoueur joueur, int montant) {
        int ancienSolde = joueur.getPointsCompetences();
        int nouveauSolde = ancienSolde - montant;
        joueur.setPointsCompetences(nouveauSolde);
        System.out.println("[ECONOMIE] " + joueur.getPseudonyme()
                + " : " + ancienSolde + " -> " + nouveauSolde);
    }

}
