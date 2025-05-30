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
import se.kth.iv1350.possystem.model.ItemDTO;
import se.kth.iv1350.possystem.model.Payment;
import se.kth.iv1350.possystem.model.Receipt;
import se.kth.iv1350.possystem.model.Sale;
import se.kth.iv1350.possystem.model.SaleDTO;

/**
 * This class is responsible for handling the operations between the view and model layers.
 */
public class Controller {
    private Sale currentSale;
    private ExternalInventorySystem inventorySystem;
    private ExternalAccountingSystem accountingSystem;
    private Printer printer;
    private List<IncomeObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates an instance of the controller.
     * 
     * @param inventorySystem External inventory system.
     * @param accountingSystem External accounting system.
     * @param printer Printer for receipt.
     */
    public Controller(ExternalInventorySystem inventorySystem, ExternalAccountingSystem accountingSystem, Printer printer) {
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.printer = printer;
    }

    /**
     * Starts a new sale transaction.
     */
    public void startSale() {
        this.currentSale = new Sale();
        for (IncomeObserver obs : revenueObservers) {
            currentSale.addObserver(obs);
        }
    }

    /**
     * Adds an item to the current sale.
     * 
     * @param barCode Item bar code.
     * @param quantity Quantity of item.
     * @return SaleDTO containing updated sale info.
     * @throws BarCodeNotFoundException when barcode is missing in the inventory
     * @throws DataBaseFailureException triggered by simulated DB error (barcode -1)
     */
    public SaleDTO enterItem(int barCode, int quantity) throws BarCodeNotFoundException, DataBaseFailureException {
        ItemDTO foundItemDTO = inventorySystem.searchItem(barCode); // FIXED: Corrected method name
        Item foundItem = new Item(foundItemDTO); // FIXED: Wrap DTO into domain object

        if (foundItem == null) {
            return null;
        }
        if (foundItem.getStoreQuantity() >= quantity) {
            currentSale.addItem(foundItem, quantity);
        }
        return currentSale.getSaleDTO();
    }

    /**
     * Completes the sale and returns the total amount.
     * 
     * @return Total price of sale.
     */
    public double endSale() {
        return currentSale.calculateTotalPrice();
    }

    /**
     * Processes the payment for the sale.
     * 
     * @param amountPaid amount customer paid.
     * @param totalPrice total cost of the sale.
     */
    public void pay(double amountPaid, double totalPrice) {
        Payment payment = new Payment(amountPaid, totalPrice);
        Receipt receipt = currentSale.generateReceipt(payment);
        accountingSystem.update(receipt);
        inventorySystem.updateInventory(currentSale);
        printer.printReceipt(receipt);
    }

    /**
     * Adds an observer to observe revenue.
     * 
     * @param obs Observer to add.
     */
    public void addObs(IncomeObserver obs) {
        revenueObservers.add(obs);
    }
}
