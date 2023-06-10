import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDemo {
    public static void main(String[] args) {
        String url = 
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        String deleteQuery = "DELETE FROM users WHERE country=?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, "Kenya"); // sorry Kenya :(
            int records = preparedStatement.executeUpdate();
            System.out.println("[+] Deleted " + records + " records");
        } catch (SQLException e) {
            System.out.println("[!] Error : " + e.getMessage());
        }
    }
}
