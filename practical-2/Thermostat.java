/**
 * Practical Lab-02: Practical 1
 * Problem: Smart Thermostat simulation with constructors, static count, and bounds checking.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    // Parameterized constructor
    public Thermostat(String location, int startTemp) {
        this.location = location;
        if (startTemp >= MIN && startTemp <= MAX) {
            this.temperature = startTemp;
        } else {
            this.temperature = 22; // default safe temperature
        }
        activeCount++;
    }

    // Chained constructor with default temperature
    public Thermostat(String location) {
        this(location, 22);
    }

    public void raise() {
        if (temperature < MAX) {
            temperature++;
        } else {
            System.out.println("Already at maximum (30)");
        }
    }

    public void lower() {
        if (temperature > MIN) {
            temperature--;
        } else {
            System.out.println("Already at minimum (16)");
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public String getLocation() {
        return location;
    }

    public static int getActiveCount() {
        return activeCount;
    }

    public static void main(String[] args) {
        ThermostatDemo.main(args);
    }
}

class ThermostatDemo {
    public static void main(String[] args) {
        Thermostat t1 = new Thermostat("Living Room", 20);
        Thermostat t2 = new Thermostat("Bedroom", 25);

        System.out.println("Raising Temperature:");
        for (int i = 0; i < 10; i++) {
            t1.raise();
            System.out.println("t1 Temperature = " + t1.getTemperature());
            t2.raise();
            System.out.println("t2 Temperature = " + t2.getTemperature());
        }

        System.out.println("\nLowering Temperature:");
        for (int i = 0; i < 20; i++) {
            t1.lower();
            System.out.println("t1 Temperature = " + t1.getTemperature());
            t2.lower();
            System.out.println("t2 Temperature = " + t2.getTemperature());
        }

        System.out.println("\nActive Count = " + Thermostat.getActiveCount());
    }
}
