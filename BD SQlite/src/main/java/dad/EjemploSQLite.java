package dad;

import java.sql.*;

public class EjemploSQLite {
    public static void main(String[] args) {
        // URL a la base de datos SQLite
        String url = "jdbc:sqlite:ejemplo.db"; // La base de datos será 'ejemplo.db'

        // Establecer la conexión
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("¡Conectado a la base de datos!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
