package org.SQL;

import org.Conexion.Conectar;

import java.sql.*;
import java.util.Scanner;

public class tutorGrupo {

    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenu");
            System.out.println("1. Crear Tutor de grupo");
            System.out.println("2. Eliminar Tutor de grupo");
            System.out.println("3. Modificar Tutor de grupo");
            System.out.println("4. Buscar Tutor de grupo");
            System.out.println("5. Listar Tutores de grupo");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    agregarTutor(sc);
                    break;
                case 2:
                    eliminarTutor(sc);
                    break;
                case 3:
                    System.out.print("Introduce el nombre del tutor del grupo que desea modificar: ");
                    String nombre = sc.nextLine();
                    editarTutor(sc, nombre);
                    break;
                case 4:
                    System.out.print("Introduce el nombre del tutor del grupo que desea buscar: ");
                    String nombreBuscar = sc.nextLine();
                    Leer(nombreBuscar);
                    break;
                case 5:
                    listarTutores();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 6);
    }

    public static void Leer(String nombre) {
        String sql = "SELECT * FROM tutor_grupo WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Id del tutor del grupo " + rs.getString("Id_Tutor"));
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("Grupo: " + rs.getString("Grupo"));
                    System.out.println("Id del alumno: " + rs.getString("Id_Alumno"));
                } else {
                    System.out.println("No se encontró un tutor de grupo con el nombre: " + nombre);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void agregarTutor(Scanner sc) {
        System.out.print("Introduce el nombre del tutor del grupo: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el grupo del tutor: ");
        String grupo = sc.nextLine();
        String sql = "INSERT INTO tutor_grupo (Nombre, Grupo) VALUES (?, ?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, grupo);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Tutor agregado correctamente.");
            } else {
                System.out.println("Error al agregar el tutor del grupo.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void editarTutor(Scanner sc, String nombre) {
        System.out.print("Introduce el nuevo grupo del tutor: ");
        String nuevoGrupo = sc.nextLine();
        String sql = "UPDATE tutor_grupo SET Grupo = ? WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevoGrupo);
            stmt.setString(2, nombre);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Tutor de grupo actualizado correctamente.");
            } else {
                System.out.println("No se encontró un tutor de grupo con el nombre especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void eliminarTutor(Scanner sc) {
        System.out.print("Introduce el nombre del tutor de grupo que desea eliminar: ");
        String nombre = sc.nextLine();
        String sql = "DELETE FROM tutor_grupo WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Tutor de grupo eliminado correctamente.");
            } else {
                System.out.println("No se encontró un tutor de grupo con el nombre especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void listarTutores() {
        String sql = "SELECT * FROM tutor_grupo";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Listado de tutores de grupo:");
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("Nombre") + ", Grupo: " + rs.getString("Grupo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
