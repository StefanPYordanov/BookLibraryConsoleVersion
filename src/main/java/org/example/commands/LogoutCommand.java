package org.example.commands;

import org.example.logger.LoggerUtil;

import static org.example.helper.messages.UserMessages.EMPTY_USER;
import static org.example.model.dto.LoggedUserDto.*;

public class LogoutCommand implements Command{
    @Override
    public void execute() {
        LoggerUtil.logInfo(loggedUserUsername + " has logged out successfully");
        loggedUserUsername = EMPTY_USER;
        loggedUserRole = EMPTY_USER;

    }
}
