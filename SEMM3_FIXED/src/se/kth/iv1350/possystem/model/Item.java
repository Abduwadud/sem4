package se.kth.iv1350.possystem.model;

/**
 * This class represents one item.
 */
public class Item {
    private ItemDTO itemDTO;
    private int storeQuantity;
    private int barCode; 
    
    /**
     * Creates instance of Item object.
     * @param barCode The items bar code
     * @param itemDTO The itemDTO object.
     * @param quantity The quantitiy of the item.
     */
    public Item(int barCode, ItemDTO itemDTO, int quantity) {
        this.barCode = barCode;
        this.itemDTO = itemDTO;
        this.storeQuantity = quantity;
    }
    /**
     * Returns the itemDTO of an item.
     * @return ItemDTO of the item.
     */
    public ItemDTO getItemDTO() {
    	return this.itemDTO; 
    }
    /**
     * Returns item bar code
     * @return bar code
     */
    public int getBarCode() {
    	return this.barCode;
    }
    // ADDED: Simplified constructor for wrapping an ItemDTO
public Item(ItemDTO itemDTO) {
    this.itemDTO = itemDTO;
    this.barCode = itemDTO.getBarCode();
    this.storeQuantity = 10; // Default or mock quantity
}

    /**
     * Returns store quantity of an item in the store.
     * @return quantity of item in store.
     */
    public int getStoreQuantity() {
    	return this.storeQuantity;
    }

    /**
     * Updates the quantity of an item in the store.
     * @param amountSold The quantity of an item that has been sold.
     */
    public void updateQuantity(int amountSold){
    	this.storeQuantity -= amountSold;
    }
}