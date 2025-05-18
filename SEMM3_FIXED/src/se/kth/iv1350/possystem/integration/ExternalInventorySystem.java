package se.kth.iv1350.possystem.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.possystem.model.Item;
import se.kth.iv1350.possystem.model.ItemDTO;

/**
 *External inventory system of all items with hard coded example items
 */
public class ExternalInventorySystem {
    private List<Item> storeItems = new ArrayList<>();
    private List<ItemDTO> itemDTOs = new ArrayList<>();
	
    
    public ExternalInventorySystem() {
        
	}
    
    /**
     * Getter for list existing items in inventory
     * @return storeitems returns list of items in store
     */
    public List<Item> getStoreItems(){
        return this.storeItems;
    }
   
    /**
     * Adds items to the store inventory.
     * (ItemName, price, VAT, barcode)
     */
    public void addItem() {
        this.itemDTOs.add(new ItemDTO("Apples 5kg", 130, 8,123)); 
        this.itemDTOs.add(new ItemDTO("Ketchup", 10, 5,234));
        this.itemDTOs.add(new ItemDTO("Fish 100g", 20, 6, 345));

        this.storeItems.add(new Item(1, itemDTOs.get(0), 100));
        this.storeItems.add(new Item(2, itemDTOs.get(1), 100));
        this.storeItems.add(new Item(3, itemDTOs.get(2), 100));
}

    /**
     * Searches for an item in the inventory system using an identifier and
     * throws exception if database is down or if barcode is not found
     * @param barCode the item's identifier.
     * @return Returns item if found, otherwise thows exception.
     * @throws se.kth.iv1350.possystem.integration.BarCodeNotFoundException if barcode not found
     * @throws se.kth.iv1350.possystem.integration.DataBaseFailureException if database is down
     */
    public Item search(int barCode) throws BarCodeNotFoundException, DataBaseFailureException {
            
        DatabaseHandler exceptionHandler = new DatabaseHandler();
    
        try {
            exceptionHandler.databaseOperation();
                
            if(barCode == 500){
                throw new DataBaseFailureException("Database not responding");
            }

            for (Item item : storeItems) {
                if(item.getBarCode() == barCode) {
                    return item;
                }
            }
            throw new BarCodeNotFoundException("Item with bar code " + barCode + " not found in system");
    
        } catch (DataBaseFailureException e) {
                
            System.out.println(e.getMessage());
            throw e;
            }
        }        
}