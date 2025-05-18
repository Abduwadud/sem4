
package se.kth.iv1350.possystem.integration;

/**
 *External accounting system which handles all the stores accounting
 */
public class ExternalAccountingSystem {
     private int storeBalance;
    
    /**
     * Updates store balance with an example starting amount satrting amount.
     */
    public ExternalAccountingSystem() {
        this.storeBalance = 200;
    }
    
    /**
     * Updates the store balance with the total price of a sale.
     * @param totalPrice Money recived from the sale.
     */
    public void update(double totalPrice) {
    	this.storeBalance += totalPrice;
    }
    
    /**
     * Getter for store balance
     * @return store balance amount.
     */
    public double getStoreBalance() {
        return this.storeBalance;
    }
}