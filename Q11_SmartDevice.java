interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();
}

class SmartLight implements SmartDevice {
    private boolean on = false;

    public void turnOn() {
        on = true;
        System.out.println("Smart Light is turned ON");
    }

    public void turnOff() {
        on = false;
        System.out.println("Smart Light is turned OFF");
    }

    public String getStatus() {
        return "Smart Light is " + (on ? "ON" : "OFF");
    }
}

class SmartFan implements SmartDevice {
    private boolean on = false;
    private int speed = 0;

    public void turnOn() {
        on = true;
        speed = 3;
        System.out.println("Smart Fan is turned ON at speed " + speed);
    }

    public void turnOff() {
        on = false;
        speed = 0;
        System.out.println("Smart Fan is turned OFF");
    }

    public String getStatus() {
        return "Smart Fan is " + (on ? "ON (speed " + speed + ")" : "OFF");
    }
}

public class Q11_SmartDevice {
    public static void main(String[] args) {
        SmartDevice light = new SmartLight();   // interface references
        SmartDevice fan = new SmartFan();
        SmartDevice[] devices = { light, fan };

        System.out.println("Initial status:");
        for (SmartDevice d : devices) System.out.println("  " + d.getStatus());

        System.out.println("\nTurning all devices ON:");
        for (SmartDevice d : devices) d.turnOn();

        System.out.println("\nStatus now:");
        for (SmartDevice d : devices) System.out.println("  " + d.getStatus());

        System.out.println("\nTurning light OFF:");
        light.turnOff();

        System.out.println("\nFinal status:");
        for (SmartDevice d : devices) System.out.println("  " + d.getStatus());
    }
}

/*
==================== SAMPLE OUTPUT ====================
Initial status:
  Smart Light is OFF
  Smart Fan is OFF

Turning all devices ON:
Smart Light is turned ON
Smart Fan is turned ON at speed 3

Status now:
  Smart Light is ON
  Smart Fan is ON (speed 3)

Turning light OFF:
Smart Light is turned OFF

Final status:
  Smart Light is OFF
  Smart Fan is ON (speed 3)
=======================================================
*/
