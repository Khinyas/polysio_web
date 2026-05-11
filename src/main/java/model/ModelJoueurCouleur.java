package model;

public enum ModelJoueurCouleur {
    ROUGE("\uD83D\uDD34"),
    VERT("\uD83D\uDFE2"),
    BLEU("\uD83D\uDD35"),
    JAUNE("\uD83D\uDFE1");

    private final String couleurJoueur;

    // Le constructeur d'une Enum est TOUJOURS privé par défaut
    ModelJoueurCouleur (String couleurJoueurP) {
        this.couleurJoueur = couleurJoueurP;
    }

    public String getCouleurJoueur() {
        return couleurJoueur;
    }
}
