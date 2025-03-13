package org.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    static final String LOGGER_FILE_PATH = "D:\\SoftUni\\SAP Project\\BookLibraryConsoleVersion\\src\\main\\" +
            "java\\org\\example\\logger\\application.log";
    private static Logger logger;
    static {
        logger = Logger.getLogger(LoggerUtil.class.getName());
        try{
            FileHandler fileHandler = new FileHandler(LOGGER_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (IOException e){
            System.out.println("Logger has failed !");
        }
    }
    public static void logInfo(String message){
        logger.info(message);
    }
    public static void logWaring(String message){
        logger.warning(message);
    }
    public static void logSevere(String message){
        logger.severe(message);
    }
}
