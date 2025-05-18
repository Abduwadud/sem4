package se.kth.iv1350.possystem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ExternalAccountingSystem class.
 */
public class ExternalAccountingSystemTest {
    private ExternalAccountingSystem accountingSystem;

    /**
     * Creates a fresh accounting system before each test.
     */
    @BeforeEach
    public void setUp() {
        accountingSystem = new ExternalAccountingSystem();
    }

    /**
     * Checks that the store balance is updated correctly after a banana sale.
     */
    @Test
    public void testUpdateWithBananEko() {
        double totalPrice = 49.24; // Price for Banan eko 2.15kg
        double initialBalance = accountingSystem.getStoreBalance();
        accountingSystem.update(totalPrice);
        assertEquals(initialBalance + totalPrice, accountingSystem.getStoreBalance(),
                "Balance should increase by the sale amount.");
    }

    /**
     * Checks that the balance stays the same if nothing is paid.
     */
    @Test
    public void testUpdateWithZero() {
        double initialBalance = accountingSystem.getStoreBalance();
        accountingSystem.update(0.0);
        assertEquals(initialBalance, accountingSystem.getStoreBalance(),
                "Balance should not change when updated with 0.");
    }
}