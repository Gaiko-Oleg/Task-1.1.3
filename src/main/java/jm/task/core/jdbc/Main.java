package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Denis","Ginger",(byte) 30);
        userService.saveUser("Maxim","Boroda",(byte) 34);
        userService.saveUser("Oleg","Bignos",(byte)35);
        userService.saveUser("Sergey","Italica",(byte) 40);

        List<User> userList = userService.getAllUsers();
        System.out.println(userService.getAllUsers());

        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
//
//
//