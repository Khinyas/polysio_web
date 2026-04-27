package connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ModelUser;
import service.Securite;

public class DAOUser {

    public void creerUtilisateur(ModelUser user) {
    	String sql = "INSERT INTO utilisateur (pseudo,email,mot_de_passe) VALUES (?,?,?)";
    	

        // Utilise DAOAcces pour obtenir la connexion existante
        try (Connection conn = DAOAcces.getConnexion()) {
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, user.getUsername());
             pstmt.setString(2, user.getEmail());
             // Hash le mot de passe passé en paramètre
             pstmt.setString(3, Securite.hacherPassword(user.getPassword()));
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}