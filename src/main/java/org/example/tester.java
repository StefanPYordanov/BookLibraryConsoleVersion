package org.example;

import org.example.helper.Menu;
import org.example.helper.Validator;
import org.example.model.entity.UserEntity;
import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        UserEntity userEntity = new UserEntity();
        Menu menu = new Menu();
        Validator validator = new Validator();

        menu.nonUserMenu();
        String choice = scanner.nextLine();
        while (!choice.equals("9") ) {


            String currentUser = "";
            switch (choice) {


                case "1":
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
                    if (userServiceImpl.isAdmin(username)){
                        menu.adminMenu();
                        choice = scanner.nextLine();
                        while (!choice.equals("9") && !currentUser.equals("")){
                        switch (choice){
                            case "1":
                                bookServiceImpl.addBook();
                                break;
                            case "2":
                                bookServiceImpl.displayBooks();
                                break;
                            case "3":
                                bookServiceImpl.addRating();
                                System.out.println("Thank you for your vote !");
                                break;
                            case "4":
                                bookServiceImpl.mostRatedBooks();
                                break;
                            case "5":
                                currentUser = "";
                                menu.nonUserMenu();
                                break;
                            case"6":
                                System.out.println("Please enter book name to delete book");
                                String bookToDelete = scanner.nextLine();
                                bookServiceImpl.deleteBook(bookToDelete);
                                break;
                            case "7":
                                userServiceImpl.displayUsers();
                                System.out.println("Please enter the id of the user you want to delete:");
                                int idToDeleteUser = scanner.nextInt();
                                userServiceImpl.deleteUser(idToDeleteUser);
                                break;
                            case"8":
                                userServiceImpl.displayUsers();
                                System.out.println("Please enter the id of the user you want to become admin:");
                                int idToBecomeAdmin = scanner.nextInt();
                                userServiceImpl.giveRole(idToBecomeAdmin);
                                break;
                            case "9":
                                System.exit(0);
                            default:
                                System.out.println("Invalid command, please select from existing one");
                        }
                        if (!currentUser.equals("")){
                            menu.adminMenu();
                            choice = scanner.nextLine();
                        }
                        }


                    }else{
                        menu.userMenu();
                        choice = scanner.nextLine();
                        while (!choice.equals("9") && !currentUser.equals("")){
                            switch (choice){
                                case "1":
                                    bookServiceImpl.addBook();
                                    break;
                                case "2":
                                    bookServiceImpl.displayBooks();
                                    break;
                                case "3":
                                    bookServiceImpl.addRating();
                                    System.out.println("Thank you for your vote !");
                                    break;
                                case "4":
                                    bookServiceImpl.mostRatedBooks();
                                    break;
                                case "5":
                                    currentUser = "";
                                    menu.nonUserMenu();
                                    break;
                                case "9":
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Invalid command, please select from existing one");
                            }
                            if (!currentUser.equals("")){
                                menu.userMenu();
                                choice = scanner.nextLine();
                            }
                        }


                    }
                    break;



                case "2":
                    String[] tokens = userServiceImpl.register().split(" ");
                    userServiceImpl.login(tokens[0],tokens[1]);
                    currentUser = tokens[0] + " " + tokens[1];


                    menu.userMenu();
                    choice = scanner.nextLine();
                    while (!choice.equals("9") && !currentUser.equals("")){
                        switch (choice){
                            case "1":
                                bookServiceImpl.addBook();
                                break;
                            case "2":
                                bookServiceImpl.displayBooks();
                                break;
                            case "3":
                                bookServiceImpl.addRating();
                                System.out.println("Thank you for your vote !");
                                break;
                            case "4":
                                bookServiceImpl.mostRatedBooks();
                                break;
                            case "5":
                                currentUser = "";
                                menu.nonUserMenu();
                                break;
                            case "9":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid command, please select from existing one");
                        }
                        if (!currentUser.equals("")){
                            menu.userMenu();
                            choice = scanner.nextLine();
                        }
                    }

                    break;



                case "3":
                    bookServiceImpl.displayBooks();
                    menu.nonUserMenu();
                    break;



                case "9":
                    System.exit(0);



                default:
                    System.out.println("Invalid command, please select from existing one");
            }
            choice = scanner.nextLine();
        }
    }
}
