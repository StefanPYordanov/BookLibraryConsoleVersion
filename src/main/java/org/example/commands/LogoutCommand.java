package org.example.commands;

import org.example.logger.LoggerUtil;

import static org.example.helper.messages.TextMessages.EMPTY_USER;
import static org.example.model.dto.LoggedUserDto.*;

public class LogoutCommand implements Command{
    @Override
    public void execute() {
        LoggerUtil.logInfo(loggedUserUsername + " has logged out");
        loggedUserUsername = EMPTY_USER;
        loggedUserRole = EMPTY_USER;

    }
}
