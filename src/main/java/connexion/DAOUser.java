package connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ModelUser;

public class DAOUser {

    public void creerUtilisateur(ModelUser user) {
    	String sql = "INSERT INTO utilisateur (pseudo,email,mot_de_passe) VALUES (?,?,?)";
    	

        // Utilise DAOAcces pour obtenir la connexion existante
        try (Connection conn = DAOAcces.getConnexion()) {
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, user.getUsername());
             pstmt.setString(2, user.getEmail());
             pstmt.setString(3, user.getPassword());
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}