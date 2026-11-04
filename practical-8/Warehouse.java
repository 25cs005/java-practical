/**
 * Practical Lab-08: Practical 2
 * Problem: Warehouse Stock Issue with custom OutOfStockException and InvalidQuantityException.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.*;

class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        super("Out of stock! Shortfall = " + shortfall);
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

public class Warehouse {
    int stock = 10;

    void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Invalid quantity for " + item);
        }
        if (qty > stock) {
            int shortfall = qty - stock;
            throw new OutOfStockException(shortfall);
        }
        stock = stock - qty;
        System.out.println("Issued " + qty + " " + item + ", Remaining stock = " + stock);
    }

    public static void main(String[] args) {
        Stock.main(args);
    }
}

class Stock {
    public static void main(String[] args) {
        Warehouse w = new Warehouse();
        String[] items = { "Pen", "Book", "Pencil", "Eraser" };
        int[] quantities = { 3, 8, -2, 5 };

        for (int i = 0; i < quantities.length; i++) {
            try {
                w.issue(items[i], quantities[i]);
            } catch (OutOfStockException | InvalidQuantityException e) {
                System.out.println("Failed: " + items[i] + " - " + e.getMessage());
            }
        }
        System.out.println("All requests processed.");
    }
}
