package org.example.helper;

import java.util.Random;

public class PasswordManager {
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    public String passwordEncrypt (String password){
        for (int i = 0; i < 20; i++) { // For loop to get 20 random symbols
            char letter = (char) (random.nextInt(33,122)); // Take symbols from ascii table
            sb.append(letter); // Add them to builder
        }
        sb.append(password); // Add password to 20 random symbols
        for (int i = 0; i < 20; i++) { // Rotate new for loop for get 20 more symbols
            char letter = (char) (random.nextInt(33,122)); // Take 20 more symbols from ascii table
            sb.append(letter); // Add them to first 20 symbols and to password
        }
        sb.reverse(); //Reverse whole builder
        return sb.toString(); // Return encrypted password
    }
    public String passwordDecrypt (String password){
        for (int i = 0; i < 20; i++) { // For loop to remove 20 random symbols
            sb = new StringBuilder();
            sb.append(password); // Add encrypted password
            sb.reverse(); // Reverse whole builder again to be in normal order
            sb.delete(0,20); // Remove first 20 added random symbols
            sb.delete(sb.length() - 20, sb.length()); // Remove last 20 added random symbols


        }
        return sb.toString(); // Return decrypted password
    }
}
