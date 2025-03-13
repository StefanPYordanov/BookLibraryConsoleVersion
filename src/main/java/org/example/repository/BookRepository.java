package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.logger.LoggerUtil;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    Connection connection = ConnectionFactory.getConnection();
    public void saveBook (BookEntity bookEntity) { //-> Add book to DB
        try {
            String query = "INSERT INTO books (isbn, book_name, author_name, genre, release_year, pages, rating) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, bookEntity.getIsbn());
            statement.setString(2, bookEntity.getBookName());
            statement.setString(3, bookEntity.getAuthorName());
            statement.setString(4, bookEntity.getGenre());
            statement.setInt(5, bookEntity.getReleaseYear());
            statement.setInt(6, bookEntity.getPages());
            statement.setInt(7, bookEntity.getRating());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("A problem has occurred with adding book!\nPlease try again !");
            LoggerUtil.logWaring("Adding a book failed due to SQL Exception");
        }
    }
    public ResultSet getAllBooks () { //-> Show all books from DB
        try {
            String query = "SELECT * FROM books";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e){
            System.out.println("A problem has occurred with showing all books!\nPlease try again !");
            LoggerUtil.logWaring("Displaying a book failed due to SQL Exception");
            return null;
        }
    }
    public void deleteBookByName (String bookToDelete) { //-> Delete book from DB
        try {
            String query = "DELETE FROM books WHERE book_name='" + bookToDelete + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println("There is problem with deleting book!\nPlease try again !");
            LoggerUtil.logWaring("Deleting a book by title failed due to SQL Exception");
        }
    }
    public ResultSet getBookByName (String name) { //-> Show book from DB
        try {
            String query = "SELECT * FROM books WHERE book_name='" + name + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        }catch(SQLException e){
            System.out.println("A problem has occurred with getting book from database!");
            LoggerUtil.logWaring("Finding a book by name in database failed due to SQL Exception");
            return null;
        }
    }
    public void updateBookRatingByBookName(int rating, String name)  { //-> Update rating for book
        try {
            String query = "UPDATE books SET `rating` = '" + rating + "' WHERE book_name ='" + name + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
        }catch (SQLException e){
            System.out.println("A problem has occurred with update rating in database!");
            LoggerUtil.logWaring("Rating for a book failed due to SQL Exception");
        }
    }
    public ResultSet getAllBooksInOrderByRating()  { //-> Show most rated books
        try {
            String query = "SELECT * FROM books ORDER BY rating DESC";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        }catch (SQLException e){
            System.out.println("A problem has occurred with getting books with biggest rating!\n" +
                    "Please try again !");
            LoggerUtil.logWaring("Displaying books in order failed due to SQL Exception");
            return null;
        }
    }
    public ResultSet getBookNameByUserId (int userId)  { //-> Show does user rate for current book
        try {
            String query = "SELECT title FROM ratings WHERE user_id = '" + userId + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        }catch (SQLException e){
            System.out.println("A problem has occurred with getting book by username in database!");
            LoggerUtil.logWaring("Finding a book by user id in database failed due to SQL Exception");
            return null;
        }
    }
    public void saveRatingToCorrespondingUser (int userId, String title){ //-> Add book rating and which user rate for the book
        try {
            String query = "INSERT INTO ratings (user_id, title) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, title);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("A problem has occurred with adding rating to book!\n" +
                    "Please try again !");
            LoggerUtil.logWaring("Adding rating to book failed due to SQL Exception");
        }
    }
}
