package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.helper.Validator;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService{
    Scanner scanner = new Scanner(System.in);
    Connection connection = ConnectionFactory.getConnection();
    Validator validator = new Validator();
    @Override
    public void addBook() { // -> Add book to DB
        BookEntity bookEntity = new BookEntity();
    try{
    // Enter whole data for the book
    System.out.println("Please enter isbn:");
    int isbn = scanner.nextInt();
    while (!validator.isNumberValid(isbn)) {
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
    while (!validator.isNumberValid(releaseYear)){
        releaseYear = scanner.nextInt();
    }
    bookEntity.setReleaseYear(releaseYear);

    System.out.println("Please enter book pages:");
    int pages = scanner.nextInt();
    while (!validator.isNumberValid(pages)){
        pages = scanner.nextInt();
    }
    bookEntity.setPages(pages);
        // Rating is set to be 0 in initialization, he will be incremented after user vote
    bookEntity.setRating(0);

    String query = "INSERT INTO books (isbn, book_name, author_name, genre, release_year, pages, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1,bookEntity.getIsbn());
    statement.setString(2, bookEntity.getBookName());
    statement.setString(3, bookEntity.getAuthorName());
    statement.setString(4, bookEntity.getGenre());
    statement.setInt(5, bookEntity.getReleaseYear());
    statement.setInt(6, bookEntity.getPages());
    statement.setInt(7, bookEntity.getRating());
    statement.executeUpdate();

        System.out.println(bookEntity.getBookName() + " has been added to library!\n");

    }catch (Exception e){
        System.out.println("Can't add book!!!");
    }
    }
    @Override
    public void displayBooks() { // -> Show list of all books in library
        try{
            String query = "SELECT * FROM books";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

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
            System.out.println("Can't Display books!!!");
        }
    }
    @Override
    public void deleteBook(String name) { //-> Function for admins, to delete books
        while (!isBookExist(name)) {
            System.out.println("Please chose from existing library");
            name = scanner.nextLine();
        }
        try{
                String query = "DELETE FROM books WHERE book_name='" + name + "'";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();
            System.out.println(name + " has been removed from library!\n");
            } catch (Exception e) {
            System.out.println("Can't Delete book!!!");
            }
        }
    public void addRating(String name){ // -> Add rating to book in DB
        if (isBookExist(name)){
            try{
                String query="SELECT * FROM books WHERE book_name='"+name+"'";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    String update = "UPDATE books SET `rating` = '" + rating + "' WHERE book_name ='" + name + "'";
                    statement.executeUpdate(update);
                    System.out.println("Thank you for your vote !\n");
                }
            }
            catch (Exception e) {
                System.out.println("Can't add rating!!!");
            }
        }else{
            System.out.println("Book don't exist!");
        }
    }
    public boolean isBookExist(String name) { //-> Check if book exist in DB
        try {
            String query="SELECT * FROM books WHERE book_name='"+name+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println("No Such book!!!");
            return false;
        }
    }
    @Override
    public void mostRatedBooks() { // -> Show books in order from the biggest rating to the lowest
        try{
            String query = "SELECT * FROM books ORDER BY rating DESC";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

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
            System.out.println("Can't show most rated book!!!");
        }
    }
    public void vote(int userId) { // -> Check if user is already voted, add vote is not
        List<String> listOfTitles = new ArrayList<>();
        System.out.println("Please enter title to vote for:");
        String title = scanner.nextLine();
        try{
            String query = "SELECT title FROM ratings WHERE user_id = '"+ userId +"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

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
            System.out.println("Can't Vote!!!");
        }
        if (!listOfTitles.contains(title)) {
            addRating(title);
            if (isBookExist(title)) {
                countVote(userId, title);
            }
        }
    }
    public void countVote(int userId, String title){ // -> Add data for vote in DB
        try{
            String query = "INSERT INTO ratings (user_id, title) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2,title);

            statement.executeUpdate();
        }catch (Exception e){
            System.out.println("Can't count vote!!!");
        }
    }
}
