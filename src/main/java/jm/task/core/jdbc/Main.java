package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

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
        userService.dropUsersTable();// реализуйте алгоритм здесь
    }
}

