/**
 * Practical Lab-05: Practical 1
 * Problem: Shape areas using abstract class Shape, polymorphism, and tracking largest area.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return 3.14 * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }
}

public class ShapeDrive {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(21),
            new Rectangle(5, 6),
            new Triangle(5, 6)
        };

        double total = 0;
        double largest = 0;
        String label = "";

        for (Shape s : shapes) {
            double a = s.area();
            total += a;
            System.out.println("Area is " + a);
            if (largest < a) {
                largest = a;
                label = s.getClass().getSimpleName();
            }
        }

        System.out.println("Largest shape is : " + label);
    }
}

