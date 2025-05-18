package se.kth.iv1350.possystem.view;
import se.kth.iv1350.possystem.model.IncomeObserver;

/**
 *Observer a sales total revenue and displays on user interface
 */
public class TotalRevenueView implements IncomeObserver {
    private double totalIncome;
    
    
    /**
     * Upfated the total income 
     * @param totalPrice total price a single sale
     */
    @Override
    public void updateIncome(double totalPrice){
        totalIncome += totalPrice;
        System.out.println("Total Income: " + this.totalIncome);
    }
}