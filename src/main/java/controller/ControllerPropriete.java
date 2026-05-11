package controller;

import connexion.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelPropriete;
import model.ModelUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class ControllerAccueil
 */
@WebServlet("/ControllerPropriete")
public class ControllerPropriete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPropriete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 // Dans ControllerAccueil.java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            response.getWriter().append("Served at: ").append(request.getContextPath());
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // On appelle la même méthode d'affichage
        afficherPage(request, response);
    }

    // Méthode utilitaire pour centraliser le forward
    private void afficherPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    public static ArrayList<ModelPropriete> proprietePlateau() {
        String reqSQL = "SELECT p.*, " +
                "p.chemin_svg AS img_fiche," +
                "c.chemin_svg AS img_plateau," +
                " c.nom_case, c.type_case, c.positionX, c.positionY \n" +
                "FROM polysio.propriete p  \n" +
                "JOIN case_plateau c USING (id_case_plateau)\n" +
                "ORDER BY id_propriete ASC";
        ArrayList<ModelPropriete> listeProprieteBdd = new ArrayList<>();
        try (PreparedStatement pst = DAOAcces.getConnexion().prepareStatement(reqSQL);

             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                {
                    ModelPropriete proprietes =  new ModelPropriete(
                            rs.getInt("id_propriete"),
                            rs.getString("nom_case"),
                            rs.getString("type_case"),
                            rs.getInt("positionX"),
                            rs.getInt("positionY"),
                            rs.getString("img_plateau"),  // ✅ image plateau
                            rs.getString("img_fiche"), 
                            rs.getString("idCSS"),
                            rs.getInt("prix"),
                            rs.getInt("loyer_nue"),
                            rs.getInt("loyer_batiment"),
                            rs.getInt("id_couleur"),
                            rs.getBoolean("batiment")
                    );
                    listeProprieteBdd.add(proprietes);
                }
            }
            System.out.println("DEBUG SQL : Nombre de proprietés récupérées = " + listeProprieteBdd.size());
            return listeProprieteBdd;
        }
        catch (SQLException erreur) {
            // Un seul CATCH : erreurs SQL
            System.err.println("Erreur SQL : Liste proprietés non récupérée " + erreur.getMessage());
            erreur.printStackTrace();
        }
        return listeProprieteBdd;}


}



