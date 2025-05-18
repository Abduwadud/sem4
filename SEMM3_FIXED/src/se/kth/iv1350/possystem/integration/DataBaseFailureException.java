package se.kth.iv1350.possystem.integration;

/**
 *This class is for when the database can not be called or
 * is offline
 */
public class DataBaseFailureException extends Exception{
    
    /**
     * Instance of database failure exception
     * @param errormessage used for sending the error message
     */
    public DataBaseFailureException(String errormessage){
        super(errormessage);
    }
    
}
