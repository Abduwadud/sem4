package se.kth.iv1350.possystem.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.possystem.controller.Controller;
import se.kth.iv1350.possystem.integration.ExternalAccountingSystem;
import se.kth.iv1350.possystem.integration.ExternalInventorySystem;
import se.kth.iv1350.possystem.integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the View class by running a full sale simulation
 * and checking that no exceptions are thrown.
 */
public class ViewTest {

    private View view;

    @BeforeEach
    public void setUp() {
        Printer printer = new Printer();
        ExternalAccountingSystem accounting = new ExternalAccountingSystem();
        ExternalInventorySystem inventory = new ExternalInventorySystem();
        inventory.initializeStoreItems(); // Load the items into inventory

        Controller controller = new Controller(printer, accounting, inventory);
        view = new View(controller);
    }

    /**
     * Makes sure that runFakeExecution() completes without crashing.
     */
    @Test
    public void testRunFakeExecutionDoesNotCrash() {
        assertDoesNotThrow(() -> view.runFakeExecution(), "runFakeExecution() should run without throwing anything.");
    }
}