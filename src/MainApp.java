public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Program Thread + MySQL ===");

        // Buat beberapa thread untuk insert
        Thread t1 = new DataInserter("Alice");
        Thread t2 = new DataInserter("Bob");
        Thread t3 = new DataInserter("Charlie");

        // Jalankan thread secara paralel
        t1.start();
        t2.start();
        t3.start();

        // Tunggu semua insert selesai
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Baca data setelah insert
        Thread reader = new DataReader();
        reader.start();
    }
}
