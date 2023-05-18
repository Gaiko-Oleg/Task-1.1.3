package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    Util util = new Util();

    @Override
    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS user1 (id SERIAL PRIMARY KEY, name VARCHAR(20), lastName VARCHAR(20), age INTEGER)";
            statement.executeUpdate(sql);
            System.out.println("Создание таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            String sql = "DROP TABLE IF EXISTS user1"; // Обновленный SQL-запрос
            statement.executeUpdate(sql);
            System.out.println("Удаление таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user1 (name, lastName, age) VALUES( ?, ?, ?)";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Добавление User(ов) в таблицу");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM user1 WHERE id=?";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Удаление User из таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM user1";
        try (Statement statement = util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
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
        try (Statement statement = util.getConnection().createStatement()) {
            String sql = "TRUNCATE TABLE user1";
            statement.executeUpdate(sql);
            System.out.println("Очистка содержания таблицы");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
