/**
 * Practical Lab-12: Practical 2
 * Problem: Unique visitor tracking using HashSet and LinkedHashSet.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        String[] users = {
            "Amit", "Riya", "Jay", "Amit", "Neha", "Riya"
        };

        HashSet<String> set = new HashSet<>();
        LinkedHashSet<String> orderedSet = new LinkedHashSet<>();

        for (String user : users) {
            set.add(user);
            orderedSet.add(user);
        }

        System.out.println("Unique users: " + set.size());
        System.out.println("First three unique users:");
        int count = 0;
        for (String user : orderedSet) {
            if (count == 3) {
                break;
            }
            System.out.println(user);
            count++;
        }
    }
}

