package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.DAOAcces;

public class ModelUser {
	private String username;
	private String email;
	private String password;
	private String newusername;
    // Ajoute d'autres champs si besoin (id, email, etc.)

    public ModelUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public ModelUser(String newusername) {
		this.newusername = newusername;
	}
	
	// Ajoute les getters et setters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNewusername() { return newusername; }
    
    public ModelUser trouverParPseudo(String pseudo) {
        String sql = "SELECT * FROM utilisateur WHERE pseudo = ?";
        try (Connection conn = DAOAcces.getConnexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pseudo);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ModelUser(rs.getString("pseudo"), rs.getString("email"), rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
