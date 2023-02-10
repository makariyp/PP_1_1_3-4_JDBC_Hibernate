package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ));";
            statement.executeUpdate(sql);
            System.out.println("Таблица USERS успешно создана!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE USERS;";
            statement.executeUpdate(sql);
            System.out.println("Таблица USERS успешно удалена!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "INSERT USERS(name, lastname, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + "); ";
            statement.executeUpdate(sql);
            System.out.println("Пользователь " + name + " успешно добавлен.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM USERS WHERE id = " + id + ";";
            statement.executeUpdate(sql);
            System.out.println("Пользователь " + id + " успешно удален.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM USERS;";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);

                result.add(user);
            }
            System.out.println("Пользователи успешно получены.");
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE USERS;";
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очистилась.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
