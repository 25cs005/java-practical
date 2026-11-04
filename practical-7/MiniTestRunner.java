/**
 * Practical Lab-07: Practical 2
 * Problem: Mini Test Runner using custom @Run annotation and Java Reflection.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class MyTests {
    @Run
    public void test1() {
        System.out.println("Test 1 passed");
    }

    @Run
    public void test2() {
        System.out.println("Test 2 passed");
    }

    public void normalMethod() {
        System.out.println("Not a test");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        MyTests obj = new MyTests();
        int count = 0;

        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Run.class) && m.getParameterCount() == 0) {
                m.invoke(obj);
                count++;
            }
        }

        System.out.println("Tests run: " + count);
    }
}
