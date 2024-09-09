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
                        + " " + resultSet.getString(6)
                        +" " + resultSet.getString(7));
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void deleteBook(int isbn) {
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="delete from books where isbn='"+isbn+"'";
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void findBookByName(String name){
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="select * from books where book_name='"+name+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4)
                        + " " + resultSet.getString(5)
                        + " " + resultSet.getString(6)
                        + " " + resultSet.getString(7));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addRating(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter book name to rate it");
        String name = scanner.nextLine();
        if (isBookExist(name)){
            try {
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
    public boolean isBookExist(String name) {
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
}
