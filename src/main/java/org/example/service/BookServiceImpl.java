package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.helper.Validator;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookServiceImpl implements BookService{
    @Override
    public void addBook() { // -> Add book to DB
        Scanner scanner = new Scanner(System.in);
        BookEntity bookEntity = new BookEntity();
        Validator validator = new Validator();
    try{
    // Enter whole data for the book
    System.out.println("Please enter isbn:");
    int isbn = scanner.nextInt();
    while (!validator.isIsbnValid(isbn)) {
        isbn = scanner.nextInt();
    }
        bookEntity.setIsbn(isbn);

    System.out.println("Please enter title:");
    String s = scanner.nextLine();
    String bookName = scanner.nextLine();
    while (isBookExist(bookName) || validator.isFieldEmpty(bookName)){
        System.out.println("Book already exist, please try with other book!");
        bookName = scanner.nextLine();
    }
        bookEntity.setBookName(bookName);

    System.out.println("Please enter author:");
    String author = scanner.nextLine();
    while (validator.isFieldEmpty(author)){
        author = scanner.nextLine();
    }
        bookEntity.setAuthorName(author);

    System.out.println("Please enter genre:");
    String genre = scanner.nextLine();
    while(validator.isFieldEmpty(genre)){
        genre = scanner.nextLine();
    }
    bookEntity.setGenre(genre);

    System.out.println("Please enter the release year of the book");
    int releaseYear = scanner.nextInt();
    while (!validator.isIsbnValid(releaseYear)){
        releaseYear = scanner.nextInt();
    }
    bookEntity.setReleaseYear(releaseYear);

    System.out.println("Please enter book pages:");
    int pages = scanner.nextInt();
    while (!validator.isIsbnValid(pages)){
        pages = scanner.nextInt();
    }
    bookEntity.setPages(pages);
        // Rating is set to be 0 in initialization, he will be incremented after user vote
    bookEntity.setRating(0);

    Connection connection = ConnectionFactory.getConnection();
    Statement statement = connection.createStatement();

    String query = "insert into books values ('"+bookEntity.getIsbn()+"','"+bookEntity.getBookName()
            +"','"+bookEntity.getAuthorName()+"','"+bookEntity.getGenre()+"','"+bookEntity.getReleaseYear()
            +"','"+bookEntity.getPages()+"','"+bookEntity.getRating()+"')";
    statement.executeUpdate(query);

    }catch (Exception e){
    System.out.println(e);
    }
    }
    @Override
    public void displayBooks() { // -> Show list of all books in library
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from books";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println("*ISBN* : " + resultSet.getString(1)
                        + " *Title* : " + resultSet.getString(2)
                        + " *Author* : " + resultSet.getString(3)
                        + " *Genre* : " + resultSet.getString(4)
                        + " *Release Year* : " + resultSet.getString(5)
                        + " *Pages* : " + resultSet.getString(6)
                        +" *Rating* : " + resultSet.getString(7)); //-> TODO: Use String Builder and optimize printing
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void deleteBook(String name) { //-> Function for admins, to delete books
        Scanner scanner = new Scanner(System.in);
        while (!isBookExist(name)) {
            System.out.println("Please chose from existing library");
            name = scanner.nextLine();
        }
        try{
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement();

                String query = "delete from books where book_name='" + name + "'";
                statement.executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    public void findBookByName(String name){ //-> Finding book by name
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="select * from books where book_name='"+name+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println("*ISBN* : " + resultSet.getString(1)
                        + " *Title* : " + resultSet.getString(2)
                        + " *Author* : " + resultSet.getString(3)
                        + " *Genre* : " + resultSet.getString(4)
                        + " *Release Year* : " + resultSet.getString(5)
                        + " *Pages* : " + resultSet.getString(6)
                        +" *Rating* : " + resultSet.getString(7)); //-> TODO: Use String Builder and optimize printing
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addRating(){ // TODO: Hide rating when user vote for book, can vote for different books
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title to rate for book:");
        String name = scanner.nextLine();
        if (isBookExist(name)){
            try{
                Connection connection=ConnectionFactory.getConnection();
                Statement statement=connection.createStatement();

                String query="select * from books where book_name='"+name+"'";

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    String update = "update books set `rating` = '" + rating + "' where book_name ='" + name + "'";
                    statement.executeUpdate(update);
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public boolean isBookExist(String name) { //-> Check if book exist in DB
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query="select * from books where book_name='"+name+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public void mostRatedBooks() { // -> Show books in order from biggest rating to lowest
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from books order by rating desc";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println("*ISBN* : " + resultSet.getString(1)
                        + " *Title* : " + resultSet.getString(2)
                        + " *Author* : " + resultSet.getString(3)
                        + " *Genre* : " + resultSet.getString(4)
                        + " *Release Year* : " + resultSet.getString(5)
                        + " *Pages* : " + resultSet.getString(6)
                        +" *Rating* : " + resultSet.getString(7)); //-> TODO: Use String Builder and optimize printing
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
