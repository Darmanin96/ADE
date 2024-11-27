package dad.Conexion.Crud;

import java.sql.*;
import java.util.*;

public class Cursos {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("Seleccionar que base de datos desea trabajar");
                System.out.println("1. Insertar curso");
                System.out.println("2. Editar curso");
                System.out.println("3. Eliminar curso");
                System.out.println("4. Listar cursos");
                System.out.println("5. Mostrar curso");
                System.out.println("6. Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                        case 4:
                            ListarCurso();
                            break;
                            case 5:

                                break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }while (opcion != 6);
    }




    public static void ListarCurso(){
        String url = "jdbc:mysql://localhost:3306/gestion_universidad";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {


            String query = "SELECT * FROM cursos";
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nombre: " + resultSet.getString("Nombre del curso"));
                System.out.println("Apellido: " + resultSet.getString("Descripcion"));
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta: " + e.getMessage());
        }
    }


    public static void EditarCurso(){
        String url = "jdbc:mysql://localhost:3306/gestion_universidad";
        String user = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()){
            String query = "SELECT * FROM cursos";



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

