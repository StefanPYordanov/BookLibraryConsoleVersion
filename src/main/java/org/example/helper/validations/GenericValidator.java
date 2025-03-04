package org.example.helper.validations;

import static org.example.helper.messages.TextMessages.MIN_LENGTH_REQUIREMENT_FOR_FIELD;

public class GenericValidator {
    public static boolean isFieldEmpty(String field) { //-> Check if someone try to input empty text or only few letters
        if (field.trim().length() < MIN_LENGTH_REQUIREMENT_FOR_FIELD) {
            System.out.println("Field must contain at least 4 symbols!");
            return true;
        } else {
            return false;
        }
    }
}
