package connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ModelUser;
import service.Securite;

public class DAOUser {
	
	// Dans DAOUser.java
	public boolean existeDeja(String pseudo, String email) {
	    String sql = "SELECT COUNT(*) FROM utilisateur WHERE pseudo = ? OR email = ?";
	    try (Connection conn = DAOAcces.getConnexion();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, pseudo);
	        pstmt.setString(2, email);
	        var rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

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
    
    public void modifierUtilisateur(ModelUser modifUser) {
		String sql = "UPDATE `utilisateur` SET `pseudo`=? WHERE id_utilisateur=41;";
		
		// Utilise DAOAcces pour obtenir la connexion existante
        try (Connection conn = DAOAcces.getConnexion()) {
             PreparedStatement pstmt = conn.prepareStatement(sql);
             System.out.println(modifUser.getNewusername());
             pstmt.setString(1, modifUser.getNewusername());
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
 // Dans ton fichier DAOUser.java
    public ModelUser trouverParPseudo(String pseudo) {
        // La requête SQL pour trouver l'utilisateur par son pseudo
        String sql = "SELECT pseudo, email, mot_de_passe FROM utilisateur WHERE pseudo = ?";
        
        try (Connection conn = DAOAcces.getConnexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pseudo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // On crée et on retourne l'objet ModelUser avec les données de la BDD
                    return new ModelUser(
                        rs.getString("pseudo"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si l'utilisateur n'est pas trouvé
    }

}