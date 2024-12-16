import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManagement userManagement = new UserManagement();
        CarManagement carManagement = new CarManagement();
        BookingManagement bookingManagement = new BookingManagement();

        while (true) {
            System.out.println("=== Rental Car System ===");
            System.out.println("1. User Management");
            System.out.println("2. Car Management");
            System.out.println("3. Booking Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> userManagement.manageUser();
                case 2 -> carManagement.manageCars();
                case 3 -> bookingManagement.manageBookings();
                case 4 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
