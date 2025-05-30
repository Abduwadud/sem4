package se.kth.iv1350.possystem.startup;

import se.kth.iv1350.possystem.controller.Controller;
import se.kth.iv1350.possystem.integration.ExternalAccountingSystem;
import se.kth.iv1350.possystem.integration.ExternalInventorySystem;
import se.kth.iv1350.possystem.integration.Printer;
import se.kth.iv1350.possystem.view.View;


/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the application.
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
    	
        Printer printer = new Printer();
        ExternalAccountingSystem accounting = new ExternalAccountingSystem();
        ExternalInventorySystem inventory = new ExternalInventorySystem();
        Controller contr = new Controller(inventory, accounting, printer); // FIXED

        View view = new View(contr);
        
        view.runFakeExecution();
    }
}