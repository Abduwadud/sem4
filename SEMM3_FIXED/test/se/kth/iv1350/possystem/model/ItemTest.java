package se.kth.iv1350.possystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Item class using "Banan eko 2.15kg" as example item.
 */
public class ItemTest {
    private Item item;
    private ItemDTO itemDTO;
    private final int barCode = 101;
    private final int quantity = 100;
    private final String itemName = "Banan eko 2.15kg";
    private final double price = 49.24;
    private final double VAT = 2.95;

    /**
     * Sets up a fresh item before each test.
     */
    @BeforeEach
    public void setUp() {
        itemDTO = new ItemDTO(itemName, price, VAT, barCode);
        item = new Item(barCode, itemDTO, quantity);
    }

    /**
     * Checks that getItemDTO() returns the correct object and values.
     */
    @Test
    public void testGetItemDTO() {
        ItemDTO result = item.getItemDTO();
        assertEquals(itemDTO, result, "getItemDTO() returned wrong object.");
        assertEquals(itemName, result.getItemName(), "Wrong item name.");
        assertEquals(price, result.getPrice(), 0.001, "Wrong price.");
    }

    /**
     * Checks that the barcode is returned correctly.
     */
    @Test
    public void testGetBarCode() {
        int result = item.getBarCode();
        assertEquals(barCode, result, "getBarCode() returned the wrong value.");
    }

    /**
     * Checks that the store quantity is set and returned correctly.
     */
    @Test
    public void testGetStoreQuantity() {
        int result = item.getStoreQuantity();
        assertEquals(quantity, result, "Initial quantity is incorrect.");
    }

    /**
     * Verifies that the quantity is updated correctly after a sale.
     */
    @Test
    public void testUpdateQuantity() {
        int amountSold = 3;
        item.updateQuantity(amountSold);
        int result = item.getStoreQuantity();
        assertEquals(quantity - amountSold, result, "Quantity wasn't reduced as expected.");
    }
}
