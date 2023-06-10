import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDemo {
    public static void main(String[] args) {
        String url =
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        String updateQuery = "UPDATE users SET password='str0ngp@ssWOrD' WHERE country=?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, "Kenya");
            int records = preparedStatement.executeUpdate();
            System.out.println("[+] Updated " + records + " records");
        } catch (SQLException e) {
            System.out.println("[!] Error : " + e.getMessage());
        }
    }
}
