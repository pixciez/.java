import java.util.InputMismatchException;
import java.util.Scanner;

class TicketBooking extends Thread {
    private static int availableSeats;
    private final boolean isVIP;

    public TicketBooking(String name, boolean isVIP) {
        super(name);
        this.isVIP = isVIP;
    }

    public static void setAvailableSeats(int seats) {
        availableSeats = seats;
    }

    public void run() {
        synchronized (TicketBooking.class) {
            if (availableSeats > 0) {
                System.out.println(getName() + " booked a seat. (" + --availableSeats + " seats remaining)");
            } else {
                System.out.println(getName() + " could not book a seat. No seats available.");
            }
        }
    }
}

public class Ticket_Booking_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seats = 0, numPassengers = 0;

        // Get number of available seats
        while (true) {
            try {
                System.out.print("\nEnter the number of available seats: ");
                seats = scanner.nextInt();
                if (seats < 0) throw new IllegalArgumentException("Seat count cannot be negative.");
                TicketBooking.setAvailableSeats(seats);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Get number of passengers
        while (true) {
            try {
                System.out.print("Enter the number of passengers: ");
                numPassengers = scanner.nextInt();
                if (numPassengers <= 0) throw new IllegalArgumentException("Number of passengers must be at least 1.");
                scanner.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        TicketBooking[] passengers = new TicketBooking[numPassengers];

        System.out.println("\n===== Passenger Details =====");
        for (int i = 0; i < numPassengers; i++) {
            String name;
            boolean isVIP;

            while (true) {
                try {
                    System.out.print("Enter passenger name: ");
                    name = scanner.nextLine().trim();
                    if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Is this passenger VIP? (y/n): ");
                    String vipInput = scanner.nextLine().trim().toLowerCase();
                    if (!vipInput.equals("y") && !vipInput.equals("n"))
                        throw new IllegalArgumentException("Please enter 'y' for VIP or 'n' for regular.");
                    isVIP = vipInput.equals("y");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            passengers[i] = new TicketBooking(name, isVIP);
            if (isVIP) {
                passengers[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                passengers[i].setPriority(Thread.NORM_PRIORITY);
            }
        }

        System.out.println("\n===== Ticket Booking Process =====");
        for (TicketBooking passenger : passengers) {
            passenger.start();
        }

        scanner.close();
    }
}
