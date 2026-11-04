/**
 * Practical Lab-13: Practical 2
 * Problem: Bounded type generic method <N extends Number> for statistical average calculation.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

public class BoundedStats {
    // Bounded generic method accepting any subclass of java.lang.Number
    static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (N value : values) {
            sum += value.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] a = {10, 20, 30};
        Double[] b = {2.5, 3.5, 4.5};
        Long[] c = {100L, 200L, 300L};

        System.out.println("Integer average: " + average(a));
        System.out.println("Double average: " + average(b));
        System.out.println("Long average: " + average(c));
    }
}
