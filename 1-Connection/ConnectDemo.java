import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDemo {
    public static void main(String[] args) {
        String url = 
            "postgresql://ojlbhhnbkencjphbwbtkhgex%40psql-mock-database-cloud:nkcxqdjxpmvjoruwynhfvzvu@psql-mock-database-cloud.postgres.database.azure.com:5432/booking1686342304255dblcokyxcuipfkgl";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
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
