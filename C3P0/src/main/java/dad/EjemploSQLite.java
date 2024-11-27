package dad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploSQLite {
    public static void main(String[] args) {
        // URL a la base de datos SQLite
        String url = "jdbc:sqlite:ejemplo.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("¡Conectado a la base de datos!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
