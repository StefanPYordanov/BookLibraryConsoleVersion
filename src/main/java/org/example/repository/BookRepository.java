package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    Connection connection = ConnectionFactory.getConnection();
    public int addBook (BookEntity bookEntity) throws SQLException {
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
        return statement.executeUpdate();
    }
    public ResultSet getAllBooks () throws SQLException {
        String query = "SELECT * FROM books";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void deleteBookByName (String bookToDelete) throws SQLException {
        String query = "DELETE FROM books WHERE book_name='" + bookToDelete + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
    public ResultSet getBookByName (String name) throws SQLException {
        String query = "SELECT * FROM books WHERE book_name='" + name + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void updateBookRatingByName (int rating, String name) throws SQLException {
        String query = "UPDATE books SET `rating` = '" + rating + "' WHERE book_name ='" + name + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
    }
    public ResultSet getBooksByRating () throws SQLException {
        String query = "SELECT * FROM books ORDER BY rating DESC";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;

    }
    public ResultSet getBookNameByUserId (int userId) throws SQLException {
        String query = "SELECT title FROM ratings WHERE user_id = '" + userId + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void addRating (int userId, String title){
        try {
            String query = "INSERT INTO ratings (user_id, title) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, title);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't count vote!!!");
        }
    }
}
