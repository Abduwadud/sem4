package se.kth.iv1350.possystem.view;

import se.kth.iv1350.possystem.controller.Controller;
import se.kth.iv1350.possystem.integration.BarCodeNotFoundException;
import se.kth.iv1350.possystem.integration.DataBaseFailureException;
import se.kth.iv1350.possystem.integration.LogWriter;
import se.kth.iv1350.possystem.model.SaleDTO;

/**
 * Simulates the interface where user interactions take place.
 */
public class View {
    private Controller controller;
    private double totalCost;
    private LogWriter errorLogger;

    /**
     * Sets up the view using the provided controller for handling logic.
     * @param controller The controller used to communicate with system operations
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addObs(new TotalRevenueFileOutput());
        controller.addObs(new TotalRevenueView());
        this.errorLogger = LogWriter.getInstance(); // FIXED: Use Singleton instead of constructor
    }

    /**
     * Runs a dummy sale by calling system functions in the correct order.
     */
    public void runFakeExecution() {
        controller.startSale();
        System.out.println("Starting a new sale");
        System.out.println("Scan items");

        try {
            // First item
            SaleDTO saleDTO1 = controller.enterItem(123, 1);
            System.out.println("Item ID: " + saleDTO1.getItems().get(0).getItemDTO().getBarCode());
            System.out.println("Item: " + saleDTO1.getItems().get(0).getItemDTO().getItemName() + " " + saleDTO1.getItems().get(0).getItemDTO().getPrice() + " SEK");
            totalCost += saleDTO1.getItems().get(0).getItemDTO().getPrice();
            System.out.println("Running total including VAT: " + saleDTO1.getTotalPrice());

            // Second item
            SaleDTO saleDTO2 = controller.enterItem(456, 2);
            System.out.println("Item: " + saleDTO2.getItems().get(1).getItemDTO().getItemName() + " " + saleDTO2.getItems().get(1).getItemDTO().getPrice() + " SEK");
            totalCost += saleDTO2.getItems().get(1).getItemDTO().getPrice();
            System.out.println("Running total including VAT: " + saleDTO2.getTotalPrice());

        } catch (BarCodeNotFoundException | DataBaseFailureException e) {
            errorLogger.logException("Error during item entry:", e); // FIXED: Log full stack trace
        }

        controller.endSale();
        double amountPaid = 1000;
        controller.pay(amountPaid, totalCost); // FIXED: use correct method and parameters

        System.out.println("Total cost (including VAT): " + totalCost);
    }
}
