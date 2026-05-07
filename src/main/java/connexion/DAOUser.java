package connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ModelUser;
import model.ModelUserRole;
import service.Securite;

public class DAOUser {

    // VERIFIER SI UTILISATEUR EXISTE
    public static boolean reqVerifierUserExiste(String usernameP) {

        String reqSQL =
            "SELECT id_utilisateur FROM utilisateur WHERE pseudo = ?";

        try (Connection conn = DAOAcces.getConnexion();
             PreparedStatement pst =
                 conn.prepareStatement(reqSQL)) {

            pst.setString(1, usernameP);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException erreur) {

            erreur.printStackTrace();
        }

        return false;
    }

    // CONNEXION UTILISATEUR
    public static ModelUser connexionUtilisateur(
            String usernameP,
            String passwordP) {

        String reqSQL =
            "SELECT * FROM polysio.utilisateur WHERE pseudo = ?";

        try (PreparedStatement pst =
                 DAOAcces.getConnexion()
                 .prepareStatement(reqSQL)) {

            pst.setString(1, usernameP);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    String hashedPasswordBDD =
                        rs.getString("mot_de_passe");

                    if (Securite.verifyPassword(
                            passwordP,
                            hashedPasswordBDD)) {

                        return new ModelUser(
                            rs.getInt("id_utilisateur"),
                            rs.getString("pseudo"),
                            rs.getString("email"),
                            ModelUserRole.valueOf(
                                rs.getString("role")
                                .toUpperCase()
                            )
                        );
                    }
                }
            }

        } catch (SQLException erreur) {

            System.err.println(
                "Erreur SQL Impossible d'authentifier l'utilisateur : "
                + erreur.getMessage()
            );

            erreur.printStackTrace();
        }

        return null;
    }

    // RECUPERER USER
    public static ModelUser reqRecupererUser(int idP) {

        String reqSQL =
            "SELECT * FROM polysio.utilisateur WHERE id_utilisateur = ?";

        try (PreparedStatement pst =
                 DAOAcces.getConnexion()
                 .prepareStatement(reqSQL)) {

            pst.setInt(1, idP);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int id =
                        rs.getInt("id_utilisateur");

                    String username =
                        rs.getString("pseudo");

                    String email =
                        rs.getString("email");

                    ModelUserRole roleBDD;

                    try {

                        roleBDD =
                            ModelUserRole.valueOf(
                                rs.getString("role")
                                .toUpperCase()
                            );

                    } catch (Exception e) {

                        roleBDD =
                            ModelUserRole.UTILISATEUR;
                    }

                    return new ModelUser(
                        id,
                        username,
                        email,
                        roleBDD
                    );
                }
            }

        } catch (SQLException erreur) {

            System.err.println(
                "Erreur SQL Impossible de récupérer le profil utilisateur : "
                + erreur.getMessage()
            );

            erreur.printStackTrace();
        }

        return null;
    }

    // INSERTION USER
    public static Boolean insererUser(
            String usernameP,
            String passwordP,
            String emailP) {

        String passwordHache =
            Securite.hacherPassword(passwordP);

        String reqSQL =
            "INSERT INTO polysio.utilisateur " +
            "(pseudo, mot_de_passe, email) " +
            "VALUES (?,?,?)";

        try (PreparedStatement pst =
                 DAOAcces.getConnexion()
                 .prepareStatement(reqSQL)) {

            pst.setString(1, usernameP);
            pst.setString(2, passwordHache);
            pst.setString(3, emailP);

            int lignesAffectees =
                pst.executeUpdate();

            return lignesAffectees > 0;

        } catch (SQLException erreur) {

            System.err.println(
                "Erreur SQL Impossible de créer l'utilisateur : "
                + erreur.getMessage()
            );

            erreur.printStackTrace();

            return false;
        }
    }

    // DELETE USER
    public static Boolean deleteUSer(int idP) {

        String reqSQL =
            "DELETE FROM polysio.utilisateur WHERE id_utilisateur = ?";

        try (PreparedStatement pst =
                 DAOAcces.getConnexion()
                 .prepareStatement(reqSQL)) {

            pst.setInt(1, idP);

            int lignesAffectees =
                pst.executeUpdate();

            return lignesAffectees > 0;

        } catch (SQLException erreur) {

            System.err.println(
                "Erreur SQL Impossible de supprimer l'utilisateur : "
                + erreur.getMessage()
            );

            erreur.printStackTrace();

            return false;
        }
    }

    // LISTE UTILISATEURS
    public static ArrayList<ModelUser>
        reqListeTousLesUtilisateurs() {

        ArrayList<ModelUser> liste =
            new ArrayList<>();

        String reqSQL =
            "SELECT * FROM polysio.utilisateur";

        try (PreparedStatement pst =
                 DAOAcces.getConnexion()
                 .prepareStatement(reqSQL);

             ResultSet rs =
                 pst.executeQuery()) {

            while (rs.next()) {

                liste.add(
                    new ModelUser(
                        rs.getInt("id_utilisateur"),
                        rs.getString("pseudo"),
                        rs.getString("email"),
                        ModelUserRole.valueOf(
                            rs.getString("role")
                            .toUpperCase()
                        )
                    )
                );
            }

        } catch (SQLException erreur) {

            erreur.printStackTrace();
        }

        return liste;
    }

    // MODIFIER UTILISATEUR
    public static void modifierUtilisateur(
            int id,
            String pseudo,
            String email,
            String role) {

        String sql =
            "UPDATE polysio.utilisateur " +
            "SET pseudo = ?, email = ?, role = ? " +
            "WHERE id_utilisateur = ?";

        try (PreparedStatement ps =
                 DAOAcces.getConnexion()
                 .prepareStatement(sql)) {

            ps.setString(1, pseudo);
            ps.setString(2, email);
            ps.setString(3, role);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}