package org.example.commands;

import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

import static org.example.model.LoggedUserDto.*;

public class RateBookCommand implements Command {
    BookServiceImpl bookServiceImpl = new BookServiceImpl();
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    public void execute() {
        int userId = userServiceImpl.findUser(loggedUserUsername);
        bookServiceImpl.addBookRating(userId);
    } //TODO : give more info ??
}
