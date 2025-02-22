package org.example.commands;

import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

public class RateBookCommand implements Command {
    BookServiceImpl bookServiceImpl = new BookServiceImpl();
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public RateBookCommand setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        return this;
    }

    @Override
    public void execute() {
        int userId = userServiceImpl.findUser(currentUser);
        bookServiceImpl.vote(userId);
    }
}
