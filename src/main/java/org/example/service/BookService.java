package org.example.service;

public interface BookService {
    public void addBook();

    public void displayBooks();

    public void deleteBook();

    public void addRating(String name);

    public void mostRatedBooks();

    public void vote(int userId);
}
