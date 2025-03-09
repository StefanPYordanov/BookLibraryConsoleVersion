package org.example.commands;

import static org.example.model.dto.LoggedUserDto.*;

public class LogoutCommand implements Command{
    @Override
    public void execute() {
        loggedUserUsername = ""; //TODO : Magical Strings, describe values in text messages
        loggedUserRole = "";
    }
}
