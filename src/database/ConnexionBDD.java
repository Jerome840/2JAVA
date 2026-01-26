package database;

import java.sql.*;

public class ConnexionBDD {
    private static final String url = "jdbc:mysql://localhost:3306/javadatabase?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "rootpass";

    public static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}