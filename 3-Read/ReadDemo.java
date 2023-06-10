import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadDemo {
    public static void main(String[] args) {
        String url = 
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        String selectQuery = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String usernm = resultSet.getString("username");
                String passwd = resultSet.getString("password");
                String country = resultSet.getString("country");

                String detail = 
                    id + "," + email + "," + usernm + "," + passwd + "," + country;
                System.out.println(detail);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("[!] Error : " + e.getMessage());
        }
    }
}
