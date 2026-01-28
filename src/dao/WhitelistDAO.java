package dao;

import database.ConnexionBDD;
import java.sql.*;

public class WhitelistDAO {

    public boolean addEmail(String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO whitelist_emails (email) VALUES (?)"
            );
            ps.setString(1, email);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur ajout whitelist : " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmail(String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM whitelist_emails WHERE email = ?"
            );
            ps.setString(1, email);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur suppression utilisateur : " + e.getMessage());
            return false;
        }
    }

    public boolean emailExists(String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM whitelist_emails WHERE email = ?"
            );

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erreur vérification email : " + e.getMessage());
        }
        return false;
    }

}
