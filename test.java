package vulnerabilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableApp {

    public static void main(String[] args) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine()

        try {
            // Connect to the database (replace with your actual database connection details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // Vulnerability: Using Statement with string concatenation for SQL query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);

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
