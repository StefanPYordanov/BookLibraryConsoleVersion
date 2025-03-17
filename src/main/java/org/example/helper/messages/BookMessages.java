package org.example.helper.messages;

import java.time.LocalDate;

public final class BookMessages {
    public static final int MIN_VALUE_OF_POSITIVE_NUMBER = 1;
    public static int CURRENT_YEAR = LocalDate.now().getYear();
    public static final int MIN_LENGTH_REQUIREMENT_FOR_FIELD = 4;
    public static final int RATING_BEFORE_SOMEONE_RATE_FOR_BOOK = 0;
    private BookMessages (){
        throw new UnsupportedOperationException("Cannot instantiate this class !");
    }
}
