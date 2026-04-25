package service;


import org.mindrot.jbcrypt.BCrypt;

public class Securite {

    public static String hacherPassword(String passwordClairP) {
        // C'est ici qu'on utilise l'import : l'appel à BCrypt.hashpw
    	
        return BCrypt.hashpw(passwordClairP, BCrypt.gensalt());
    }

    public static Boolean verifyPassword(String passwordSaisi, String passwordHashedP) {
        try {
            // Et ici : l'appel à BCrypt.checkpw
            return BCrypt.checkpw(passwordSaisi, passwordHashedP);
        } catch (Exception e) {
            return false;
        }
    }
}
