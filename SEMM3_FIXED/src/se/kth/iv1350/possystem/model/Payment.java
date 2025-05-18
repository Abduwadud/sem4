package se.kth.iv1350.possystem.model;

/**
 *
 */
public class Payment {
    private double change;
    private String message;

    public Payment(double change, String message) {
        this.change = change;
        this.message = message;
    }

    public double getChange() {
        return change;
    }

    public String getMessage() {
        return message;
    }
    

}