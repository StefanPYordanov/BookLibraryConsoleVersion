package org.example.commands;

import org.example.service.BookServiceImpl;
public class AddBookCommand implements Command {
    BookServiceImpl bookServiceImpl = new BookServiceImpl();
    @Override
    public void execute() {
        bookServiceImpl.addBook();
    }
}
