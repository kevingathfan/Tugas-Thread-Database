/**
 * File: DataReader.java
 * Kelas ini adalah turunan dari Thread. Tugasnya adalah membaca
 * semua data dari tabel 'users' dan menampilkannya.
 */

// Impor paket yang diperlukan untuk SQL
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataReader extends Thread {
    /**
     * Metode run() yang akan dieksekusi saat thread di-start().
     */
    @Override
    public void run() {
        // SQL query untuk mengambil semua data dari tabel 'users'
        String sql = "SELECT * FROM users";

        // Menggunakan try-with-resources. Koneksi (conn), Statement (stmt),
        // dan ResultSet (rs) akan ditutup secara otomatis.
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Memeriksa apakah koneksi berhasil
            if (conn == null) {
                System.out.println("Koneksi gagal untuk pembaca data.");
                return; // Hentikan thread
            }

            System.out.println("\n=== Data dari Database ===");
            // Looping (iterasi) melalui setiap baris data yang dikembalikan
            // rs.next() memindahkan kursor ke baris berikutnya dan mengembalikan
            // 'true' jika masih ada baris data.
            while (rs.next()) {
                // Mengambil data dari baris saat ini berdasarkan nama kolom
                int id = rs.getInt("id");
                String name = rs.getString("name");
                // Menampilkan data ke konsol
                System.out.println("ID: " + id + " | Nama: " + name);
            }

        } catch (Exception e) {
            // Tangani jika terjadi kesalahan SQL
            e.printStackTrace();
        }
    }
}