/**
 * Practical Lab-12: Practical 1
 * Problem: Word frequency counter using HashMap and Comparator-based sorting.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is easy and java is powerful";
        String[] words = text.toLowerCase().split("\\W+");
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        // Sort by frequency (descending), then alphabetically
        list.sort((a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            return result != 0 ? result : a.getKey().compareTo(b.getKey());
        });

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

