package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "password";
    Connection connection;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }// реализуйте настройку соеденения с БД
}
