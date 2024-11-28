package lesson24;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Logger {
    private static Logger instance;
    private final String logFileName;

    private Logger() {
        String logDirectory = "files";
        File directory = new File(logDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        LocalDate currentDate = LocalDate.now();
        logFileName = logDirectory + File.separator + "logger-" + currentDate + ".txt";
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
