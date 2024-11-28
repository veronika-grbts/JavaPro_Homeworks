package lesson24;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Hello World - logger");
        logger.log("hello java - logger");

        Logger anotherLogger = Logger.getInstance();
        anotherLogger.log("hello word - anotherLogger");

    }
}
