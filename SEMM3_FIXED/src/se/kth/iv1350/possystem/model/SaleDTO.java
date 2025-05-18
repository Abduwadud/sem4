package se.kth.iv1350.possystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sale data transfer object
 */
public class SaleDTO {
	private java.time.LocalTime time;
	private double totalVAT;
	private double totalPrice;
	private List<Item> items;

    /**
    * Creates  instance of SaleDTO.
    * @param time time of the sale
    * @param totalVAT total VAT of a sale
    * @param totalPrice the total Price of a sale
    * @param items the Sale items
    */
    public SaleDTO(java.time.LocalTime time, double totalVAT,
        double totalPrice, List<Item> items) {
	this.time = time;
	this.totalVAT = totalVAT;
	this.totalPrice = totalPrice;
	this.items = new ArrayList<>(items);
    }

    /**
    * Returns the localtime of the sale.
    * @return time returns the local time.
    */
    public java.time.LocalTime getTime() {
        return this.time;
    }


    /**
    * Return total VAT of the sale
    * @return totalVAT, holds the total VAT for the sale
    */
    public double getTotalVAT() {
	return this.totalVAT;
    }

    /**
    * Returns the total Price of the sale
    * @return totalPrice the total price of the sale
    */
    public double getTotalPrice() {
	return this.totalPrice;
    }

    /**
    * Returns a list of items in a sale
    * @return items the items in a sale
    */
    public List<Item> getItems() {
        return new ArrayList<>(this.items);
    }
}