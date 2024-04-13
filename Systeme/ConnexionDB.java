package Systeme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gestion_bibliotheque";
        String user = "root";
        String password = "";

        // Charger le pilote JDBC MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Établir la connexion
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}

