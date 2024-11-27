package Sql;

import java.sql.*;

public class Modificar {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestion_empleados";
        String user = "root";
        String password = "";

        // Consulta SQL para actualizar un empleado
        String updateQuery = "UPDATE empleados SET nombre = ?, apellido = ?, email = ?, salario = ?, fecha_contratacion = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {


            preparedStatement.setString(1, "Cambio Laura");
            preparedStatement.setString(2, "Cambio Martinez");
            preparedStatement.setString(3, "Cambio laura.martinez@example.com");
            preparedStatement.setBigDecimal(4, new java.math.BigDecimal("75000.00"));        preparedStatement.setDate(5, java.sql.Date.valueOf("2023-04-01"));  // nueva fecha de contratación
            preparedStatement.setInt(6, 4);


            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("Error en la actualización: " + e.getMessage());
        }
    }

}

