/**
 * Practical Lab-11: Practical 2
 * Problem: Log analyzer using java.nio.file.Path, Files, and BasicFileAttributes.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) {
        try {
            // Create test log files
            Files.write(Paths.get("log1.txt"),
                Arrays.asList("Java is easy", "Hello World", "Java programming"));
            Files.write(Paths.get("log2.txt"),
                Arrays.asList("Python programming", "Java is powerful"));

            String[] files = {"log1.txt", "log2.txt"};
            String keyword = "Java";
            int totalLines = 0;
            int matches = 0;

            for (String file : files) {
                Path path = Paths.get(file);
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    totalLines++;
                    if (line.contains(keyword)) {
                        matches++;
                    }
                }
                BasicFileAttributes attr = Files.readAttributes(
                    path, BasicFileAttributes.class);
                System.out.println(file + " Size: " + attr.size() + " bytes");
            }

            System.out.println("Total lines: " + totalLines);
            System.out.println("Lines containing " + keyword + ": " + matches);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
