package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookServiceImpl implements BookService{
    @Override
    public void addBook() {

try{
    Scanner scanner = new Scanner(System.in);
    BookEntity bookEntity = new BookEntity();

    System.out.println("isbn");
    bookEntity.setIsbn(scanner.nextInt());
    System.out.println("book name");
    bookEntity.setBookName(scanner.nextLine());
    bookEntity.setBookName(scanner.nextLine());
    System.out.println("author name");
    bookEntity.setAuthorName(scanner.nextLine());
    System.out.println("genre");
    bookEntity.setGenre(scanner.nextLine());
    System.out.println("release year");
    bookEntity.setReleaseYear(scanner.nextInt());
    System.out.println("pages");
    bookEntity.setPages(scanner.nextInt());

    Connection connection = ConnectionFactory.getConnection();
    Statement statement = connection.createStatement();

    String query = "insert into books values ('"+bookEntity.getIsbn()+"','"+bookEntity.getBookName()
            +"','"+bookEntity.getAuthorName()+"','"+bookEntity.getGenre()+"','"+bookEntity.getReleaseYear()
            +"','"+bookEntity.getPages()+"')";
    statement.executeUpdate(query);

}catch (Exception e){
    System.out.println(e);
}
    }

    @Override
    public void displayBooks() {

        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from books";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4)
                        + " " + resultSet.getString(5)
                        + " " + resultSet.getString(6));
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
