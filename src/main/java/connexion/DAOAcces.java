package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOAcces {

    // Tes identifiants (à adapter selon ta configuration locale)
    private static final String URL = "jdbc:mysql://localhost:3306/polysio";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnexion() {
        try {
            // 1. Charger le pilote (Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Créer et retourner la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Pilote JDBC introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible de se connecter à la base !");
            e.printStackTrace();
        }
        return null;
    }
}