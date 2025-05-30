package se.kth.iv1350.possystem.model;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents the recepit of one sale.
 */
public class Receipt {
    private SaleDTO saleInformation;
    private LocalTime time;
    private final String nameOfStore = "Grabbarna Grus";
    private double totalDiscount;
    private double totalVAT;
    private double totalPrice;
    private List<Item> nameOfItems;
    
    /**
     * Creates instance of receipt.
     * @param saleInformation saleDTO with information of a sale
     */
    public Receipt(SaleDTO saleInformation) {
        this.saleInformation = saleInformation;
        this.totalVAT = saleInformation.getTotalVAT();
        this.totalPrice = saleInformation.getTotalPrice();
        this.nameOfItems = saleInformation.getItems();
    }
    
    /**
     * Returns current time to print out time of sale.
     * @return time is the current time of the sale.
     */
    public java.time.LocalTime getTimeOfSale() {
        this.time = LocalTime.now();
        return this.time;
    }
     /**
     * getter for total price of a sale
     * @return totalPrice is the total price of the sale.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    /**
     * Returns a arraylist containing the names of all items.
     * @return nameOfItems is the list of all items in a sale.
     */
    public List<Item> getNameOfItems() {
        return this.nameOfItems;
    }
    
    /**
     * Returns name of the store.
     * @return nameOfStore is the name of the store.
     */
    public String getNameOfStore() {
        return this.nameOfStore;
    }
    
    /**
     * Returns the total discount of a sale.
     * @return totalDiscount is the total discount of the sale.
     */
    public double getTotalDiscount() {
        return this.totalDiscount;
    }
    
    /**
     * Returns the total VAT.
     * @return totalVAT is the total VAT of the sale.
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }
    // ADDED: Constructor that includes payment
public Receipt(SaleDTO saleInformation, Payment payment) {
    this.saleInformation = saleInformation;
    this.totalVAT = saleInformation.getTotalVAT();
    this.totalPrice = saleInformation.getTotalPrice();
    this.nameOfItems = saleInformation.getItems();
    this.time = LocalTime.now();
    // You can log or print payment info if needed
}

}