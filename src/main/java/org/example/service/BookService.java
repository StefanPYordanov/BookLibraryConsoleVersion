package org.example.service;

public interface BookService {
    public void addBook();
    public void displayBooks();
    public void deleteBook(String name);
    public void findBookByName(String name);
    public void addRating();
    public boolean isBookExist(String name);
    public void mostRatedBooks();
}
