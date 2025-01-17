package lesson33.server;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting Chat Server...");
        ChatServer server = new ChatServer();
        server.start();
    }
}
