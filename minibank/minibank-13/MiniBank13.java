/**
 * MiniBank Project - Lab 13: Generic Repository Pattern & Reflection-based ID Resolution
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

public class MiniBank13 {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Id {
    }

    static class BankException extends RuntimeException {
        BankException(String message) {
            super(message);
        }
    }

    static class AccountNotFoundException extends BankException {
        AccountNotFoundException(String message) {
            super(message);
        }
    }

    static class Account {
        @Id
        private String accountNumber;
        private String holderName;
        private double balance;

        Account(String accountNumber, String holderName, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
        }

        String getAccountNumber() {
            return accountNumber;
        }

        String getHolderName() {
            return holderName;
        }

        double getBalance() {
            return balance;
        }

        @Override
        public String toString() {
            return accountNumber + " | " + holderName + " | " + balance;
        }
    }

    static class SavingsAccount extends Account {
        SavingsAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class CurrentAccount extends Account {
        CurrentAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class Repository<T> {
        private final HashMap<String, T> storage = new HashMap<>();

        private String getId(T entity) {
            Class<?> type = entity.getClass();
            while (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        try {
                            field.setAccessible(true);
                            Object value = field.get(entity);
                            if (value == null) {
                                throw new IllegalArgumentException("ID cannot be null");
                            }
                            return value.toString();
                        } catch (IllegalAccessException e) {
                            throw new IllegalArgumentException("Unable to read ID", e);
                        }
                    }
                }
                type = type.getSuperclass();
            }
            throw new IllegalArgumentException("No field annotated with @Id");
        }

        public T save(T entity) {
            storage.put(getId(entity), entity);
            return entity;
        }

        @SafeVarargs
        public final void saveAll(T... entities) {
            for (T entity : entities) {
                save(entity);
            }
        }

        public T findById(String id) {
            T entity = storage.get(id);
            if (entity == null) {
                throw new AccountNotFoundException("Record not found: " + id);
            }
            return entity;
        }

        public ArrayList<T> findAll() {
            return new ArrayList<>(storage.values());
        }

        public boolean delete(String id) {
            return storage.remove(id) != null;
        }

        public int count() {
            return storage.size();
        }
    }

    static <N extends Number> double average(List<N> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (N number : numbers) {
            total += number.doubleValue();
        }
        return total / numbers.size();
    }

    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        Account a1 = new SavingsAccount("A101", "Bhavya", 10000);
        Account a2 = new CurrentAccount("A102", "Anushka", 15000);
        Account a3 = new SavingsAccount("A103", "Shrusti", 20000);

        repository.saveAll(a1, a2, a3);

        System.out.println("Account found:");
        System.out.println(repository.findById("A101"));

        System.out.println("\nAll accounts:");
        for (Account account : repository.findAll()) {
            System.out.println(account);
        }

        ArrayList<Double> balances = new ArrayList<>();
        for (Account account : repository.findAll()) {
            balances.add(account.getBalance());
        }

        System.out.println("\nAverage balance: " + average(balances));
        System.out.println("Total records: " + repository.count());

        repository.delete("A102");
        System.out.println("Records after deletion: " + repository.count());
    }
}
