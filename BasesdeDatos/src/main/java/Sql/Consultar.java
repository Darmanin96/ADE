package Sql;

import java.sql.*;
import conexion.conexionDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultar {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestion_empleados";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {


            String query = "SELECT * FROM empleados";
            ResultSet resultSet = statement.executeQuery(query);

            // Procesar los resultados
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Apellido: " + resultSet.getString("apellido"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Salario: " + resultSet.getBigDecimal("salario"));
                System.out.println("Fecha de Contratación: " + resultSet.getDate("fecha_contratacion"));
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta: " + e.getMessage());
        }
    }
}

