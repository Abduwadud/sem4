package se.kth.iv1350.possystem.model;

/**
 *Interface for observing and implementing the updated revenue
 */
public interface IncomeObserver {
    
    
    /**
     * Updates the total income based on sales total price
     * @param totalPrice total price of a single sale
     */
    public void updateIncome(double totalPrice);
    
}
