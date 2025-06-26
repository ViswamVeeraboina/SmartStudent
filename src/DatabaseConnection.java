import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/smartstudent";
    private static final String USERNAME = "root"; // use your MySQL username
    private static final String PASSWORD = "root123"; // use the password you set

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to database.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        // Test the connection
        getConnection();
    }
}

