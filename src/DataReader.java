import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataReader extends Thread {
    @Override
    public void run() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Koneksi gagal untuk pembaca data.");
                return;
            }

            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== Data dari Database ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nama: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
