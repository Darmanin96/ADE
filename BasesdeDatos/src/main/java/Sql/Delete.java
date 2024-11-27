package Sql;

import java.sql.*;

public class Delete {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/gestion_empleados";
            String user = "root";
            String password = "";
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement()) {
                String sql = "DELETE FROM empleados WHERE empleados.nombre=\"Laura\"; ";
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println("Filas afectadas: " + rowsAffected);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}
