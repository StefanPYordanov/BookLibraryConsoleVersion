package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.helper.Validator;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    String query = "INSERT INTO books VALUES ('"+bookEntity.getIsbn()+"','"+bookEntity.getBookName()
            +"','"+bookEntity.getAuthorName()+"','"+bookEntity.getGenre()+"','"+bookEntity.getReleaseYear()
            +"','"+bookEntity.getPages()+"','"+bookEntity.getRating()+"')";
    statement.executeUpdate(query);
        System.out.println(bookEntity.getBookName() + " has been added to library!\n");

    }catch (Exception e){
    System.out.println(e);
    }
    }
    @Override
    public void displayBooks() { // -> Show list of all books in library
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM books";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ISBN* : " + resultSet.getString(1)
                        + " *Title* : " + resultSet.getString(2)
                        + " *Author* : " + resultSet.getString(3)
                        + " *Genre* : " + resultSet.getString(4)
                        + " *Release Year* : " + resultSet.getString(5)
                        + " *Pages* : " + resultSet.getString(6)
                        +" *Rating* : " + resultSet.getString(7));
                System.out.println(builder.toString());
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

                String query = "DELETE FROM books WHERE book_name='" + name + "'";
                statement.executeUpdate(query);
            System.out.println(name + " has been removed from library!\n");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    public void addRating(String name){
        if (isBookExist(name)){
            try{
                Connection connection=ConnectionFactory.getConnection();
                Statement statement=connection.createStatement();

                String query="SELECT * FROM books WHERE book_name='"+name+"'";

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    String update = "UPDATE books SET `rating` = '" + rating + "' WHERE book_name ='" + name + "'";
                    statement.executeUpdate(update);
                    System.out.println("Thank you for your vote !\n");
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }else{
            System.out.println("Book don't exist!");
        }
    }
    public boolean isBookExist(String name) { //-> Check if book exist in DB
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query="SELECT * FROM books WHERE book_name='"+name+"'";

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

            String query = "SELECT * FROM books ORDER BY rating DESC";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ISBN* : " + resultSet.getString(1)
                        + " *Title* : " + resultSet.getString(2)
                        + " *Author* : " + resultSet.getString(3)
                        + " *Genre* : " + resultSet.getString(4)
                        + " *Release Year* : " + resultSet.getString(5)
                        + " *Pages* : " + resultSet.getString(6)
                        +" *Rating* : " + resultSet.getString(7));
                System.out.println(builder.toString());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void vote(int userId) {
        List<String> listOfTitles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter title to vote for:");
        String title = scanner.nextLine();
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT title FROM ratings WHERE user_id = '"+ userId +"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                //resultSet return list of books for user id
                if (resultSet.getString(1).equals(title)){
                    System.out.println("You already voted for this book!");
                    listOfTitles.add(resultSet.getString(1));
                    break;
                }else{
                    listOfTitles.add(resultSet.getString(1));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        if (!listOfTitles.contains(title)) {
            addRating(title);
            if (isBookExist(title)) {
                countVote(userId, title);
            }
        }
    }
    public void countVote(int userId, String title){
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "INSERT INTO ratings VALUES ('"+ userId +"','"+title+"')";
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
