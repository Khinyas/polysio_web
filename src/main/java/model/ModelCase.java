package model;

public class ModelCase {
	
	private int id;
	private String nom;
	private int positionX;
	private int positionY;
	private String typeCase;
	private String cheminSvg;
	private String idCSS;

	public ModelCase(int idP, String nomP, String typeCaseP, int positionXP,int positionYP, String cheminSvgP, String idCSSP) {
		
		this.id = idP;
		this.nom = nomP;
		this.typeCase = typeCaseP;
		this.positionX = positionXP;
		this.positionY = positionYP;
		this.cheminSvg = cheminSvgP;
		this.idCSS = idCSSP;
	}


	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	public String getTypeCase() {
		return typeCase;
	}


	public void setTypeCase(String typeCase) {
		this.typeCase = typeCase;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}
	
	public String getCheminSvg() {
		return cheminSvg;
	}


	public void setCheminSvg(String cheminSvg) {
		this.cheminSvg = cheminSvg;
	}


	public String getIdCSS() {
		return idCSS;
	}


	public void setIdCSS(String idCSS) {
		this.idCSS = idCSS;
	}

	

}
