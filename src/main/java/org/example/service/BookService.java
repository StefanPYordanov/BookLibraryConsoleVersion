package org.example.service;

public interface BookService {
    public void addBook();
    public void displayBooks();
    public void deleteBook(String name);
    public void addRating(String name);
    public boolean isBookExist(String name);
    public void mostRatedBooks();
    public void vote(int userId);
    public void countVote(int userId, String title);
}
