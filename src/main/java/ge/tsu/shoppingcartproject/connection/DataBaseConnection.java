package ge.tsu.shoppingcartproject.connection;

import org.h2.jdbcx.JdbcDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            JdbcDataSource dataSource = new JdbcDataSource();
            try (InputStream input = new FileInputStream("C:\\Users\\Saboteur\\IdeaProjects\\ShoppingCartProject\\src\\main\\resources\\config.properties")) {
                Properties prop = new Properties();
                // load a properties file
                prop.load(input);

                // Connect to database
                dataSource.setURL(prop.getProperty("db.url"));
                dataSource.setUser(prop.getProperty("db.user"));
                dataSource.setPassword(prop.getProperty("db.password"));
                connection = dataSource.getConnection();
                System.out.println("connected");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
