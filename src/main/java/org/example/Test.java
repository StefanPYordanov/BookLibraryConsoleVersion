package org.example;

import org.example.helper.Menu;
import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Menu menu = new Menu();

    userServiceImpl.register();
    }
}
