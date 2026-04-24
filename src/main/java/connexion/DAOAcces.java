package connexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOAcces {
    private static final Properties props = new Properties();

    // Bloc statique pour charger la config une seule fois au démarrage du serveur
    static {
        try (InputStream input = DAOAcces.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Désolé, impossible de trouver config.properties");
            } else {
                props.load(input);
                // Chargement explicite du driver pour Tomcat
                Class.forName(props.getProperty("db.driver"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnexion() throws SQLException {
        // Construction de l'URL avec les paramètres du fichier properties
        String url = props.getProperty("db.url") +
                     "?autoReconnect=" + props.getProperty("db.autoReconnect") +
                     "&useSSL=" + props.getProperty("db.useSSL") +
                     "&serverTimezone=" + props.getProperty("db.serverTimezone") +
                     "&allowPublicKeyRetrieval=true";
        
        return DriverManager.getConnection(
            url, 
            props.getProperty("db.login"), 
            props.getProperty("db.password")
        );
    }
}

  /*  public static void initConnexion() {
        // Paramétrage :
        String driver = MainApp.cfgApp.get("db.driver");
        String dbName = MainApp.cfgApp.get("db.name");
        String url = MainApp.cfgApp.get("db.url");
        String login = MainApp.cfgApp.get("db.login");
        String password = MainApp.cfgApp.get("db.password");
        int PORT = MainApp.cfgApp.getInt("db.port");
        String fullUrl = url +
                "?autoReconnect=" + MainApp.cfgApp.get("db.autoReconnect") +
                "&useSSL=" + MainApp.cfgApp.get("db.useSSL") +
                "&serverTimezone=" + MainApp.cfgApp.get("db.serverTimezone") +
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

*/
