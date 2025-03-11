package org.example.commands;

import static org.example.helper.messages.TextMessages.EMPTY_USER;
import static org.example.model.dto.LoggedUserDto.*;

public class LogoutCommand implements Command{
    @Override
    public void execute() {
        loggedUserUsername = EMPTY_USER;
        loggedUserRole = EMPTY_USER;
    }
}
