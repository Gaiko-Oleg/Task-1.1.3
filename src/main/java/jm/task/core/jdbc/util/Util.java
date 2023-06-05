package jm.task.core.jdbc.util;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "password");

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            System.out.println("Connect exception"+e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}