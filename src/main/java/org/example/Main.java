package org.example;

import org.example.helper.Menu;
import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Menu menu = new Menu();

        menu.nonUserMenu();
        String choice = scanner.nextLine();
        while (!choice.equals("9")) {
            String currentUser = "";

            switch (choice) {
                case "1" -> {
                    System.out.println("Please Enter your username:");
                    String username = scanner.nextLine();
                    System.out.println("Please Enter your password");
                    String password = scanner.nextLine();
                    while (!userServiceImpl.login(username, password)) {
                        System.out.println("Please Enter your username:");
                        username = scanner.nextLine();
                        System.out.println("Please Enter your password");
                        password = scanner.nextLine();
                    }
                    currentUser = username;
                    if (userServiceImpl.isAdmin(username)) {
                        menu.adminMenu();
                        choice = scanner.nextLine();
                        while (!currentUser.equals("")) {
                            switch (choice) {
                                case "1" -> bookServiceImpl.addBook();
                                case "2" -> bookServiceImpl.displayBooks();
                                case "3" -> {
                                    int userId = userServiceImpl.findUser(currentUser);
                                    bookServiceImpl.vote(userId);
                                }
                                case "4" -> bookServiceImpl.mostRatedBooks();
                                case "5" -> {
                                    currentUser = "";
                                    menu.nonUserMenu();
                                }
                                case "6" -> {
                                    System.out.println("Please enter book name to delete book");
                                    String bookToDelete = scanner.nextLine();
                                    bookServiceImpl.deleteBook(bookToDelete);
                                }
                                case "7" -> {
                                    userServiceImpl.displayUsers();
                                    System.out.println("Please enter the id of the user you want to delete:");
                                    int idToDeleteUser = scanner.nextInt();
                                    userServiceImpl.deleteUser(idToDeleteUser);
                                    choice = scanner.nextLine();
                                }
                                case "8" -> {
                                    userServiceImpl.displayUsers();
                                    System.out.println("Please enter the id of the user you want to become admin:");
                                    int idToBecomeAdmin = scanner.nextInt();
                                    userServiceImpl.giveRole(idToBecomeAdmin);
                                    choice = scanner.nextLine();
                                }
                                case "9" -> System.exit(0);
                                default -> System.out.println("Invalid command, please select from existing one");
                            }
                            if (!currentUser.equals("")) {
                                menu.adminMenu();
                                choice = scanner.nextLine();
                            }
                        }
                    } else {
                        menu.userMenu();
                        choice = scanner.nextLine();
                        while (!currentUser.equals("")) {
                            switch (choice) {
                                case "1" -> bookServiceImpl.addBook();
                                case "2" -> bookServiceImpl.displayBooks();
                                case "3" -> {
                                    int userId = userServiceImpl.findUser(currentUser);
                                    bookServiceImpl.vote(userId);
                                }
                                case "4" -> bookServiceImpl.mostRatedBooks();
                                case "5" -> {
                                    currentUser = "";
                                    menu.nonUserMenu();
                                }
                                case "9" -> System.exit(0);
                                default -> System.out.println("Invalid command, please select from existing one");
                            }
                            if (!currentUser.equals("")) {
                                menu.userMenu();
                                choice = scanner.nextLine();
                            }
                        }
                    }
                }
                case "2" -> {
                    String[] tokens = userServiceImpl.register().split(" ");
                    userServiceImpl.login(tokens[0], tokens[1]);
                    currentUser = tokens[0] + " " + tokens[1];
                    menu.userMenu();
                    choice = scanner.nextLine();
                    while (!currentUser.equals("")) {
                        switch (choice) {
                            case "1" -> bookServiceImpl.addBook();
                            case "2" -> bookServiceImpl.displayBooks();
                            case "3" -> {
                                int userId = userServiceImpl.findUser(currentUser);
                                bookServiceImpl.vote(userId);
                            }
                            case "4" -> bookServiceImpl.mostRatedBooks();
                            case "5" -> {
                                currentUser = "";
                                menu.nonUserMenu();
                            }
                            case "9" -> System.exit(0);
                            default -> System.out.println("Invalid command, please select from existing one");
                        }
                        if (!currentUser.equals("")) {
                            menu.userMenu();
                            choice = scanner.nextLine();
                        }
                    }
                }
                case "3" -> {
                    bookServiceImpl.displayBooks();
                    menu.nonUserMenu();
                }
                case "9" -> System.exit(0);
                default -> System.out.println("Invalid command, please select from existing one");
            }
            choice = scanner.nextLine();
        }
    }
}