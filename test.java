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

        // Vulnerability: Hardcoded Sensitive Information
        // Hardcoding passwords in source code is a severe security risk.
        String password = "secret"; // Hardcoded password

        try {
            // Using string concatenation in SQL queries can lead to SQL injection if inputs are not sanitized.
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "user", "pass");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");

            if (resultSet.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }

            // Using Math.random() for security-critical applications can lead to predictability.
            double randomNumber = Math.random();
            System.out.println("Your security token: " + randomNumber);

            // Printing sensitive information like passwords to logs can lead to information leakage.
            System.out.println("Debug - User login attempt: " + username + " with hardcoded password used.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
