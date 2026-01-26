package database;

import java.sql.*;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            Connection conn = ConnexionBDD.getConnexion();
            System.out.println("connexion réussie ! ");
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
