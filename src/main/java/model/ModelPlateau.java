package model;

import java.util.ArrayList;
import java.util.List;

// On utilise StackPane pour pouvoir empiler le background ET le plateau par dessus
public class ModelPlateau  {
    private List<ModelCase> listeCases;
    private List<ModelPropriete> listeProprietes;
    private ArrayList<ModelJoueur> listeJoueurs;

    public ModelPlateau(List<ModelCase> listeCasesP, List<ModelPropriete> listeProprieteP) {
        this.listeCases = listeCasesP;
        this.listeProprietes = listeProprieteP;
        listeJoueurs = new ArrayList<>();

        //this.initialiserPlateau();
    }

    public List<ModelCase> getListeCases() {
        return listeCases;
    }

    public List<ModelPropriete> getListePropriete() {
        return listeProprietes;
    }

   public void initialiserPlateau() {
        // Inserer controller plateau pour créer le plateau graphique à partir des model cases
        for (ModelCase caseModelCase : this.listeCases) {
            // On ignore la case centrale ici pour ne pas l'ajouter en 1x1 dans la boucle
            if (caseModelCase.getId() == 99 || caseModelCase.getNom().equalsIgnoreCase("centre")) {
                continue;
            }
            ViewCase casePlateau = new ViewCase(caseModelCase);

// FORCE LA CASE À PRENDRE TOUTE LA PLACE DE LA CELLULE
            GridPane.setHgrow(casePlateau, Priority.ALWAYS);
            GridPane.setVgrow(casePlateau, Priority.ALWAYS);

            int colonne = caseModelCase.getPositionX();
            int ligne = caseModelCase.getPositionY();

            // Utilisation directe de add() pour plus de clarté
            this.add(casePlateau, colonne, ligne);
            // ToDo : Création d'une liste de ViewCase pour utiliser pour les popup etc (est ce indispensable ?? )
            listeViewCases.add(casePlateau);
        }

        // ToDo : CaseCentrale est la derniere pour l'instant mais peut etre prendre getName ou Id à la place ?
        // On récupère la case centrale et on lui donne un Span de 9x9 pour remplir le milieu
        ModelCase mCentrale = this.listeCases.getLast();
        ViewCase vueCentrale = new ViewCase(mCentrale);
        this.add(vueCentrale, 1, 1, 9, 9);

        // Constuire les Cartes de proprietees :

        for (ModelPropriete propriete : this.listeProprietes) {
            ViewPropriete viewPropriete = new ViewPropriete(propriete);
            listeViewPropriete.add(viewPropriete);
        }
    }
}