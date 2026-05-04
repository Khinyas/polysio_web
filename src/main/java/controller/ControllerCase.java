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
		String reqCasesBdd = "SELECT * FROM polysio.case_plateau ORDER BY id_case_plateau ASC";
		try (Connection conn = DAOAcces.getConnexion()) {
			PreparedStatement pst = conn.prepareStatement(reqCasesBdd);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ModelCase casePlateau = new ModelCase(
					rs.getInt("id_case_plateau"),
					rs.getString("nom_case"),
					rs.getString("type_case"),
					rs.getInt("positionX"),
					rs.getInt("positionY"),
					rs.getString("chemin_svg"),
					rs.getString("idCSS")
						);
				listeCases.add(casePlateau);
			}
		} catch (SQLException erreur) {
			System.err.println("Erreur SQL : Liste Cases non récupérée " + erreur.getMessage());
			erreur.printStackTrace();
		}
		System.out.println("DEBUG SQL : Nombre de cases récupérées = " + listeCases.size());
		return listeCases;
	}
}
