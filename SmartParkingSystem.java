import java.util.*;

class ParkingLot {
    private int totalSlots;

    ParkingLot(int slots) {
        this.totalSlots = slots;
    }

      synchronized void enterParking(String vehicle) {
        System.out.println(vehicle + " trying to enter...");
        while (totalSlots == 0) {
            System.out.println("No slots available for " + vehicle + ". Waiting...");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(vehicle + " interrupted.");
            }
        }
        totalSlots--;
        System.out.println(vehicle + " parked. Slots left: " + totalSlots);
    }

     void exitParking(String vehicle) {
        System.out.println(vehicle + " leaving...");
        totalSlots++;
        System.out.println(vehicle + " exited. Slots left: " + totalSlots);
        notifyAll();
    }

    public synchronized int getAvailableSlots() {
        return totalSlots;
    }
}

class Vehicle implements Runnable {
    private ParkingLot lot;
    private String name;

    Vehicle(ParkingLot lot, String name) {
        this.lot = lot;
        this.name = name;
    }

    public void run() {
        lot.enterParking(name);
        try {
            Thread.sleep(3000); // Simulate parking duration
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted while parked.");
        }
        lot.exitParking(name);
    }
}

class Monitor extends Thread {
    private ParkingLot lot;

    Monitor(ParkingLot lot) {
        this.lot = lot;
        setDaemon(true);
    }

    public void run() {
        while (true) {
            System.out.println("[Monitor] System is running...");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Monitor interrupted");
            }
        }
    }
}

class Admin implements Runnable {
    private ParkingLot lot;

    Admin(ParkingLot lot) {
        this.lot = lot;
    }

    public void run() {
        while (true) {
            System.out.println("[Admin] Available parking slots: " + lot.getAvailableSlots());
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                System.out.println("Admin interrupted");
            }
        }
    }
}

public class SmartParkingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total parking slots: ");
        int slots = sc.nextInt();
        sc.nextLine(); // consume newline

        ParkingLot lot = new ParkingLot(slots);

        System.out.print("Enter number of vehicles: ");
        int vehicleCount = sc.nextInt();
        sc.nextLine(); // consume newline

        Thread[] vehicles = new Thread[vehicleCount];

        for (int i = 0; i < vehicleCount; i++) {
            System.out.print("Enter name of vehicle " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter vehicle type (normal/emergency/service): ");
            String type = sc.nextLine().trim().toLowerCase();

            vehicles[i] = new Thread(new Vehicle(lot, name));

            switch (type) {
                case "emergency":
                    vehicles[i].setPriority(Thread.MAX_PRIORITY);
                    break;
                case "service":
                    vehicles[i].setPriority(Thread.MIN_PRIORITY);
                    break;
                default:
                    vehicles[i].setPriority(Thread.NORM_PRIORITY);
            }
        }

        new Monitor(lot).start();
        Thread adminThread = new Thread(new Admin(lot));
        adminThread.setDaemon(true);
        adminThread.start();

        for (Thread v : vehicles) {
            v.start();
        }

        for (Thread v : vehicles) {
            try {
                v.join();
            } catch (InterruptedException e) {
                System.out.println("Main interrupted");
            }
        }

        System.out.println("All vehicles processed. System shutting down.");
        sc.close();
    }
}
