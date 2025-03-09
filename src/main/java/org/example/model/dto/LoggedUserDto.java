package org.example.model.dto;

public class LoggedUserDto {
    public static String loggedUserUsername = "";
    public static String loggedUserRole = "";

    private LoggedUserDto (){
        throw new UnsupportedOperationException("Cannot instantiate this class !");
    }
}
