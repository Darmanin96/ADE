package dad;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;


public class TomcatDataSource {

    // Instancia única del DataSource
    private static DataSource dataSource;

    // Configuración inicial del pool de conexiones
    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:mysql://localhost:3306/gestion_empleados"); // Cambia "empresa" por el nombre de tu base de datos
        poolProperties.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Cambia el driver si usas otra base de datos
        poolProperties.setUsername("root"); // Cambia "usuario" por tu usuario de base de datos
        poolProperties.setPassword(""); // Cambia "contraseña" por la contraseña de tu base de datos

        // Configuración del pool de conexiones
        poolProperties.setInitialSize(5); // Número inicial de conexiones creadas
        poolProperties.setMinIdle(5); // Número mínimo de conexiones inactivas en el pool
        poolProperties.setMaxIdle(10); // Número máximo de conexiones inactivas en el pool
        poolProperties.setMaxActive(20); // Número máximo de conexiones activas en el pool
        poolProperties.setMaxWait(10000); // Tiempo máximo de espera para obtener una conexión (en milisegundos)
        poolProperties.setTestOnBorrow(true); // Verifica la conexión al tomarla del pool
        poolProperties.setValidationQuery("SELECT 1"); // Consulta para validar que la conexión está activa

        dataSource = new DataSource();
        dataSource.setPoolProperties(poolProperties);
    }

    // Método para obtener una conexión del pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar el DataSource
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}