import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookingManagement {
    public void manageBookings() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Booking Management ===");
        System.out.println("1. Book a Car");
        System.out.println("2. View Bookings");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            bookCar(scanner);
        } else if (choice == 2) {
            viewBookings();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void bookCar(Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Car ID: ");
        int carId = scanner.nextInt();
        System.out.print("Enter Number of Days: ");
        int days = scanner.nextInt();

        String query = "INSERT INTO bookings (user_id, car_id, days, total_price) " +
                       "SELECT ?, ?, ?, price_per_day * ? FROM cars WHERE car_id = ? AND availability = true";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, carId);
            statement.setInt(3, days);
            statement.setInt(4, days);
            statement.setInt(5, carId);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Car booked successfully.");
            } else {
                System.out.println("Car booking failed. Car might not be available.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void viewBookings() {
        String query = "SELECT * FROM bookings";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id"));
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Car ID: " + rs.getInt("car_id"));
                System.out.println("Days: " + rs.getInt("days"));
                System.out.println("Total Price: " + rs.getDouble("total_price"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
