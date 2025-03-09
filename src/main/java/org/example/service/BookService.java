package org.example.service;

public interface BookService {
    public void addBook();

    public void displayBooks();

    public void deleteBook();

    public void incrementBookRating(String name);

    public void mostRatedBooks();

    public void addBookRating(int userId);
}
