package org.example.helper.validations;

import java.util.Scanner;

import static org.example.helper.messages.BookMessages.MIN_LENGTH_REQUIREMENT_FOR_FIELD;

public class GenericValidator {
    public static boolean isFieldEmpty(String fieldInput) { //-> Check if someone try to input empty text or only few letters
        if (fieldInput.trim().length() < MIN_LENGTH_REQUIREMENT_FOR_FIELD) {
            System.out.println("Field must contain at least 4 symbols!");
            return true;
        } else {
            return false;
        }
    }
    public static int readNumber (){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                int number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e){
                System.out.println("Invalid command, please type in number!");
            }
        }

    }
}
