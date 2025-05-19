package se.kth.iv1350.possystem.integration;

/**
 * Handles database-related checks and triggers custom exceptions when needed.
 */
public class DatabaseHandler {

    /**
     * Creates a new instance of the database handler.
     */
    public DatabaseHandler() {
    }

    /**
     * Simulates a database call and throws an exception if the database is unavailable.
     * @throws DataBaseFailureException if the system simulates a database failure,
     * which occurs when the barcode is 500
     */
    public void databaseOperation() throws DataBaseFailureException {
        boolean isDbUnavailable = false; 
        if (isDbUnavailable) {
            throw new DataBaseFailureException("The database is down");
        }
    }

}
