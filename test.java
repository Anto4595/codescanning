// This Java example intentionally includes vulnerabilities for educational purposes.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            // Vulnerability 1: SQL Injection
            // Using string concatenation in SQL queries can lead to SQL injection if inputs are not sanitized.
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "user", "pass");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");

            if (resultSet.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }

            // Vulnerability 2: Insecure Randomness
            // Using Math.random() for security-critical applications can lead to predictability.
            double randomNumber = Math.random();
            System.out.println("Your security token: " + randomNumber);

            // Vulnerability 3: Sensitive Data Exposure
            // Printing sensitive information like passwords to logs can lead to information leakage.
            System.out.println("Debug - User login attempt: " + username + " with password: " + password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
