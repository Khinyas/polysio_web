package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelCase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.DAOAcces;
import model.ModelPropriete;

/**
 * Servlet implementation class ControllerCase
 */
@WebServlet("/ControllerCase")
public class ControllerCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerCase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected static ArrayList<ModelCase> plateauBuilder() {
		ArrayList<ModelCase> listeCases = new ArrayList<>();

		String reqSQL = """
        SELECT c.*,
			c.chemin_svg AS img_plateau, 
           p.chemin_svg AS img_fiche,
           p.id_propriete, p.prix, p.loyer_nue, 
           p.loyer_batiment, p.id_couleur, p.batiment
        FROM polysio.case_plateau c
        LEFT JOIN polysio.propriete p USING (id_case_plateau)
        ORDER BY c.id_case_plateau ASC
    """;

		try (Connection conn = DAOAcces.getConnexion();
			 PreparedStatement pst = conn.prepareStatement(reqSQL);
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				if (rs.getObject("id_propriete") != null) {
					listeCases.add(new ModelPropriete(
							rs.getInt("id_case_plateau"),
							rs.getString("nom_case"),
							rs.getString("type_case"),
							rs.getInt("positionX"),
							rs.getInt("positionY"),
						    rs.getString("img_plateau"),  // ✅ image plateau → super()
						    rs.getString("img_fiche"), 
							rs.getString("idCSS"),
							rs.getInt("prix"),
							rs.getInt("loyer_nue"),
							rs.getInt("loyer_batiment"),
							rs.getInt("id_couleur"),
							rs.getBoolean("batiment")
					));
				} else {
					listeCases.add(new ModelCase(
							rs.getInt("id_case_plateau"),
							rs.getString("nom_case"),
							rs.getString("type_case"),
							rs.getInt("positionX"),
							rs.getInt("positionY"),
							rs.getString("img_plateau"),
							rs.getString("idCSS")
					));
				}
			}
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
		return listeCases;
	}
}
