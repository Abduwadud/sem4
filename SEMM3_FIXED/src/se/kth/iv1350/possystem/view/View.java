package se.kth.iv1350.possystem.view;

import se.kth.iv1350.possystem.controller.Controller;
import se.kth.iv1350.possystem.integration.BarCodeNotFoundException;
import se.kth.iv1350.possystem.integration.DataBaseFailureException;
import se.kth.iv1350.possystem.integration.LogWriter;
import se.kth.iv1350.possystem.model.Payment;
import se.kth.iv1350.possystem.model.SaleDTO;



/**
 * The View of the program which runs a runFakeExecution task.
 */
public class View {
    private Controller contr;
    private double runningTotal;
    private LogWriter logWriter;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addObs(new TotalRevenueFileOutput());
        contr.addObs(new TotalRevenueView());
        this.logWriter = new LogWriter("log");
    }
    
    /**
     * Performs a fake sale by calling all system operations in the controller.
     */
    public void runFakeExecution() {
    	contr.startSale();
    	System.out.println("Starting a new sale");
        
        System.out.println("Scan items");
        
        
    	try{
            
            
            SaleDTO saleDTO = contr.enterItem(1, 10);
        
            System.out.println("Item ID: " + saleDTO.getItems().get(0).getItemDTO().getBarCode());
        
        
            System.out.println("Item: " + saleDTO.getItems().get(0).getItemDTO().getItemName() + " " +  saleDTO.getItems().get(0).getItemDTO().getPrice() + " SEK");
            runningTotal += saleDTO.getItems().get(0).getItemDTO().getPrice();
            System.out.println("Running total includning VAT: " + runningTotal + " SEK");
            System.out.println("Item VAT: " + saleDTO.getItems().get(0).getItemDTO().getVAT() + "SEK\n");
            contr.endSale();
        System.out.println("Sale ended\n");        
        System.out.println("--------Receipt--------");
    	contr.print();
        Payment paymentResult = contr.pay(200, runningTotal);
        if (!paymentResult.getMessage().isEmpty()) {
            System.out.println(paymentResult.getMessage());
        }
        System.out.println("Change: " + paymentResult.getChange() + " SEK");
        }
        
        catch(BarCodeNotFoundException exception){
            System.out.println("Barcode not found");
            logWriter.log("Error item with given barcode does not exist " + exception);
            
        }
        catch(DataBaseFailureException exception){
            System.out.println("Database offline");
            logWriter.log("Error database could not be reached " + exception);
        }
        
    }
}