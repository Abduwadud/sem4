package se.kth.iv1350.possystem.integration;

/**
 *This class is for an exception when a bar code
 * is not found
 */
public class BarCodeNotFoundException extends Exception{
    
    /**
     * Instance of barcode not found exception
     * @param errormessage sends message descibing the error
     */
    public BarCodeNotFoundException(String errormessage){
        super(errormessage);
    }
}
