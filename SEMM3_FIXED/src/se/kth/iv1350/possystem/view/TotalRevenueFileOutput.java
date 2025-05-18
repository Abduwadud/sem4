package se.kth.iv1350.possystem.view;

import java.io.*;
import se.kth.iv1350.possystem.model.IncomeObserver;
/**
 *A class which observes and logs total income in a file
 */
public class TotalRevenueFileOutput implements IncomeObserver {
    private PrintWriter fileWriter;
    private double totalIncome;
    
    TotalRevenueFileOutput(){
        try{
            this.fileWriter = new PrintWriter(new FileWriter("Income.txt"), true);
        }
        catch(IOException e){
            System.out.println("File not reached");
        }
    }
    
    
    /**
     * Updates the log with total price of a sale
     * @param totalPrice the total price of a single sale
     */
    @Override
    public void updateIncome(double totalPrice){
        this.totalIncome += totalPrice;
        this.fileWriter.println("Total income: " + this.totalIncome);
    }
    
 
    
}