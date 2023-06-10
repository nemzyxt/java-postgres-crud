import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDemo {
    public static void main(String[] args) {
        String url = 
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[+] Successfully connected to DB");
        } catch (SQLException e) {
            System.out.println("[!] Error connecting to DB : " + e.getMessage());
        } finally {
            // close connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("[+] Successfully closed DB");
                } catch (SQLException e) {
                    System.out.println("[!] Error closing connection : " + e.getMessage());
                }
            }
        }
    }
}
