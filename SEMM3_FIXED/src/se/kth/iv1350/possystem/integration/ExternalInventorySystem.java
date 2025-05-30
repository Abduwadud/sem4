
// FIXED: Correct package name
package se.kth.iv1350.possystem.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.possystem.model.Item;
import se.kth.iv1350.possystem.model.ItemDTO;
import se.kth.iv1350.possystem.model.Sale;

/**
 * Simulates an external inventory system containing pre-defined store items.
 */
public class ExternalInventorySystem {
    private List<Item> inventoryList = new ArrayList<>();
    private List<ItemDTO> itemDefinitions = new ArrayList<>();
    private final LogWriter logger = LogWriter.getInstance(); // ADDED: Singleton logger

    public ExternalInventorySystem() {
        addItem(); // FIXED: Populate inventory upon construction
    }

    /**
     * Provides access to the store’s current list of stocked items.
     * @return inventoryList containing all item objects available in store
     */
    public List<Item> getStoreItems() {
        return this.inventoryList;
    }

    /**
     * Populates the store inventory with hardcoded sample items.
     * Each item is defined with a name, price, VAT, and barcode.
     */
    public void addItem() {
        this.itemDefinitions.add(new ItemDTO("Banan eko 2.15kg", 43.0, 0, 123));
        this.itemDefinitions.add(new ItemDTO("Felix Potatisbullar", 29.0, 0, 456));
    }

    /**
     * Searches for an item in the system.
     * @param barCode The bar code to search for
     * @return The found ItemDTO
     * @throws BarCodeNotFoundException if the item isn't found
     * @throws DataBaseFailureException if database access fails
     */
    public ItemDTO searchItem(int barCode) throws BarCodeNotFoundException, DataBaseFailureException {
        logger.logMessage("Searching for item with barcode: " + barCode); // ADDED: MVC-compliant logging

        if (barCode == -1) {
            DataBaseFailureException ex = new DataBaseFailureException("Database error occurred for barcode: " + barCode);
            logger.logException("Database failure logged:", ex); // ADDED: Log full stack trace
            throw ex;
        }

        for (ItemDTO item : itemDefinitions) {
            if (item.getBarCode() == barCode) {
                return item;
            }
        }

        BarCodeNotFoundException ex = new BarCodeNotFoundException("Item not found for barcode: " + barCode);
        logger.logException("Barcode not found logged:", ex); // ADDED: Log full stack trace
        throw ex;
    }
    public void updateInventory(Sale sale) {
    System.out.println("Inventory updated with sale.");
}

}
