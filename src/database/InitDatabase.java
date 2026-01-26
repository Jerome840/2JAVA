package database;

import java.sql.*;

public class InitDatabase {

    public static void main(String[] args) {

        try (Connection conn = ConnexionBDD.getConnexion();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS javadatabase");
            stmt.executeUpdate("USE javadatabase");

            // Création table "whitelist
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS whitelist_emails (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    email VARCHAR (255) UNIQUE NOT NULL
                )
                """);

            // Création table Stores
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS stores (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nom VARCHAR (255) UNIQUE NOT NULL,
                    ville VARCHAR(255) NOT NULL
                )
                """);

            // Création table utilisaters
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    email VARCHAR (255) UNIQUE NOT NULL,
                    pseudo VARCHAR (255) NOT NULL,
                    mot_de_passe VARCHAR (255) NOT NULL,
                    role ENUM('admin', 'user') NOT NULL,
                    store_id INT NULL,
                    FOREIGN KEY (store_id) REFERENCES stores(id)
                )
                """);

            // Création table Inventory
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS inventory (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    store_id INT NOT NULL,
                    FOREIGN KEY (store_id) REFERENCES stores(id)
                )
                """);

            // Création table articles
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS articles (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nom VARCHAR (255) NOT NULL,
                    reference VARCHAR(255) UNIQUE NOT NULL,
                    prix DECIMAL(10,2) NOT NULL
                )
                """);

            // Création table InventaireArticle
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS inventoryarticles (
                    inventory_id INT NOT NULL,
                    article_id INT NOT NULL,
                    quantite INT NOT NULL CHECK (quantite >= 0),
                    PRIMARY KEY (inventory_id, article_id),
                    FOREIGN KEY (inventory_id) REFERENCES inventory(id),
                    FOREIGN KEY (article_id) REFERENCES articles(id)
                )
                """);

            System.out.println("Base de donnée et tables crées avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'initialisation" + e.getMessage());
        }
    }
}
