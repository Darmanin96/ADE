package sql;


import dad.conexion.*;

import java.sql.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;

public class SqlSentences {

    public static void create(int id,String nombre, String apellidos, String email, double salario, Date fechaNacimiento) {
        String insertQuery = "INSERT INTO empleados (id, nombre, apellido, email, salario, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = conexionDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, nombre);
                preparedStatement.setString(3, apellidos);
                preparedStatement.setString(4, email);
                preparedStatement.setDouble(5, salario);
                preparedStatement.setDate(6, (java.sql.Date) fechaNacimiento);

            int filasAfectadas = preparedStatement.executeUpdate();
                System.out.println("Empleado afectado: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al crear el empleado" + e.getMessage());
        }
    }
}
