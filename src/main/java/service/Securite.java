package service;

import connexion.DAOAcces;
import org.mindrot.jbcrypt.BCrypt;

public class Securite {

    public static String hacherPassword(String passwordClairP) {
        // C'est ici qu'on utilise l'import : l'appel à BCrypt.hashpw
        String passwordWithPrivateKey = passwordClairP.trim() + DAOAcces.cfgApp.get("db.privateKey");
        return BCrypt.hashpw(passwordWithPrivateKey, BCrypt.gensalt());
    }

    public static Boolean verifyPassword(String passwordSaisi, String passwordHashedP) {
        try {
            String passwordWithPrivateKey = passwordSaisi + DAOAcces.cfgApp.get("db.privateKey");
            return BCrypt.checkpw(passwordWithPrivateKey, passwordHashedP);
        } catch (Exception e) {
            return false;
        }
    }
}
