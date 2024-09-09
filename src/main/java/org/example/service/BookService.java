package org.example.service;

public interface BookService {

    public void addBook();
    public void displayBooks();
    public void deleteBook(int isbn);
    public void findBookByName(String name);
    public void addRating();

}
