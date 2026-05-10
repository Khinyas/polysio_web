package model;


public class ModelPropriete extends ModelCase {

    private int prix;
    private int loyerNu;
    private int loyerBatiment;
    private int idCouleur;
    private String proprietaire;
    private boolean batiment;
    //  id, nom, typeCase, cheminSvg, casePlateau
    //  déjà dans ModelCase (id, nom, typeCase, cheminSvg, positionX/Y)

    public ModelPropriete(
            int id,
            String nom,
            String typeCase,
            int positionX,
            int positionY,
            String cheminSvg,
            String idCSS,
            int prix,
            int loyerNu,
            int loyerBatiment,
            int idCouleur,
            boolean batiment
    ) {
        super(id, nom, typeCase, positionX, positionY, cheminSvg, idCSS);
        this.prix = prix;
        this.loyerNu = loyerNu;
        this.loyerBatiment = loyerBatiment;
        this.idCouleur = idCouleur;
        this.batiment = batiment;
        this.proprietaire = null;
    }


    // plus besoin de : getId, getNom, getCheminSvg → hérités de ModelCase
    // garder uniquement les getters/setters propres à la propriété :
    public int getPrix() { return prix; }
    public int getLoyerNu() { return loyerNu; }
    public int getLoyerBatiment() { return loyerBatiment; }
    public int getIdCouleur() { return idCouleur; }
    public String getProprietaire() { return proprietaire; }
    public void setProprietaire(String proprietaire) { this.proprietaire = proprietaire; }
    public boolean isBatiment() { return batiment; }
    public void setBatiment(boolean batiment) { this.batiment = batiment; }
}
