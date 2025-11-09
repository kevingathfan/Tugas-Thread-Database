/**
 * File: MainApp.java
 * Ini adalah kelas utama (entry point) dari aplikasi.
 * Kelas ini akan membuat dan menjalankan beberapa thread DataInserter
 * dan kemudian menjalankan thread DataReader.
 */
public class MainApp {
    /**
     * Metode main() yang dieksekusi saat program dijalankan.
     * @param args Argumen baris perintah (tidak digunakan di sini).
     */
    public static void main(String[] args) {
        System.out.println("=== Program Thread + MySQL ===");

        // --- TAHAP INSERT DATA ---
        // Membuat tiga objek thread DataInserter
        Thread t1 = new DataInserter("Alice");
        Thread t2 = new DataInserter("Bob");
        Thread t3 = new DataInserter("Charlie");

        // Menjalankan ketiga thread secara bersamaan (concurrently)
        // Memanggil start() akan menjalankan metode run() di thread baru.
        // Ketiga thread ini mungkin akan berjalan secara paralel.
        t1.start();
        t2.start();
        t3.start();

        // --- TAHAP MENUNGGU ---
        // 'main' thread (thread yang sedang menjalankan kode ini)
        // akan menunggu sampai ketiga thread insert selesai.
        try {
            // join() membuat thread saat ini (main) berhenti dan menunggu
            // sampai thread t1 selesai mengeksekusi metode run()-nya.
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // Tangani jika thread 'main' diganggu saat sedang menunggu
            e.printStackTrace();
        }

        // --- TAHAP BACA DATA ---
        // Setelah ketiga thread insert dijamin selesai (karena join()),
        // kita sekarang membuat dan menjalankan thread DataReader.
        System.out.println("Semua proses insert selesai. Membaca data...");
        Thread reader = new DataReader();
        reader.start(); // Menjalankan thread DataReader
    }
}