/**
 * Practical Lab-06: Practical 1
 * Problem: Remote Control interface with default methods, anonymous class, and lambda expressions.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

interface Switchable {
    void on();
    void off();
    boolean isOn();
    String getName();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

class Fan implements Switchable {
    private boolean state = false;

    @Override
    public void on() {
        state = true;
        System.out.println("Fan is ON");
    }

    @Override
    public void off() {
        state = false;
        System.out.println("Fan is OFF");
    }

    @Override
    public boolean isOn() {
        return state;
    }

    @Override
    public String getName() {
        return "Fan";
    }
}

class Light implements Switchable {
    private boolean state = false;

    @Override
    public void on() {
        state = true;
        System.out.println("Light is ON");
    }

    @Override
    public void off() {
        state = false;
        System.out.println("Light is OFF");
    }

    @Override
    public boolean isOn() {
        return state;
    }

    @Override
    public String getName() {
        return "Light";
    }
}

@FunctionalInterface
interface SwitchOnRule {
    boolean maySwitchOn(Switchable device, int hour);
}

class DayTimeRule implements SwitchOnRule {
    @Override
    public boolean maySwitchOn(Switchable s, int hour) {
        return (hour >= 6 && hour <= 22);
    }
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] s = new Switchable[2];
        s[0] = new Fan();
        s[1] = new Light();

        for (int i = 0; i < s.length; i++) {
            s[i].toggle();
        }
        for (int i = 0; i < s.length; i++) {
            s[i].toggle();
        }

        SwitchOnRule rule = new DayTimeRule();
        if (rule.maySwitchOn(s[0], 3)) {
            s[0].on();
        } else {
            System.out.println("cannot on the device" + s[0].getName());
        }

        // Anonymous class demonstration
        SwitchOnRule nightRule = new SwitchOnRule() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return (hour >= 20 || hour <= 6);
            }
        };

        // Lambda demonstration
        SwitchOnRule lambdaRule = (device, hour) -> (hour >= 6 && hour <= 22);
    }
}
