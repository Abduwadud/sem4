package se.kth.iv1350.possystem.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *Class for writing logs in a given filename
 */
public class LogWriter {
    private PrintWriter logStream;
    
    /**
     * Instance of file logger
     * @param filename name of the file which shall be created
     */
    public LogWriter(String filename){
        try {
            this.logStream = new PrintWriter(new FileWriter(filename, true), true);

            this.logStream.println("This is a log message");
        } catch (IOException e) {
            System.out.println("An error occurred while trying to write to the file" + e.getMessage());
        }
    }

    /**
     * Logs a message to the file.
     *
     * @param message The message to be logged.
     */
    public void log(String message) {
        this.logStream.println(message);
        this.logStream.flush();
    }
    
}