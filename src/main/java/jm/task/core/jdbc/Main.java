package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Denis","Ginger",(byte) 30);
        userService.saveUser("Maxim","Boroda",(byte) 34);
        userService.saveUser("Oleg","Bignos",(byte)35);
        userService.saveUser("Sergey","Italica",(byte) 40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("John", "Doe", (byte) 30);
        userDao.saveUser("Jane", "Doe", (byte) 32);

        List<User> users = userDao.getAllUsers();
        users.forEach(user -> System.out.println(user.getName() + " " + user.getLastName()));

        userDao.cleanUsersTable();
        userDao.dropUsersTable();// реализуйте алгоритм здесь
    }
}

