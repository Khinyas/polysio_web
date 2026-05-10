package model;


public class ModelPropriete extends ModelCase {

    private int prix;
    private int loyerNu;
    private int loyerBatiment;
    private int idCouleur;
    private String proprietaire;
    private boolean batiment;
	private String cheminSvgFiche;
    //  id, nom, typeCase, cheminSvg, casePlateau
    //  déjà dans ModelCase (id, nom, typeCase, cheminSvg, positionX/Y)

    public ModelPropriete(
            int id,
            String nom,
            String typeCase,
            int positionX,
            int positionY,
            String cheminSvgPlateau,  // ← image plateau → passée à super()
            String cheminSvgFiche, 
            String idCSS,
            int prix,
            int loyerNu,
            int loyerBatiment,
            int idCouleur,
            boolean batiment
    ) {
        super(id, nom, typeCase, positionX, positionY, cheminSvgPlateau, idCSS);
        this.prix = prix;
        this.loyerNu = loyerNu;
        this.loyerBatiment = loyerBatiment;
        this.idCouleur = idCouleur;
        this.batiment = batiment;
        this.proprietaire = null;
        this.cheminSvgFiche = cheminSvgFiche;
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


	public String getCheminSvgFiche() {
		return cheminSvgFiche;
	}


	public void setCheminSvgFiche(String cheminSvgFiche) {
		this.cheminSvgFiche = cheminSvgFiche;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public void setLoyerNu(int loyerNu) {
		this.loyerNu = loyerNu;
	}


	public void setLoyerBatiment(int loyerBatiment) {
		this.loyerBatiment = loyerBatiment;
	}


	public void setIdCouleur(int idCouleur) {
		this.idCouleur = idCouleur;
	}
    
}
