package dad;

import java.sql.*;
import java.util.*;

public class Crud {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menu");
            System.out.println("1. Crear");
            System.out.println("2. Conectar ");
            System.out.println("3. Insertar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Listar");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    Crear();
                    break;
                case 2:
                    Conectar();
                    break;
                case 3:
                    Insertar();
                    break;
                case 4:
                    Actualizar();
                    break;
                case 5:
                    Eliminar();
                    break;
                case 6:
                    Listar();  // Aquí agregamos la opción para listar
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 7);
    }

    public static void Crear() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "edad INTEGER" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'usuarios' creada con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public static void Conectar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println("id: " + id + " nombre: " + nombre + " edad: " + edad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Insertar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "INSERT INTO usuarios (nombre, edad) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Gloria");
            pstmt.setInt(2, 48);
            pstmt.executeUpdate();
            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Consultar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println("id: " + id + " nombre: " + nombre + " edad: " + edad);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los datos: " + e.getMessage());
        }
    }

    public static void Actualizar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "UPDATE usuarios SET nombre = ?, edad = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Juan");
            pstmt.setInt(2, 35);
            pstmt.setInt(3, 1);  // Aquí puedes poner el id de la persona que quieres actualizar
            int filasActualizadas = pstmt.executeUpdate();
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Eliminar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "DELETE FROM usuarios WHERE nombre = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Juan");
            int filasEliminadas = pstmt.executeUpdate();
            System.out.println("Filas eliminadas: " + filasEliminadas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método listar para mostrar todos los usuarios
    public static void Listar() {
        String url = "jdbc:sqlite:ejemplo.db";
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Listado de usuarios:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println("id: " + id + " nombre: " + nombre + " edad: " + edad);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios: " + e.getMessage());
        }
    }
}
