package se.kth.iv1350.possystem.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.possystem.model.Item;
import se.kth.iv1350.possystem.model.ItemDTO;

/**
 * Simulates an external inventory system containing pre-defined store items.
 */
public class ExternalInventorySystem {
    private List<Item> inventoryList = new ArrayList<>();
    private List<ItemDTO> itemDefinitions = new ArrayList<>();

    public ExternalInventorySystem() {
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
        this.itemDefinitions.add(new ItemDTO("Felix Potatisbullar", 32.0, 12, 234));
        this.itemDefinitions.add(new ItemDTO("Laxfilé 250g", 85.0, 6, 345));

        this.inventoryList.add(new Item(123, itemDefinitions.get(0), 100));
        this.inventoryList.add(new Item(234, itemDefinitions.get(1), 100));
        this.inventoryList.add(new Item(345, itemDefinitions.get(2), 100));
    }

    /**
     * Tries to find an item using its barcode. May simulate database errors.
     * @param barCode The barcode used to identify the item
     * @return the matching item, if found
     * @throws BarCodeNotFoundException if the barcode doesn’t match any item
     * @throws DataBaseFailureException if the simulated database goes offline
     */
    public Item search(int barCode) throws BarCodeNotFoundException, DataBaseFailureException {
        DatabaseHandler dbCheck = new DatabaseHandler();

        try {
            dbCheck.databaseOperation();

            if (barCode == 500) {
                throw new DataBaseFailureException("Database not responding");
            }

            for (Item i : inventoryList) {
                if (i.getBarCode() == barCode) {
                    return i;
                }
            }
            throw new BarCodeNotFoundException("Item with bar code " + barCode + " not found in system");

        } catch (DataBaseFailureException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
