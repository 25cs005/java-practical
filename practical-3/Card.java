/**
 * Practical Lab-03: Practical 2
 * Problem: Duplicate Card detection using toString(), equals(), and hashCode().
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Objects;
import java.util.Scanner;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return (rank + " of " + suit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Card) {
            Card temp = (Card) obj;
            return this.rank.equalsIgnoreCase(temp.rank) && this.suit.equalsIgnoreCase(temp.suit);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank.toLowerCase(), suit.toLowerCase());
    }

    public static void main(String[] args) {
        Driver1.main(args);
    }
}

class Driver1 {
    public static void main(String[] args) {
        Card[] c = new Card[3];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < c.length; i++) {
            System.out.println("Enter rank and suit for card " + (i + 1) + ":");
            String rank = sc.next();
            String suit = sc.next();
            c[i] = new Card(rank, suit);
        }

        System.out.println("Checking for duplicates...");
        for (int i = 0; i < c.length; i++) {
            for (int j = i + 1; j < c.length; j++) {
                if (c[i].equals(c[j])) {
                    System.out.println("Duplicate found: " + c[i]);
                }
            }
        }
        sc.close();
    }
}

