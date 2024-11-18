package org.SQL;

import org.Conexion.Conectar;

import java.sql.*;
import java.util.Scanner;

public class Alumno {

    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenu");
            System.out.println("1. Crear Alumno");
            System.out.println("2. Eliminar Alumno");
            System.out.println("3. Modificar Alumno");
            System.out.println("4. Buscar Alumno");
            System.out.println("5. Listar todos los alumnos");
            System.out.println("6. Volver al menu principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    agregarAlumno(sc);
                    break;
                case 2:
                    eliminarAlumno(sc);
                    break;
                case 3:
                    System.out.println("Introducir alumno que desea editar");
                    String nombre = sc.next();
                    editarAlumno(nombre);
                    break;
                case 4:
                    System.out.println("Introducir nombre del alumno que desea buscar: ");
                    String nombre3 = sc.next();
                    Leer(nombre3);
                    break;
                case 5:

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
        String sql = "SELECT * FROM alumno WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("Apellidos: " + rs.getString("Apellidos"));
                    System.out.println("Tutor Empresa: " + rs.getString("Tutor_Empresa"));
                    System.out.println("Tutor Grupo: " + rs.getString("Tutor_Grupo"));
                    System.out.println("Materias Pendientes: " + rs.getBoolean("Pendiente"));
                } else {
                    System.out.println("No se encontró un alumno con el nombre: " + nombre);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }


    public static void agregarAlumno(Scanner sc) {
        System.out.print("Introduce el nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce los apellidos del alumno: ");
        String apellidos = sc.nextLine();
        System.out.print("Introduce el tutor de la empresa: ");
        String tutorEmpresa = sc.nextLine();
        System.out.print("Introduce el tutor del grupo: ");
        String tutorGrupo = sc.next();
        System.out.print("¿Tiene materias pendientes? (true/false): ");
        boolean pendiente = sc.nextBoolean();
        sc.nextLine();

        String sql = "INSERT INTO alumno (Nombre, Apellidos, Tutor_Empresa, Tutor_Grupo, Pendiente) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, tutorEmpresa);
            stmt.setString(4, tutorGrupo);
            stmt.setBoolean(5, pendiente);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Alumno agregado correctamente.");
            } else {
                System.out.println("Error al agregar el alumno.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void editarAlumno(String nombre) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca los nuevos datos del alumno:");
        System.out.print("Nuevo tutor de la empresa: ");
        String tutorEmpresa = sc.nextLine();
        System.out.print("Nuevo tutor de grupo: ");
        String tutorGrupo = sc.nextLine();
        String sql = "UPDATE alumno SET Tutor_Empresa = ?, Tutor_Grupo = ? WHERE Nombre = ?";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tutorEmpresa);
            stmt.setString(2, tutorGrupo);
            stmt.setString(3, nombre);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Alumno actualizado correctamente.");
            } else {
                System.out.println("No se encontró un alumno con el nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }



    public static void eliminarAlumno(Scanner sc) {
        System.out.print("Introduce el nombre del alumno que desea eliminar: ");
        String nombre = sc.nextLine();
        String sql = "DELETE FROM alumno WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Alumno eliminado correctamente.");
            } else {
                System.out.println("No se encontró a un alumno con el nombre especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
