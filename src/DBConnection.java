/**
 * File: DBConnection.java
 * Kelas ini mengelola koneksi database MySQL.
 * Ini menyediakan metode statis untuk mendapatkan koneksi baru ke database.
 * (Catatan: Meskipun komentar asli Anda menyebut Singleton, implementasi ini
 * lebih merupakan kelas utilitas yang memberikan koneksi BARU setiap kali dipanggil,
 * bukan satu instance koneksi yang sama).
 */

// Impor paket yang diperlukan untuk JDBC (Java Database Connectivity)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Konfigurasi koneksi database sebagai konstanta privat
    // URL menunjuk ke database 'testdb' di server MySQL lokal (localhost) port 3306
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    // Nama pengguna database
    private static final String USER = "root";
    // Kata sandi database (kosong dalam kasus ini)
    private static final String PASSWORD = "";

    /**
     * Metode statis untuk membuat dan mengembalikan koneksi ke database.
     * @return Objek Connection jika berhasil, atau null jika koneksi gagal.
     */
    public static Connection getConnection() {
        try {
            // Langkah 1: Memuat driver JDBC MySQL.
            // "com.mysql.cj.jdbc.Driver" adalah nama kelas driver modern.
            // (Langkah ini opsional di JDBC 4.0+ karena driver dimuat otomatis,
            // tetapi ini adalah praktik yang baik untuk kejelasan).
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Langkah 2: Membuat koneksi menggunakan DriverManager.
            // Ini mencoba menghubungkan ke database menggunakan URL, user, dan password
            // yang telah ditentukan.
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            // Tangani jika kelas driver tidak ditemukan atau terjadi kesalahan SQL
            // (misalnya, server down, kredensial salah, database tidak ada).
            System.err.println("Koneksi database gagal: " + e.getMessage());
            e.printStackTrace();
            // Kembalikan null untuk menandakan kegagalan koneksi.
            return null;
        }
    }
}