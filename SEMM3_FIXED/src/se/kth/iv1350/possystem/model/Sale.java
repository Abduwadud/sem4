package se.kth.iv1350.possystem.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

 /**
 * Represents one sale to one customer.
 */
public class Sale {
    private LocalTime time; 
    private SaleDTO saleInformation;
    private Receipt receipt;
    private List<Item> items;
    private List<Integer> customerItemsQuantity = new ArrayList<>();
    private double totalVAT;
    private double totalPrice;
    private List<IncomeObserver> incomeObs = new ArrayList<>();

    /**
	 * Creates instance of sale.
	 */
    public Sale () {
    	this.time = LocalTime.now();
    	this.items = new ArrayList<>();
    	this.saleInformation = new SaleDTO(time, 0, 0, this.items);
    }

    /**
	 * getter for the list if items in a sale
	 * @return items an arraylist containing all items.
	 */
    public List<Item> getItems() {
    	return this.items;
    }

    /**
    * Returns the quantity of items that customer has bought
    * @return customerItemsQuantity quantity of items the customer wants to buy.
    */
    public List<Integer> getCustomerItemsQuantity(){
    	return this.customerItemsQuantity;
    }

    /**
    * Returns the sale information for the sale.
    * @return saleInformation contains the information of the sale.
    */
    public SaleDTO getSaleInformation() {
        return this.saleInformation;
    }
    
    /**
    * Adds an item to the current sale.
    * @param item the item that is being added.
    * @param quantity the quantity of the item being added.
    */
    public void addItem(Item item, int quantity) {
    	updateTotalVAT(item.getItemDTO().getVAT(), quantity);
    	updateTotalPrice(item.getItemDTO().getPrice(), quantity, (item.getItemDTO().getVAT()));
        isDuplicateItem(item, quantity);
    }
    /**
    * Handles duplicate items by increasing the quantity by one for the given item.
    * @param currentItem the duplicate item.
    * @param quantity the quantity of this item.
    */
    private void isDuplicateItem(Item item, int quantity) {
        boolean found = false;
    	for(Item currentItem : items) {
    		if(currentItem.getBarCode() == item.getBarCode()) {   
    			found = true;
    			customerItemsQuantity.set(items.indexOf(currentItem), (customerItemsQuantity.get(items.indexOf(currentItem)) + quantity));
    		}
    	}
        if(found == false) {
    		updateItems(item);
                customerItemsQuantity.add(quantity);
    	}
    }
    
    /**
    * Returns the receipt.
    * @param sale the sale that is being handled.
    * @return recepit the receipt of the sale.
    */
    public Receipt getReceipt(Sale sale) {
        notifyObserver();
        this.receipt = new Receipt(sale.getSaleInformation());
    	return receipt;
    }
    
    /**
    * This method updates the total price for the sale.
    * @param amount the cost of the item.
    * @param quantity the quantity of the item.
    * @param totalVAT the total VAT
    */
    private void updateTotalPrice(double amount, int quantity, double totalVAT) {
        this.totalPrice += (amount * quantity) + (totalVAT * (double)quantity);
        this.saleInformation = new SaleDTO(this.time, this.totalVAT, this.totalPrice, this.items);
    }

    // ADDED: Allows controller to register revenue observers
public void addObserver(IncomeObserver obs) {
    incomeObs.add(obs);
}
// ADDED: Returns current sale DTO
public SaleDTO getSaleDTO() {
    return saleInformation;
}


    /**
    * Updates the total VAT a sale.
    * @param amount the price of the item.
    * @param quantity the quantity of the item.
    */
    private void updateTotalVAT(double vat, int quantity) {
        this.totalVAT += (vat* quantity);
        this.saleInformation = new SaleDTO(this.time, this.totalVAT, this.totalPrice, this.items);
    }

    /**
    * Adds the item to the arraylist nameOfItems.
    * @param item the item being added to the list.
    */
    private void updateItems(Item item) {
	items.add(item);
        this.saleInformation = new SaleDTO(this.time, this.totalVAT, this.totalPrice, this.items);
    }
    
    
    /**
     * Notifies observer of new sale
     */
    private void notifyObserver(){
        for(IncomeObserver obs : incomeObs){
            obs.updateIncome(this.totalPrice);
        }
    }
    
    /**
     * Observer will be notifyed a new sale has been made
     * @param observer  sale observer
     */
    public void IncomeObs(IncomeObserver observer){
        incomeObs.add(observer);
    }
    
// ADDED: Calculates and returns the total price of the sale
public double calculateTotalPrice() {
    return this.totalPrice;
}
public Receipt generateReceipt(Payment payment) {
    return new Receipt(this.saleInformation, payment);
}

}