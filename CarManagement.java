import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CarManagement {
    public void manageCars() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Car Management ===");
        System.out.println("1. Add Car");
        System.out.println("2. View Cars");
        System.out.println("3. Update Car Availability");
        System.out.print("Enter your choice (1, 2, or 3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addCar(scanner);
            case 2 -> viewCars();
            case 3 -> updateCarAvailability(scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addCar(Scanner scanner) {
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Price Per Day: ");
        double pricePerDay = scanner.nextDouble();
        boolean availability = true;

        String query = "INSERT INTO cars (make, model, year, price_per_day, availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, make);
            statement.setString(2, model);
            statement.setInt(3, year);
            statement.setDouble(4, pricePerDay);
            statement.setBoolean(5, availability);
            statement.executeUpdate();
            System.out.println("Car added successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void viewCars() {
        String query = "SELECT * FROM cars";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                System.out.println("Car ID: " + rs.getInt("car_id"));
                System.out.println("Make: " + rs.getString("make"));
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Year: " + rs.getInt("year"));
                System.out.println("Price Per Day: " + rs.getDouble("price_per_day"));
                System.out.println("Available: " + rs.getBoolean("availability"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void updateCarAvailability(Scanner scanner) {
        System.out.print("Enter Car ID: ");
        int carId = scanner.nextInt();
        System.out.print("Enter Availability (true/false): ");
        boolean availability = scanner.nextBoolean();

        String query = "UPDATE cars SET availability = ? WHERE car_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, availability);
            statement.setInt(2, carId);
            statement.executeUpdate();
            System.out.println("Car availability updated.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
