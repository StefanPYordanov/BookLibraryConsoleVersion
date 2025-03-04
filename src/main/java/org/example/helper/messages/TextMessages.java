package org.example.helper.messages;

import java.time.LocalDate;

public final class TextMessages { // TODO : Move different messages to other packages

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
    public static final String EMPTY_USER = "";
    public static final int MIN_VALUE_OF_POSITIVE_NUMBER = 1;
    public static int CURRENT_YEAR = LocalDate.now().getYear();
    public static final int MIN_LENGTH_REQUIREMENT_FOR_FIELD = 4;
    public static final String REGEX_FOR_VALID_EMAIL = "^\\S+@\\S+\\.\\S+$";
    public static final int RATING_BEFORE_SOMEONE_RATE_FOR_BOOK = 0;
    public static final String INITIAL_USER_ROLE_AFTER_REGISTER = "User";
    public static final int INCREMENT_LAST_USER_ID_BY_ONE = 1;



    private TextMessages (){
        throw new UnsupportedOperationException("Cannot instantiate this class !");
    }
}
