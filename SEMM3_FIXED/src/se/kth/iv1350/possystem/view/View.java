package se.kth.iv1350.possystem.view;

import se.kth.iv1350.possystem.controller.Controller;
import se.kth.iv1350.possystem.integration.BarCodeNotFoundException;
import se.kth.iv1350.possystem.integration.DataBaseFailureException;
import se.kth.iv1350.possystem.integration.LogWriter;
import se.kth.iv1350.possystem.model.Payment;
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
        this.errorLogger = new LogWriter("log");
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
            System.out.println("Running total includning VAT: " + totalCost + " SEK");
            System.out.println("Item VAT: " + saleDTO1.getItems().get(0).getItemDTO().getVAT() + "SEK\n");

            // Second item
            SaleDTO saleDTO2 = controller.enterItem(234, 1);
            System.out.println("Item ID: " + saleDTO2.getItems().get(1).getItemDTO().getBarCode());
            System.out.println("Item: " + saleDTO2.getItems().get(1).getItemDTO().getItemName() + " " + saleDTO2.getItems().get(1).getItemDTO().getPrice() + " SEK");
            totalCost += saleDTO2.getItems().get(1).getItemDTO().getPrice();
            System.out.println("Running total includning VAT: " + totalCost + " SEK");
            System.out.println("Item VAT: " + saleDTO2.getItems().get(1).getItemDTO().getVAT() + "SEK\n");

            // Third item
            SaleDTO saleDTO3 = controller.enterItem(345, 1);
            System.out.println("Item ID: " + saleDTO3.getItems().get(2).getItemDTO().getBarCode());
            System.out.println("Item: " + saleDTO3.getItems().get(2).getItemDTO().getItemName() + " " + saleDTO3.getItems().get(2).getItemDTO().getPrice() + " SEK");
            totalCost += saleDTO3.getItems().get(2).getItemDTO().getPrice();
            System.out.println("Running total includning VAT: " + totalCost + " SEK");
            System.out.println("Item VAT: " + saleDTO3.getItems().get(2).getItemDTO().getVAT() + "SEK\n");

            controller.endSale();
            System.out.println("Sale ended\n");
            System.out.println("--------Receipt--------");
            controller.print();

            Payment result = controller.pay(200, totalCost);
            if (!result.getMessage().isEmpty()) {
                System.out.println(result.getMessage());
            }
            System.out.println("Change: " + result.getChange() + " SEK");
        } catch (BarCodeNotFoundException e) {
            System.out.println("Barcode not found");
            errorLogger.log("Invalid barcode: " + e);
        } catch (DataBaseFailureException e) {
            System.out.println("Database offline");
            errorLogger.log("Database failure: " + e);
        }
    }
}
