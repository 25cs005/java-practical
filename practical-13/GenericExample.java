/**
 * Practical Lab-13: Practical 1
 * Problem: Generic containers Box<T>, Pair<A, B>, and generic methods.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    void display() {
        System.out.println(first + " " + second);
    }
}

public class GenericExample {
    // Generic method to return first element of any array
    static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> b1 = new Box<>();
        b1.set(100);
        System.out.println(b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Hello Java");
        System.out.println(b2.get());

        Pair<String, Integer> p = new Pair<>("Amit", 20);
        p.display();

        Integer[] numbers = {10, 20, 30};
        String[] names = {"Riya", "Jay", "Amit"};

        System.out.println(firstElement(numbers));
        System.out.println(firstElement(names));
    }
}

