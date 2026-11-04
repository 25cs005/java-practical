/**
 * Practical Lab-04: Practical 2
 * Problem: Chat-log filter using String split, case-insensitive search, and StringBuilder.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:10 bob How are you",
            "10:15 Charlie"
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        StringBuilder report = new StringBuilder();
        int count = 0;

        for (String line : logs) {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) {
                continue; // skip malformed lines with fewer than 3 parts
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                report.append(time)
                      .append(" ")
                      .append(user)
                      .append(": ")
                      .append(message)
                      .append("\n");
            }
        }

        System.out.println("Matches: " + count);
        System.out.print(report);
        sc.close();
    }
}

