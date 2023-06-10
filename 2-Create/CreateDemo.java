import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateDemo {
    public static void main(String[] args) {
        // choice of countries
        String[] countries = {"Kenya", "Egypt", "China", "Russia", "Uganda"};

        Map<String, String> records = new HashMap<>();

        // generate the records now
        for (int i=1; i<=10; i++) {
            String email = "user" + i + "@example.com";
            String usernm = "user" + i;
            String passwd = "password" + i;
            String country = countries[new Random().nextInt(countries.length)];

            records.put(email, usernm + "," + passwd + "," + country);
        }

        String url = 
            "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "password123";
        String insertQuery = 
            "INSERT INTO users (email, username, password, country) VALUES (?, ?, ?, ?)";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            for (Map.Entry<String, String> entry: records.entrySet()) {
                String email = entry.getKey();
                String[] userDetails = entry.getValue().split(",");
                String usernm = userDetails[0];
                String passwd = userDetails[1];
                String country = userDetails[2];

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, usernm);
                preparedStatement.setString(3, passwd);
                preparedStatement.setString(4, country);
                preparedStatement.executeUpdate();
                System.out.println("[+] Added record " + ++count);
            }
        } catch (SQLException e) {
            System.out.println("[!] Error connecting to DB : " + e.getMessage());
        } finally {
            System.out.println("[+] Inserted " + count + " records");
        }
    }
}
