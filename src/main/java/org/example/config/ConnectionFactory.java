package org.example.config;

import org.example.logger.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    static Connection connection;

    public static Connection getConnection() {
        try {
            // Load Property file
            Properties props = new Properties();
            props.load(new FileInputStream(".\\src\\main\\resources\\db.properties"));
            // Read Property file
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String url = props.getProperty("url");
            // Get Connection to db
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,
                    user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection to DB Fail !");
            LoggerUtil.logSevere("Connection to DataBase failed");
        }catch(IOException e){
            System.out.println("Property file missing !");
            LoggerUtil.logSevere("Property file for database is missing");
        }
        return connection;
    }
}