package se.kth.iv1350.possystem.integration;


/**
 * This class is to handle and implement database exception
 */

public class DatabaseHandler {

    /**
     * instance of databasehandler
     */
    public DatabaseHandler() {
    }

    /**
     * Method for checking if databasse is down to throw the exception
     * @throws DataBaseFailureException if database is down. Happens when a 
     * barcode is equal to 500
     */
    public void databaseOperation() throws DataBaseFailureException {
        boolean databaseIsDown = false; 
        if (databaseIsDown) {
            throw new DataBaseFailureException("The database is down");
        }
    }

}
