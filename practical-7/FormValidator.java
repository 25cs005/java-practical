/**
 * Practical Lab-07: Practical 1
 * Problem: Form Validator using custom annotations (@NotBlank, @MaxLength) and Java Reflection.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    SignupForm(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class FormValidator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        SignupForm form = new SignupForm(name, email);
        int errors = 0;

        for (Field f : form.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            String value = (String) f.get(form);

            if (f.isAnnotationPresent(NotBlank.class)
                    && (value == null || value.trim().isEmpty())) {
                System.out.println(f.getName() + " cannot be blank");
                errors++;
            }
            if (f.isAnnotationPresent(MaxLength.class)
                    && value != null && value.length() > f.getAnnotation(MaxLength.class).value()) {
                System.out.println(f.getName() + " is too long");
                errors++;
            }
        }

        if (errors == 0) {
            System.out.println("Form is valid");
        }

        sc.close();
    }
}
