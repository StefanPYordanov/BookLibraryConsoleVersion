package org.example.helper.messages;

public class CommandsMessages {
    public static final String LOGIN_COMMAND = "1";
    public static final String REGISTER_COMMAND = "2";
    public static final String EXIT_COMMAND = "3";
    public static final String DISPLAY_ALL_BOOKS_COMMAND = "4";
    public static final String ADD_BOOK_COMMAND = "5";
    public static final String RATE_BOOK_COMMAND = "6";
    public static final String DISPLAY_BIGGEST_RATED_BOOKS_COMMAND = "7";
    public static final String LOGOUT_COMMAND = "8";
    public static final String DELETE_BOOK_COMMAND = "9";
    public static final String DELETE_USER_COMMAND = "10";
    public static final String GIVE_ADMIN_ROLE_TO_USER_COMMAND = "11";

    private CommandsMessages (){
        throw new UnsupportedOperationException("Cannot instantiate this class !");
    }
}
