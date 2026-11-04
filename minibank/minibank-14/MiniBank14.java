/**
 * MiniBank Project - Lab 14: Final Capstone Assembly & Layered Architecture
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.*;

public class MiniBank14 {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Id {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Positive {
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

    static class InsufficientBalanceException extends BankException {
        InsufficientBalanceException(String message) {
            super(message);
        }
    }

    static class InvalidAmountException extends BankException {
        InvalidAmountException(String message) {
            super(message);
        }
    }

    static class Account implements Serializable, Comparable<Account> {
        private static final long serialVersionUID = 1L;

        @Id
        private String accountNumber;
        private String holderName;

        @Positive
        private double balance;

        Account(String accountNumber, String holderName, double balance) {
            if (accountNumber == null || accountNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Account number is required");
            }
            if (holderName == null || holderName.trim().isEmpty()) {
                throw new IllegalArgumentException("Holder name is required");
            }
            if (!Double.isFinite(balance) || balance < 0) {
                throw new InvalidAmountException("Invalid initial balance");
            }
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

        synchronized double getBalance() {
            return balance;
        }

        synchronized void deposit(double amount) {
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new InvalidAmountException("Amount must be positive");
            }
            balance += amount;
        }

        synchronized void withdraw(double amount) {
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new InvalidAmountException("Amount must be positive");
            }
            if (balance < amount) {
                throw new InsufficientBalanceException("Insufficient balance");
            }
            balance -= amount;
        }

        @Override
        public int compareTo(Account other) {
            return accountNumber.compareTo(other.accountNumber);
        }

        @Override
        public String toString() {
            return accountNumber + " | " + holderName + " | " + getBalance();
        }
    }

    static class SavingsAccount extends Account {
        private static final long serialVersionUID = 1L;

        SavingsAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class CurrentAccount extends Account {
        private static final long serialVersionUID = 1L;

        CurrentAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class AnnotationValidator {
        static void validate(Object object) {
            Class<?> type = object.getClass();
            while (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(object);
                        if (field.isAnnotationPresent(Id.class)) {
                            if (value == null || value.toString().trim().isEmpty()) {
                                throw new IllegalArgumentException("ID cannot be empty");
                            }
                        }
                        if (field.isAnnotationPresent(Positive.class)) {
                            if (!(value instanceof Number)
                                    || !Double.isFinite(((Number) value).doubleValue())
                                    || ((Number) value).doubleValue() < 0) {
                                throw new InvalidAmountException(
                                        field.getName() + " cannot be negative");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("Validation failed", e);
                    }
                }
                type = type.getSuperclass();
            }
        }
    }

    static class Repository<T> {
        private final HashMap<String, T> records = new HashMap<>();

        private String getId(T object) {
            Class<?> type = object.getClass();
            while (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        try {
                            field.setAccessible(true);
                            Object value = field.get(object);
                            if (value == null) {
                                throw new IllegalArgumentException("ID cannot be null");
                            }
                            return value.toString();
                        } catch (IllegalAccessException e) {
                            throw new IllegalArgumentException("Cannot read ID", e);
                        }
                    }
                }
                type = type.getSuperclass();
            }
            throw new IllegalArgumentException("No @Id field found");
        }

        synchronized void save(T object) {
            records.put(getId(object), object);
        }

        @SafeVarargs
        final synchronized void saveAll(T... objects) {
            for (T object : objects) {
                save(object);
            }
        }

        synchronized T findById(String id) {
            T object = records.get(id);
            if (object == null) {
                throw new AccountNotFoundException("Account not found: " + id);
            }
            return object;
        }

        synchronized ArrayList<T> findAll() {
            return new ArrayList<>(records.values());
        }

        synchronized boolean exists(String id) {
            return records.containsKey(id);
        }

        synchronized boolean delete(String id) {
            return records.remove(id) != null;
        }
    }

    static class AccountStore {
        private final ConcurrentHashMap<String, Account> accounts
                = new ConcurrentHashMap<>();

        void add(Account account) {
            Account previous = accounts.putIfAbsent(
                    account.getAccountNumber(), account);
            if (previous != null) {
                throw new BankException("Account already exists");
            }
        }

        Account get(String number) {
            Account account = accounts.get(number);
            if (account == null) {
                throw new AccountNotFoundException("Account not found: " + number);
            }
            return account;
        }

        ArrayList<Account> getAll() {
            ArrayList<Account> result = new ArrayList<>(accounts.values());
            Collections.sort(result);
            return result;
        }

        boolean contains(String number) {
            return accounts.containsKey(number);
        }
    }

    static class StatePersister {
        static void save(AccountStore store, Path path) throws IOException {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(path))) {
                out.writeObject(store.getAll());
            }
        }

        static ArrayList<Account> load(Path path)
                throws IOException, ClassNotFoundException {
            try (ObjectInputStream in = new ObjectInputStream(
                    Files.newInputStream(path))) {
                Object saved = in.readObject();
                if (saved instanceof ArrayList<?>) {
                    ArrayList<Account> accounts = new ArrayList<>();
                    for (Object item : (ArrayList<?>) saved) {
                        if (!(item instanceof Account)) {
                            throw new IOException("Invalid account data in file");
                        }
                        accounts.add((Account) item);
                    }
                    return accounts;
                }
                if (saved instanceof Account[]) {
                    return new ArrayList<>(
                            Arrays.asList((Account[]) saved));
                }
                throw new IOException("Unsupported saved data format");
            }
        }
    }

    static class TransactionLog {
        private static final Path LOG_FILE
                = Paths.get("bank-data", "logs", "transactions.txt");

        static synchronized void append(String accountNumber, String type,
                double amount) throws IOException {
            Path parent = LOG_FILE.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            String line = accountNumber + "," + type + "," + amount
                    + System.lineSeparator();
            Files.write(LOG_FILE, line.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    static class ReportGenerator {
        static void generate() throws IOException {
            Path directory = Paths.get("bank-data", "logs");
            Path report = Paths.get("bank-data", "report.txt");
            Files.createDirectories(directory);
            double deposits = 0;
            double withdrawals = 0;
            StringBuilder output = new StringBuilder();

            try (DirectoryStream<Path> files
                    = Files.newDirectoryStream(directory)) {
                for (Path file : files) {
                    if (!Files.isRegularFile(file)) {
                        continue;
                    }
                    try (BufferedReader reader = Files.newBufferedReader(file)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts.length != 3) {
                                continue;
                            }
                            try {
                                double amount = Double.parseDouble(parts[2]);
                                if (parts[1].equalsIgnoreCase("DEPOSIT")) {
                                    deposits += amount;
                                } else if (parts[1].equalsIgnoreCase("WITHDRAW")) {
                                    withdrawals += amount;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                    BasicFileAttributes attributes
                            = Files.readAttributes(file, BasicFileAttributes.class);
                    output.append(file.getFileName())
                            .append(" | Size: ")
                            .append(attributes.size())
                            .append(" bytes | Modified: ")
                            .append(attributes.lastModifiedTime())
                            .append("\n");
                }
            }
            output.append("Total Deposits: ").append(deposits).append("\n");
            output.append("Total Withdrawals: ").append(withdrawals).append("\n");
            Files.write(report, output.toString().getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println(output);
        }
    }

    static class BankService {
        private final Repository<Account> repository = new Repository<>();
        private final AccountStore store = new AccountStore();

        BankService() {
            Path dataFile = Paths.get("bank-data", "accounts.ser");
            try {
                if (Files.exists(dataFile)) {
                    for (Account account : StatePersister.load(dataFile)) {
                        AnnotationValidator.validate(account);
                        repository.save(account);
                        store.add(account);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new BankException("Unable to load saved accounts: "
                        + e.getMessage());
            }
        }

        synchronized void openAccount(String number, String name,
                double initialBalance) throws IOException {
            if (store.contains(number)) {
                throw new BankException("Account already exists");
            }
            Account account = new SavingsAccount(number, name, initialBalance);
            AnnotationValidator.validate(account);
            repository.save(account);
            store.add(account);
            TransactionLog.append(number, "OPEN", initialBalance);
            System.out.println("Account created successfully");
        }

        void deposit(String number, double amount) throws IOException {
            Account account = store.get(number);
            account.deposit(amount);
            TransactionLog.append(number, "DEPOSIT", amount);
            System.out.println("Deposit successful");
        }

        void withdraw(String number, double amount) throws IOException {
            Account account = store.get(number);
            account.withdraw(amount);
            TransactionLog.append(number, "WITHDRAW", amount);
            System.out.println("Withdrawal successful");
        }

        void transfer(String fromNumber, String toNumber,
                double amount) throws IOException {
            if (fromNumber.equals(toNumber)) {
                throw new BankException("Source and destination must differ");
            }
            Account from = store.get(fromNumber);
            Account to = store.get(toNumber);

            Account first = fromNumber.compareTo(toNumber) < 0 ? from : to;
            Account second = first == from ? to : from;

            synchronized (first) {
                synchronized (second) {
                    if (!Double.isFinite(amount) || amount <= 0) {
                        throw new InvalidAmountException("Amount must be positive");
                    }
                    if (from.getBalance() < amount) {
                        throw new InsufficientBalanceException(
                                "Insufficient balance");
                    }
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
            TransactionLog.append(fromNumber, "WITHDRAW", amount);
            TransactionLog.append(toNumber, "DEPOSIT", amount);
            System.out.println("Transfer successful");
        }

        void statement(String number) {
            System.out.println(store.get(number));
        }

        void listAccounts() {
            ArrayList<Account> accounts = store.getAll();
            if (accounts.isEmpty()) {
                System.out.println("No accounts available");
                return;
            }
            for (Account account : accounts) {
                System.out.println(account);
            }
        }

        void batchDeposit(String number, double amount, int count)
                throws InterruptedException {
            if (count <= 0) {
                throw new InvalidAmountException(
                        "Number of deposits must be positive");
            }
            Account account = store.get(number);
            ExecutorService executor = Executors.newFixedThreadPool(3);
            try {
                for (int i = 0; i < count; i++) {
                    executor.submit(() -> {
                        try {
                            account.deposit(amount);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                }
            } finally {
                executor.shutdown();
            }
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                throw new BankException("Batch operation timed out");
            }
            System.out.println("Batch deposits completed");
        }

        void save() throws IOException {
            StatePersister.save(store,
                    Paths.get("bank-data", "accounts.ser"));
        }
    }

    public static void main(String[] args) {
        BankService service;
        try {
            service = new BankService();
        } catch (BankException e) {
            System.out.println(e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMINI BANK");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Generate Report");
            System.out.println("8. Batch Deposit");
            System.out.println("9. Save and Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.print("Account number: ");
                        String number = scanner.nextLine();
                        System.out.print("Holder name: ");
                        String name = scanner.nextLine();
                        System.out.print("Initial balance: ");
                        double initialBalance
                                = Double.parseDouble(scanner.nextLine());
                        service.openAccount(number, name, initialBalance);
                        break;
                    }
                    case 2: {
                        System.out.print("Account number: ");
                        String number = scanner.nextLine();
                        System.out.print("Deposit amount: ");
                        double depositAmount
                                = Double.parseDouble(scanner.nextLine());
                        service.deposit(number, depositAmount);
                        break;
                    }
                    case 3: {
                        System.out.print("Account number: ");
                        String number = scanner.nextLine();
                        System.out.print("Withdrawal amount: ");
                        double withdrawalAmount
                                = Double.parseDouble(scanner.nextLine());
                        service.withdraw(number, withdrawalAmount);
                        break;
                    }
                    case 4: {
                        System.out.print("Source account: ");
                        String from = scanner.nextLine();
                        System.out.print("Destination account: ");
                        String to = scanner.nextLine();
                        System.out.print("Transfer amount: ");
                        double transferAmount
                                = Double.parseDouble(scanner.nextLine());
                        service.transfer(from, to, transferAmount);
                        break;
                    }
                    case 5: {
                        System.out.print("Account number: ");
                        service.statement(scanner.nextLine());
                        break;
                    }
                    case 6:
                        service.listAccounts();
                        break;
                    case 7:
                        ReportGenerator.generate();
                        break;
                    case 8: {
                        System.out.print("Account number: ");
                        String number = scanner.nextLine();
                        System.out.print("Amount per deposit: ");
                        double batchAmount
                                = Double.parseDouble(scanner.nextLine());
                        System.out.print("Number of deposits: ");
                        int count = Integer.parseInt(scanner.nextLine());
                        service.batchDeposit(number, batchAmount, count);
                        break;
                    }
                    case 9:
                        service.save();
                        running = false;
                        System.out.println("Data saved. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            } catch (BankException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Operation interrupted");
            }
        }
        scanner.close();
    }
}
