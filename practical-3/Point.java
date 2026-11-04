/**
 * Practical Lab-03: Practical 1
 * Problem: Distinct Points counter using toString(), equals(), and hashCode().
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Objects;
import java.util.Scanner;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point) {
            Point temp = (Point) obj;
            return this.x == temp.x && this.y == temp.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        Driver.main(args);
    }
}

class Driver {
    public static void main(String[] args) {
        Point[] p = new Point[5];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < p.length; i++) {
            System.out.println("Enter x and y coordinates for point " + (i + 1) + ":");
            int x = sc.nextInt();
            int y = sc.nextInt();
            p[i] = new Point(x, y);
        }

        int count = 0;
        for (int i = 0; i < p.length; i++) {
            boolean duplicate = false;
            for (int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                count++;
            }
        }

        System.out.println("Total number of distinct points: " + count);
        sc.close();
    }
}

