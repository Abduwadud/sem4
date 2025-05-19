package se.kth.iv1350.possystem.integration;

/**
 * Represents an external system that manages the store’s financial records.
 */
public class ExternalAccountingSystem {
    private int currentBalance;
    
    /**
     * Initializes the system with a base amount of funds.
     */
    public ExternalAccountingSystem() {
        this.currentBalance = 200;
    }
    
    /**
     * Increases the current balance by the amount paid in a completed sale.
     * @param amountPaid Total money received from the sale.
     */
    public void update(double amountPaid) {
        this.currentBalance += amountPaid;
    }
    
    /**
     * Returns the current balance stored in the accounting system.
     * @return the store’s financial balance.
     */
    public double getStoreBalance() {
        return this.currentBalance;
    }
}