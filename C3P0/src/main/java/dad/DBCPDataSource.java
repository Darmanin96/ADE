package dad;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCPDataSource {

    // Instancia única del DataSource
    private static BasicDataSource dataSource;

    // Configuración inicial del pool de conexiones
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/gestion_empleados"); // Cambia "gestion_empleados" por tu base de datos
        dataSource.setUsername("root"); // Cambia "root" por tu usuario
        dataSource.setPassword(""); // Cambia "" por tu contraseña

        // Configuración del pool de conexiones
        dataSource.setMinIdle(5); // Número mínimo de conexiones inactivas en el pool
        dataSource.setMaxIdle(10); // Número máximo de conexiones inactivas en el pool
        dataSource.setMaxTotal(20); // Número máximo de conexiones activas en el pool
        dataSource.setMaxWaitMillis(10000); // Tiempo de espera máximo para obtener una conexión (en milisegundos)
    }

    // Método para obtener una conexión del pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar el DataSource
    public static void close() {
        try {
            if (dataSource != null) {
                dataSource.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBCPDataSource.class.getName()).log(Level.SEVERE, "Error al cerrar el DataSource", e);
        }
    }
}

class MainApplication {
    public static void main(String[] args) {
        // Crear un pool de threads para simular conexiones concurrentes
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Ejecutar varias tareas que solicitan conexiones del pool
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                try (Connection connection = DBCPDataSource.getConnection()) {
                    String query = "SELECT * FROM empleados LIMIT 1"; // Cambia "empleados" por el nombre real de tu tabla
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                         ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            System.out.println("Resultado: " + resultSet.getString("columna_ejemplo")); // Cambia "columna_ejemplo" por el nombre real de tu columna
                        }
                    }
                } catch (SQLException e) {
                    Logger.getLogger(MainApplication.class.getName()).log(Level.SEVERE, "Error al ejecutar la consulta", e);
                }
            });
        }

        // Cierra el servicio de ejecución después de que todas las tareas hayan terminado
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // Cerrar el pool de conexiones al finalizar el programa
        Runtime.getRuntime().addShutdownHook(new Thread(DBCPDataSource::close));
    }
}
