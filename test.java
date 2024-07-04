import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.SecureRandom;
import java.util.Scanner;

public class SecureApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            // Fixed Vulnerability 1: Prevent SQL Injection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "user", "pass");
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }

            // Fixed Vulnerability 2: Secure Randomness
            SecureRandom random = new SecureRandom();
            double secureRandomNumber = random.nextDouble();
            System.out.println("Your security token: " + secureRandomNumber);

            // Addressed Vulnerability 3: Sensitive Data Exposure
            // Avoid logging sensitive information like passwords.
            System.out.println("Debug - User login attempt: " + username);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
