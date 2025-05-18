package se.kth.iv1350.possystem.model;

/**
 * Item data transfer object.
 */
public class ItemDTO {
    private String itemName;
    private double price;
    private double VAT;
    private int barCode; 
    
    /**
     * Creates instance of ItemDTO.
     * @param itemName name of an item.
     * @param price Price of the item
     * @param VAT  VAT of the item
     */
    public ItemDTO(String itemName, double price, double VAT, int barCode) {
    	this.itemName = itemName;
        this.price = price;
        this.VAT = VAT;
        this.barCode = barCode;
    }
    
     /**
     * Returns VAT for the item.
     * @return VAT for the item.
     */
    public double getVAT() {
        return this.VAT;
    }
    /**
     * getter for bar code
     * @return the bar code of an item
     */
    public int getBarCode(){
        return this.barCode;
    }
    
    /**
     * getter for item name
     * @return name of the item
     */
    public String getItemName() {
        return this.itemName;
    }
    
    /**
     * getter for price of an item
     * @return price of the item.
     */
    public double getPrice() {
        return this.price;
    }
}