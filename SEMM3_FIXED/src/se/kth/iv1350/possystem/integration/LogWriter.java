
// FIXED: Enforced Singleton pattern
package se.kth.iv1350.possystem.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for writing logs to a file.
 */
public class LogWriter {
    private static final LogWriter instance = new LogWriter(); // ADDED: Singleton instance
    private static final String LOG_FILE = "log";
    private PrintWriter logStream;

    // ADDED: Private constructor for Singleton
    private LogWriter() {
        try {
            this.logStream = new PrintWriter(new FileWriter(LOG_FILE, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ADDED: Public access to Singleton instance
    public static LogWriter getInstance() {
        return instance;
    }

    // ADDED: Method to log plain messages
    public void logMessage(String message) {
        logStream.println(message);
    }

    // ADDED: Method to log exception stack traces
    public void logException(String message, Exception e) {
        logStream.println(message);
        e.printStackTrace(logStream);
    }
}
