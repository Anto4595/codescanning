package vulnerabilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class VulnerableApp {

    public static void main(String[] args) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        try {
            // Connect to the database (replace with your actual database connection details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "mySuperSecretPassword123!");

            // Fix: Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result
            if (resultSet.next()) {
                System.out.println("User exists.");
            } else {
                System.out.println("User does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
