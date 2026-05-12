package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOAcces {

	public static ConfigLoader cfgApp = new ConfigLoader(ConfigLoader.ENV_FILE_PATH);
	

    private static Connection conn = null;


    public DAOAcces() {
        System.out.println(" Initialisation de la connexion " + cfgApp.get("db.type") + "..." + cfgApp.get("db.name"));
        throw new UnsupportedOperationException("Classe utilitaire");
    }

    public static Connection getConnexion() {
        try {
            if (conn == null || conn.isClosed()) {
                initConnexion();
            }
            return conn;
        } catch (SQLException erreur) {
            System.out.println("Erreur lors de la vérification de la connexion : " + erreur.getMessage());
            erreur.printStackTrace();
        }
        return null;
    }

    public static void initConnexion() {
        // Paramétrage :
        String driver = cfgApp.get("db.driver");
        String dbName = cfgApp.get("db.name");
        String url = cfgApp.get("db.url");
        String login = cfgApp.get("db.login");
        String password = cfgApp.get("db.password");
        int PORT = cfgApp.getInt("db.port");
        String fullUrl = url +
                "?autoReconnect=" + cfgApp.get("db.autoReconnect") +
                "&useSSL=" + cfgApp.get("db.useSSL") +
                "&serverTimezone=" + cfgApp.get("db.serverTimezone") +
                "&characterEncoding=" + cfgApp.get("db.characterEncoding") +
                "&allowPublicKeyRetrieval=true";
        try  {
            Class.forName(driver);
            System.out.println("Chargement du Driver : " + driver);
            conn = DriverManager.getConnection(fullUrl , login, password);
            System.out.println("Connecté à la base de données : " + dbName);
            System.out.println("Url base de donnée : " + fullUrl);
        } catch (ClassNotFoundException erreur) {
            System.out.println("Driver Non Chargé (not found)" + erreur.getMessage());
            erreur.printStackTrace();
            throw new RuntimeException(erreur);
        } catch (SQLException erreur) {
            System.out.println("Erreur SQL, Constructor Fail" + erreur.getMessage());
            erreur.printStackTrace();
        }
    }

    public static void closeConnexion() {
        try {
            conn.close();
            System.out.println("🔌 Connexion base de données fermée.");
        } catch (SQLException erreur) {
            System.out.println("Erreur SQL, Constructor Fail" + erreur.getMessage());
            erreur.printStackTrace();
        }
    }
}

