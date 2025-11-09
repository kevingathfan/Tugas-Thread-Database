import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataInserter extends Thread {
    private String name;

    public DataInserter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Koneksi gagal untuk thread: " + name);
                return;
            }

            String sql = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();

            System.out.println("Thread " + name + " berhasil menambah data.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
