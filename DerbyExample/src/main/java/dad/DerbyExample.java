package dad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyExample {
    public static void main(String[] args) {
        // URL para la base de datos Derby embebida
        String dbURL = "jdbc:derby:miBaseDeDatos;create=true";

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            if (conn != null) {
                System.out.println("Conectado a la base de datos Derby embebida.");

                // Crear una tabla como ejemplo
                String createTableSQL = "CREATE TABLE ejemplo (id INT PRIMARY KEY, nombre VARCHAR(50))";
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'ejemplo' creada con éxito.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}