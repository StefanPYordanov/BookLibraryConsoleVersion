package org.example.commands;

import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

import static org.example.model.dto.LoggedUserDto.*;

public class RateBookCommand implements Command {
    BookServiceImpl bookServiceImpl = new BookServiceImpl();
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    public void execute() {
        int userId = userServiceImpl.findUser(loggedUserUsername);
        bookServiceImpl.addBookRating(userId);
    } //TODO : describe this operations to be more clear what you want to achieve
}
