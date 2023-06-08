package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS user1 " +
                    "(id SERIAL PRIMARY KEY, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age INTEGER)";
            statement.executeUpdate(sql);
            System.out.println("Создание таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS user1";
            statement.executeUpdate(sql);
            System.out.println("Удаление таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO user1(name,lastName,age) VALUES ('" + name+"','" + lastName + "'," + age+")";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            System.out.println("User " + name + " " + lastName + " ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM user1 WHERE id=?";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Удаление User из таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user1";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(id, name, lastName, age);
                listUser.add(user);
            }

            System.out.println(listUser);
            System.out.println("Получение всех User(ов) из таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUser;
    }


    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "TRUNCATE TABLE user1";
            statement.executeUpdate(sql);
            System.out.println("Очистка содержания таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
