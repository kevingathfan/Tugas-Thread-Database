/**
 * File: DataInserter.java
 * Kelas ini adalah turunan dari Thread. Setiap instance dari kelas ini
 * dapat berjalan di thread-nya sendiri untuk memasukkan data ke database secara
 * bersamaan (concurrently).
 */

// Impor paket yang diperlukan untuk koneksi SQL
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataInserter extends Thread {
    // Variabel untuk menyimpan nama yang akan dimasukkan
    private String name;

    /**
     * Konstruktor untuk DataInserter.
     * @param name Nama yang akan dimasukkan ke database.
     */
    public DataInserter(String name) {
        this.name = name;
    }

    /**
     * Metode run() adalah inti dari Thread. Kode di dalam inilah yang akan
     * dieksekusi ketika thread di-start().
     */
    @Override
    public void run() {
        // SQL query untuk memasukkan data. Tanda tanya (?) adalah placeholder
        // untuk parameter yang akan diisi nanti.
        String sql = "INSERT INTO users (name) VALUES (?)";

        // Menggunakan "try-with-resources". Ini adalah praktik terbaik di Java
        // untuk mengelola sumber daya seperti koneksi database.
        // Koneksi (conn) dan PreparedStatement (ps) akan ditutup secara otomatis
        // setelah blok try selesai, bahkan jika terjadi error.
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Memeriksa apakah koneksi berhasil didapat
            if (conn == null) {
                System.out.println("Koneksi gagal untuk thread: " + name);
                return; // Hentikan eksekusi thread jika koneksi gagal
            }

            // Mengatur nilai parameter pertama (?) dengan variabel 'name'
            // Ini adalah cara aman untuk menghindari SQL Injection.
            ps.setString(1, name);

            // Menjalankan perintah INSERT ke database
            ps.executeUpdate();

            // Cetak pesan sukses jika data berhasil dimasukkan
            System.out.println("Thread " + name + " berhasil menambah data.");

        } catch (Exception e) {
            // Tangani jika terjadi kesalahan selama proses koneksi atau eksekusi SQL
            e.printStackTrace();
        }
    }
}