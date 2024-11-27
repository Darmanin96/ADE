package Sql;

import conexion.*;

import java.sql.*;

public class Insert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestion_empleados";
        String user = "root";
        String password = "";

        String insertQuery = "INSERT INTO empleados (id, nombre, apellido, email, salario, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Valores para el nuevo empleado
            preparedStatement.setInt(1, 4);  // id
            preparedStatement.setString(2, "Laura");  // nombre
            preparedStatement.setString(3, "Martinez");  // apellido
            preparedStatement.setString(4, "laura.martinez@example.com");  // email
            preparedStatement.setBigDecimal(5, new java.math.BigDecimal("65000.00"));  // salario
            preparedStatement.setDate(6, java.sql.Date.valueOf("2023-04-01"));  // fecha de contratación

            // Ejecutar inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("Error en la inserción: " + e.getMessage());
        }
    }


}
