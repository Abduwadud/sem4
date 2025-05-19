package se.kth.iv1350.possystem.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.possystem.integration.BarCodeNotFoundException;
import se.kth.iv1350.possystem.integration.DataBaseFailureException;
import se.kth.iv1350.possystem.integration.ExternalAccountingSystem;
import se.kth.iv1350.possystem.integration.ExternalInventorySystem;
import se.kth.iv1350.possystem.integration.Printer;
import se.kth.iv1350.possystem.model.IncomeObserver;
import se.kth.iv1350.possystem.model.Item;
import se.kth.iv1350.possystem.model.Payment;
import se.kth.iv1350.possystem.model.Sale;
import se.kth.iv1350.possystem.model.SaleDTO;
import se.kth.iv1350.possystem.view.TotalRevenueFileOutput;
import se.kth.iv1350.possystem.view.TotalRevenueView;

/**
 * Acts as the main coordinator between external systems, the model layer, and the user interface.
 */
public class Controller {
    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer receiptPrinter; 
    private Sale currentSale;
    private List<IncomeObserver> revenueTrackers = new ArrayList<>();
    private TotalRevenueView revenueView;
    private TotalRevenueFileOutput revenueFileLogger;
    
    /**
     * Initializes the controller and sets up external system connections.
     * @param receiptPrinter Used to output the receipt
     * @param accountingSystem External system that tracks financial transactions
     * @param inventorySystem External storage representing item inventory
     */
    public Controller(Printer receiptPrinter, ExternalAccountingSystem accountingSystem, ExternalInventorySystem inventorySystem) {
        this.receiptPrinter = receiptPrinter;
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        inventorySystem.addItem();
    }
    
    /**
     * Begins a new purchase session.
     * @return SaleDTO containing details of the new sale
     */
    public SaleDTO startSale() {
        this.currentSale = new Sale();
        for(IncomeObserver tracker : revenueTrackers)
            currentSale.IncomeObs(tracker);
        return currentSale.getSaleInformation();
    }
    
    /**
     * Attempts to scan and register an item by its barcode.
     * @param barCode Item identifier
     * @param quantity How many of the scanned item the customer wants
     * @return updated sale data after the item is added
     * @throws BarCodeNotFoundException when barcode is missing in the inventory
     * @throws DataBaseFailureException triggered by simulated DB error (barcode 500)
     */
    public SaleDTO enterItem(int barCode, int quantity) throws BarCodeNotFoundException, DataBaseFailureException {
        Item foundItem = inventorySystem.search(barCode);
        if (foundItem == null) {
            return null;
        }
        if (foundItem.getStoreQuantity() >= quantity) {
            currentSale.addItem(foundItem, quantity);
        } else {
            return null;
        }
        return this.currentSale.getSaleInformation();
    }

    /**
     * Finalizes the sale and synchronizes inventory updates.
     * @return summary of the completed sale
     */
    public SaleDTO endSale() {
        List<Integer> soldQuantities = currentSale.getCustomerItemsQuantity();
        List<Item> purchasedItems = currentSale.getItems();

        for (Item item : purchasedItems) {
            for (Item stockItem : this.inventorySystem.getStoreItems()) {
                if (item == stockItem) {
                    this.inventorySystem.getStoreItems()
                        .get(this.inventorySystem.getStoreItems().indexOf(stockItem))
                        .updateQuantity(soldQuantities.get(purchasedItems.indexOf(item)));
                }
            }
        }

        return this.currentSale.getSaleInformation();
    }
    
    /**
     * Finalizes the payment process, calculates change, and notifies revenue trackers.
     * @param paidAmount how much the customer has given
     * @param saleTotal total cost of all purchased items
     * @return Payment object containing change and optional messages
     */
    public Payment pay(double paidAmount, double saleTotal) {
        double changeDue = paidAmount - saleTotal;
        String message = "";

        if(changeDue >= 0){
            this.accountingSystem.update(paidAmount - changeDue);
        } else {
            message = "Insufficient funds";
            changeDue = 0;
        }

        for(IncomeObserver tracker : revenueTrackers){
            tracker.updateIncome(saleTotal);
        }

        return new Payment(changeDue, message);
    }

    /**
     * Prints the final receipt of the current sale.
     */
    public void print() {
        receiptPrinter.print(this.currentSale.getReceipt(currentSale));
    }

    /**
     * Adds a revenue observer for monitoring earnings after each sale.
     * @param tracker observer instance to be notified
     */
    public void addObs(IncomeObserver tracker){
        revenueTrackers.add(tracker);
    }
}
