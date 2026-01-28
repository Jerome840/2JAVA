package dao;

import java.sql.*;

import database.ConnexionBDD;
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO {
    public User login(String email, String password) {

        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ?"
            );
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hash = rs.getString("password");

                if (BCrypt.checkpw(password, hash)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("pseudo"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getInt("store_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'initialisation" + e.getMessage());
        }
        return null;
    }

    public boolean addUser(User user) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (email, pseudo, password, role, store_id) VALUES (?, ?, ?, ?, ?)"
            );

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPseudo());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            //ps.setInt(5, user.getStoreId());
            if (user.getStoreId() ==null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, user.getStoreId());
            }

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur ajout utilisateur : " + e.getMessage());
            return false;
        }
    }

    public boolean emailExists(String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE email = ?"
            );

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erreur vérification email :");
        }
        return false;
    }

    public boolean deleteUserByEmail(String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM users WHERE email = ?"
            );
            ps.setString(1, email);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur suppression utilisateur : " + e.getMessage());
            return false;
        }
    }

    public User getUserByEmail (String email) {
        try (Connection conn = ConnexionBDD.getConnexion()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ?"
            );
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("pseudo"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getInt("store_id")
                );
            }
            }catch (SQLException e) {
            System.out.println("Erreur getUserByEmail : " + e.getMessage());

        }return null;
    }

    public boolean updateUser (User user) {
        try (Connection conn = ConnexionBDD.getConnexion()) {

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE users SET pseudo = ?, roel = ?, store_id = ? WHERE email = ?"
            );

            ps.setString(1, user.getPseudo());
            ps.setString(2, user.getRole());
            ps.setInt(3, user.getStoreId());
            ps.setString(4, user.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erreur updateUser : " + e.getMessage());
            return false;
        }

    }
}
