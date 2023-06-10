import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchReadDemo {
    public static void main(String[] args) {
        String url = 
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        String selectQuery = "SELECT * FROM users";
        int batchSize = 5; // in a real-world scenario it'd be a big number :)

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            preparedStatement.setFetchSize(batchSize);
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0, batchCount = 0;
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String usernm = resultSet.getString("username");
                String passwd = resultSet.getString("password");
                String country = resultSet.getString("country");

                String detail = 
                    id + "," + email + "," + usernm + "," + passwd + "," + country;
                System.out.println(detail);
                count++;

                if (count % batchSize == 0) {
                    System.out.println("\n[+] Done with batch " + ++batchCount + "\n\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] Error : " + e.getMessage());
        }
    }
}
