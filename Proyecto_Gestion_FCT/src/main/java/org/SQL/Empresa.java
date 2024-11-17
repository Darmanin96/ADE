package org.SQL;

import org.Conexion.*;

import java.sql.*;
import java.util.*;

public class Empresa {

    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenu");
            System.out.println("1. Crear Empresa");
            System.out.println("2. Eliminar Empresa");
            System.out.println("3. Modificar Empresa");
            System.out.println("4. Buscar Empresa");
            System.out.println("5. Listar todas las Empresas");
            System.out.println("6. Volver al menu principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    agregarEmpresa(sc);
                    break;
                case 2:
                    eliminarEmpresa(sc);
                    break;
                case 3:
                    System.out.println("Introducir la empresa que desea editar");
                    String nombre = sc.next();
                    editarEmpresa(nombre);
                    break;
                case 4:
                    System.out.println("Introducir nombre de la empresa que desea buscar: ");
                    String nombre3 = sc.next();
                    Leer(nombre3);
                    break;
                case 5:
                    listarEmpresas();
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
        String sql = "SELECT * FROM empresas WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("Participa: " + rs.getBoolean("Participa"));
                    System.out.println("Plazas: " + rs.getInt("Plazas"));
                } else {
                    System.out.println("No se encontró una empresa con el nombre: " + nombre);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void agregarEmpresa(Scanner sc) {
        System.out.print("Introduce el nombre de la empresa: ");
        String nombre = sc.next();
        System.out.print("Participa? (true/false): ");
        boolean participa = sc.nextBoolean();
        System.out.print("Introduce las plazas: ");
        int plazas = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO empresas (Nombre, Participa, Plazas) VALUES (?, ?, ?)";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setBoolean(2, participa);
            stmt.setInt(3, plazas);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Empresa agregada correctamente.");
            } else {
                System.out.println("Error al agregar la empresa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void editarEmpresa(String nombre) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Desea cambiar la participación? (Si/No): ");
        String respuesta = sc.next();
        boolean participacion = respuesta.equalsIgnoreCase("Si");
        System.out.println("Introducir plazas: ");
        int plazas = sc.nextInt();
        String sql = "UPDATE empresas SET Participa = ?, Plazas = ? WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, participacion);
            stmt.setInt(2, plazas);
            stmt.setString(3, nombre);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empresa actualizada correctamente.");
            } else {
                System.out.println("No se encontró una empresa con ese nombre.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void eliminarEmpresa(Scanner sc) {
        System.out.print("Introduce el nombre de la empresa que desea eliminar: ");
        String nombre = sc.next();
        String sql = "DELETE FROM empresas WHERE Nombre = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Empresa eliminada correctamente.");
            } else {
                System.out.println("No se encontró una empresa con el nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void listarEmpresas() {
        String sql = "SELECT * FROM empresas";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Participa: " + rs.getBoolean("Participa"));
                System.out.println("Plazas: " + rs.getInt("Plazas"));
                System.out.println("------");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}

