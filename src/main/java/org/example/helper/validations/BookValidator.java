package org.example.helper.validations;

import org.example.repository.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.helper.messages.TextMessages.*;

public class BookValidator {

    BookRepository bookRepository = new BookRepository();

    public boolean isNumberPositive(int number) { //-> Check if isbn is a positive number
        if (number < MIN_VALUE_OF_POSITIVE_NUMBER) {
            System.out.println("Must be positive number!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isBookExist(String name) { //-> Check if book exist in DB
        try {
            ResultSet resultSet = bookRepository.getBookByName(name);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("A problem has occurred with finding book in database !");
            return false;
        }
    }

    public boolean isYearValid(int year){ //-> check if year is valid
        if (year > CURRENT_YEAR || year < MIN_VALUE_OF_POSITIVE_NUMBER){
            System.out.println("Invalid Year");
            return false;
        }else{
            return true;
        }
    }
}
