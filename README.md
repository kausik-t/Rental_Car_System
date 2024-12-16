# Rental Car System

## Project Overview
The **Rental Car System** is a Java-based application designed to manage car rentals for customers. It integrates with a MySQL database to store and manage user information, car details, and booking records. The system allows users to perform the following:

- Add users (both admins and customers).
- View available cars.
- Book cars for a specified number of days.
- View all bookings.

---

## Features

1. **User Management**
   - Add new users with roles (admin or customer).
   - Unique usernames for each user.

2. **Car Inventory Management**
   - View available cars.
   - Automatically update car availability upon booking.

3. **Booking System**
   - Book a car for a specific number of days.
   - Calculate total rental cost based on daily price and duration.
   - Maintain booking history.

4. **Database Integration**
   - Use MySQL to manage and persist data.
   - Perform CRUD operations for users, cars, and bookings.

---

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL
- **JDBC Driver**: MySQL Connector/J
- **IDE**: Eclipse or IntelliJ IDEA

---

## Prerequisites

1. Install **MySQL Server**.
2. Add the MySQL JDBC driver (`mysql-connector-j-X.X.X.jar`) to your project.
3. Install a Java Development Kit (JDK) (version 8 or higher).
4. Install an IDE such as Eclipse or IntelliJ IDEA.

---

## Setup Instructions

### Step 1: Database Setup

1. Create the MySQL database and tables:

   ```sql
   CREATE DATABASE rental_car_system;
   USE rental_car_system;

   CREATE TABLE users (
       user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
       username VARCHAR(50) NOT NULL UNIQUE,
       password VARCHAR(50) NOT NULL,
       role VARCHAR(20) NOT NULL
   );

   CREATE TABLE cars (
       car_id INTEGER PRIMARY KEY AUTO_INCREMENT,
       make VARCHAR(50) NOT NULL,
       model VARCHAR(50) NOT NULL,
       year INTEGER NOT NULL,
       price_per_day DOUBLE NOT NULL,
       availability BOOLEAN NOT NULL
   );

   CREATE TABLE bookings (
       booking_id INTEGER PRIMARY KEY AUTO_INCREMENT,
       user_id INTEGER NOT NULL,
       car_id INTEGER NOT NULL,
       days INTEGER NOT NULL,
       total_price DOUBLE NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(user_id),
       FOREIGN KEY (car_id) REFERENCES cars(car_id)
   );
   ```

2. Insert sample data (optional):

   ```sql
   INSERT INTO users (username, password, role) VALUES
       ('admin', 'admin123', 'admin'),
       ('johndoe', 'password1', 'customer'),
       ('janedoe', 'password2', 'customer');

   INSERT INTO cars (make, model, year, price_per_day, availability) VALUES
       ('Toyota', 'Corolla', 2020, 50.0, 1),
       ('Honda', 'Civic', 2019, 55.0, 1),
       ('Ford', 'Mustang', 2021, 120.0, 1);
   ```

### Step 2: Project Setup

1. Clone or download the project source code.
2. Add the MySQL Connector/J JAR file to your project library.
   - In Eclipse: Right-click the project → Build Path → Configure Build Path → Add External JARs.

### Step 3: Configuration

1. Update the `DatabaseConnection.java` file with your database credentials:

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/rental_car_system";
   private static final String USER = "root"; // Replace with your MySQL username
   private static final String PASSWORD = "password"; // Replace with your MySQL password
   ```

2. Compile the project:
   ```Terminal
   javac -cp "lib/mysql-connector-j-X.X.X.jar" -d bin src/*.java
   ```

3. Run the project:
   ```Terminal
   java -cp "lib/mysql-connector-j-X.X.X.jar;bin" MainApplication
   ```

---

## How to Use

### Menu Options

1. **Add User**
   - Provide username, password, and role (admin or customer).

2. **View Available Cars**
   - Displays a list of all cars currently available for booking.

3. **Book a Car**
   - Enter your user ID, car ID, and the number of days you wish to rent the car.
   - The system calculates the total price and marks the car as unavailable.

4. **View Bookings**
   - Displays all bookings along with user and car details.

5. **Exit**
   - Closes the application.

---

## Project Structure

```
Rental Car System/
|-- src/
|   |-- MainApplication.java       # Entry point for the application
|   |-- DatabaseConnection.java    # Database connection logic
|   |-- User.java                  # User model and operations
|   |-- Car.java                   # Car model and operations
|   |-- Booking.java               # Booking model and operations
|
|-- lib/
|   |-- mysql-connector-java-X.X.X.jar # MySQL JDBC Driver
|
|-- README.md                      # Project documentation
```

---

## Example Outputs

1. **View Available Cars**
   ```
   Available Cars:
   ID: 1, Make: Toyota, Model: Corolla, Year: 2020, Price/Day: 50.0
   ID: 2, Make: Honda, Model: Civic, Year: 2019, Price/Day: 55.0
   ```

2. **Booking Confirmation**
   ```
   Enter your user ID: 2
   Enter car ID to book: 1
   Enter number of days: 3
   Booking successful! Total price: $150.0
   ```

---

## Future Enhancements

- Add an admin panel to manage cars and bookings.
- Implement user authentication and authorization.
- Add email notifications for booking confirmations.
- Enable filtering and searching for cars by criteria (e.g., price range, make, model).

---

## Author
**[Kausik T]**

