# UAS-PBO
# Dokumentasi Konfigurasi Kendaraan Pada Pabrik Mitsubishi

## Daftar Isi
- [Dokumentasi Konfigurasi Kendaraan Pada Pabrik Mitsubishi](#dokumentasi-konfigurasi-kendaraan-pada-pabrik-mitsubishi)
  - [Daftar Isi](#daftar-isi)
  - [Anggota Kelompok](#anggota-kelompok)
  - [Deskripsi Program](#deskripsi-program)
  - [Flowchart](#flowchart)
  - [Entity Relationship Diagram](#entity-relationship-diagram)
    - [Logical Model](#logical-model)
    - [Relational model](#relational-model)
  - [Hirarki Kelas](#hirarki-kelas)
  - [Screenshot Coding Program](#screenshot-coding-program)
    - [Package Entity](#package-entity)
      - [Dokumen.java](#dokumenjava)
      - [Kendaraan.java](#kendaraanjava)
      - [Mobil.java](#mobiljava)
      - [Pegawai.java](#pegawaijava)
      - [Truk.java](#trukjava)
    - [Package Koneksi](#package-koneksi)
      - [Database.java](#databasejava)
  - [Screenshot Output Program](#screenshot-output-program)

## Anggota Kelompok
- **2209116006** - Dinnuhoni Trahutomo
- **2209116031** - Aufa Tri Hapsari

## Deskripsi Program
Program ini merupakan program yang dapat digunakan untuk mengatur konfigurasi kendaraan pada pabrik Mitsubishi. Program ini dapat digunakan untuk mengatur konfigurasi kendaraan berdasarkan tipe kendaraan, warna, dan fitur-fitur yang ada pada kendaraan. Program ini juga dapat digunakan untuk mendata opsi kendaraan berdasarkan konfigurasi yang telah dipilih. Program ini dibuat menggunakan bahasa pemrograman Java Swing.

## Flowchart
![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/bb013a23-705c-4b85-ac15-44a67cdd7df0)

## Entity Relationship Diagram
### Logical Model
![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/53f3a2c2-a946-4e94-a278-89ee1a60aea0)

### Relational model
![WhatsApp Image 2023-11-03 at 23 30 10_ae030ea7](https://github.com/kelompok-23-PA-Mitsubishi/pbo-dbd-pa/assets/122195566/2760e3e6-4625-4162-99f5-e97154e3376a)

## Hirarki Kelas
![image](https://github.com/kelompok-23-PA-Mitsubishi/pbo-dbd-pa/assets/122031507/f9fe68fe-d94c-467e-bda7-b00f71a78b56)

## Screenshot Coding Program
Terdapat 4 Package pada program ini, yaitu:
- **Entity** - Package ini berisi kelas-kelas yang berfungsi untuk menyimpan data-data yang dibutuhkan pada program.
- **Koneksi** - Package ini berisi kelas-kelas yang berfungsi untuk menghubungkan program dengan database.
- **project_akhir_pbo** - Package ini berisi kelas-kelas yang berfungsi untuk mengatur tampilan program.
Kode per Package:
Pada source packages terdapat 4 package, yaitu Connection, Entity, GUI, dan Images.
1.	Connection
Package “Connection” adalah bagian dari proyek Java yang berhubungan dengan koneksi ke database MySQL.
package Connection;

// Mengimpor kelas-kelas yang diperlukan
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

// Deklarasi kelas utama
public class Database {
    
    // Variabel-variabel kelas
    public static Connection mysqlconfig; // Variabel statis untuk konfigurasi (tidak digunakan?)
    private Statement st; // Objek Statement untuk pernyataan SQL
    private Connection con; // Objek Connection untuk koneksi ke database
    private ResultSet rs; // Objek ResultSet untuk menyimpan hasil query SELECT

    // Konstruktor kelas
    public Database(){
        try {
            // Membuat koneksi ke database MySQL
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercedes-benz","root","");
            st = con.createStatement(); // Membuat objek Statement
        } catch (SQLException e) {
            // Menampilkan pesan kesalahan jika koneksi gagal
            JOptionPane.showMessageDialog(null, "Turn on MySQL first!");
        }
    }
    
    // Metode untuk menjalankan query SELECT dan mengembalikan hasilnya dalam bentuk ResultSet
    public ResultSet executeSelectQuery(String qq) throws SQLException{
        rs = st.executeQuery(qq);
        return rs;
    }
    
    // Metode untuk menjalankan query UPDATE, INSERT, DELETE, dll.
    public void executeUpdateQuery(String query) throws SQLException{
        st.execute(query);
    }
}


2.	Entity
Dalam package “Entity” terdapat 5 kelas atau class, yaitu Car, Document, Employee, Truck, dan Vehicle.
a.	Car
Kelas Car yang merupakan turunan dari kelas Vehicle dalam paket Entity. Kelas ini merepresentasikan entitas mobil dengan tambahan informasi jumlah penumpang dan jumlah pintu. Terdapat metode-metode untuk mengakses dan mengubah informasi mobil, serta untuk membaca, menambahkan, menghapus, dan memperbarui data mobil dalam database.
Perlu diperhatikan bahwa terdapat penggunaan INNER JOIN dalam kueri SQL pada metode readMobil. Hal ini digunakan untuk menggabungkan data dari tabel kendaraan dan mobil berdasarkan kolom id_kendaraan.
Metode-metode utama dalam kelas ini termasuk readMobil untuk membaca data mobil, createMobil untuk menambahkan mobil baru, deleteMobil untuk menghapus mobil, dan updateMobil untuk memperbarui informasi mobil.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Connection.Database;

public class Car extends Vehicle{
    // Variabel-variabel instance untuk menyimpan informasi mobil
    String jumlah_penumpang;
    String jumlah_pintu;
    
    // Objek Database untuk koneksi ke database
    static Database db = new Database();
    
    // ArrayList untuk menyimpan objek Car
    static ArrayList<Car> ArrayMobil = new ArrayList<>();

    // Konstruktor untuk membuat objek Car dengan informasi yang diberikan
    public Car(String jumlah_penumpang, String jumlah_pintu, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) {
        super(id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
        this.jumlah_penumpang = jumlah_penumpang;
        this.jumlah_pintu = jumlah_pintu;
    }
    
    // Metode-metode untuk mengakses dan mengubah informasi mobil
    // (Getter dan Setter)
    public String getJumlah_penumpang() {
        return jumlah_penumpang;
    }

    public void setJumlah_penumpang(String jumlah_penumpang) {
        this.jumlah_penumpang = jumlah_penumpang;
    }

    public String getJumlah_pintu() {
        return jumlah_pintu;
    }

    public void setJumlah_pintu(String jumlah_pintu) {
        this.jumlah_pintu = jumlah_pintu;
    }

    public String getId_Kendaraan() {
        return id_Kendaraan;
    }

    public void setId_Kendaraan(String id_Kendaraan) {
        this.id_Kendaraan = id_Kendaraan;
    }

    public String getUkuranmesin_Kendaraan() {
        return ukuranmesin_Kendaraan;
    }

    public void setUkuranmesin_Kendaraan(String ukuranmesin_Kendaraan) {
        this.ukuranmesin_Kendaraan = ukuranmesin_Kendaraan;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }
    
    // Metode untuk membaca data mobil dari database dan menyimpannya dalam ArrayList
    public static ArrayList<Car> readMobil() throws SQLException {
        ArrayMobil.clear(); // Membersihkan ArrayList sebelum mengisi ulang
        String query = "SELECT kendaraan.*, mobil.jumlah_penumpang, mobil.jumlah_pintu FROM kendaraan INNER JOIN mobil ON kendaraan.id_kendaraan = mobil.id_kendaraan;";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {
            // Membaca data dari hasil query
            String id = rs.getString("id_kendaraan");
            String ukuranMesin = rs.getString("ukuran_mesin");
            String model = rs.getString("model");
            String warna = rs.getString("warna");
            String idPegawai = rs.getString("Pegawai_id_Pegawai");
            String jumlahPenumpang = rs.getString("jumlah_penumpang");
            String jumlahPintu = rs.getString("jumlah_pintu");
            Car mobilBaru = new Car(jumlahPenumpang, jumlahPintu, id, ukuranMesin, model, warna, idPegawai);
            ArrayMobil.add(mobilBaru);
        }
        return ArrayMobil;
    }
    
        // Metode untuk membuat mobil baru dan menyimpannya ke dalam database
        public static void createMobil(String jumlah_penumpang, String jumlah_pintu, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) throws SQLException {
            Car mobilBaru = new Car(jumlah_penumpang, jumlah_pintu, id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
            ArrayMobil.add(mobilBaru); // Use the correct object name
            String query = String.format("INSERT INTO mobil VALUES ('%s','%s','%s')",
                    mobilBaru.getId_Kendaraan(),mobilBaru.getJumlah_penumpang(), mobilBaru.getJumlah_pintu());
            db.executeUpdateQuery(query);
        }

        // Metode untuk menghapus mobil dari ArrayList dan database
        public static void deleteMobil(String id_Kendaraan) throws SQLException {
            for (Car mobil : ArrayMobil) {
                if (mobil.getId_Kendaraan().equals(id_Kendaraan)) {
                    ArrayMobil.remove(mobil); // Use the correct object name
                    String query = String.format("DELETE FROM mobil WHERE id_kendaraan = '%s'", mobil.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }

        // Metode untuk mengupdate informasi mobil di ArrayList dan database
        public static void updateMobil(String pintu, String penumpang, String idMobil) throws SQLException{
            for (Car mobil : ArrayMobil) {
                if (mobil.getId_Kendaraan().equals(idMobil)) {
                    mobil.setJumlah_penumpang(penumpang);
                    mobil.setJumlah_pintu(pintu);
                    String query = String.format("UPDATE mobil SET jumlah_pintu = '%s', jumlah_penumpang = '%s' WHERE id_kendaraan = '%s' ",
                            mobil.getJumlah_pintu(),mobil.getJumlah_penumpang(),mobil.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }
    }
 
b.	Document
Kelas Java Document ini mewakili entitas dokumen dengan atribut seperti id_Dokumen, jenis_Dokumen, dan tanggalterbit_Dokumen. Kelas ini memiliki metode untuk membaca dokumen dari database dan membuat dokumen baru. Namun, beberapa perbaikan dapat dilakukan, seperti konsistensi dalam konvensi penamaan, penanganan yang lebih baik terhadap pengecualian, penggunaan pernyataan SQL yang aman dari serangan injeksi, dan perhatian terhadap inisialisasi daftar statis dalam lingkungan multi-threaded. Disarankan juga untuk menghapus impor yang tidak digunakan dan menambahkan dokumentasi untuk meningkatkan pemahaman tentang tujuan kelas dan metode.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import static Entity.Vehicle.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Document {
    String id_Dokumen;
    String jenis_Dokumen;
    String tanggalterbit_Dokumen;
    static ArrayList<Document> arrayDokumen = new ArrayList<>();

    public Document(String id_Dokumen, String jenis_Dokumen, String tanggalterbit_Dokumen) {
        this.id_Dokumen = id_Dokumen;
        this.jenis_Dokumen = jenis_Dokumen;
        this.tanggalterbit_Dokumen = tanggalterbit_Dokumen;
    }

    public String getId_Dokumen() {
        return id_Dokumen;
    }

    public void setId_Dokumen(String id_Dokumen) {
        this.id_Dokumen = id_Dokumen;
    }

    public String getJenis_Dokumen() {
        return jenis_Dokumen;
    }

    public void setJenis_Dokumen(String jenis_Dokumen) {
        this.jenis_Dokumen = jenis_Dokumen;
    }

    public String getTanggalterbit_Dokumen() {
        return tanggalterbit_Dokumen;
    }

    public void setTanggalterbit_Dokumen(String tanggalterbit_Dokumen) {
        this.tanggalterbit_Dokumen = tanggalterbit_Dokumen;
    } 
    public static ArrayList<Document> readDokumen() throws SQLException{
        arrayDokumen.clear();
        String query = "SELECT * FROM dokumen";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id = rs.getString("id_dokumen");
            String jenis = rs.getString("jenis_dokumen");
            String tanggal = rs.getString("tanggalterbit_dokumen");
            Document dokumenBaru = new Document(id, jenis, tanggal);
            arrayDokumen.add(dokumenBaru);
        }
        return arrayDokumen;
    }
  
    public static void createKendaraan(String id_Dokumen, String jenis_Dokumen, String tanggalterbit_Dokumen) throws SQLException{
        Document dokumenBaru = new Document(id_Dokumen, jenis_Dokumen, tanggalterbit_Dokumen);
        arrayDokumen.add(dokumenBaru);
        String query = String.format("INSERT INTO `dokumen`(`jenis_dokumen`, `tanggalterbit_dokumen`) VALUES ('%s','%s');",
                dokumenBaru.getJenis_Dokumen(),dokumenBaru.getTanggalterbit_Dokumen());
        db.executeUpdateQuery(query);
    } 
}
 
c.	Employee
Kelas Employee dalam paket Entity yang digunakan untuk merepresentasikan entitas pegawai. Kelas ini memiliki atribut seperti ID pegawai, nama, nomor telepon, jabatan, dan kata sandi. Interaksi dengan database dilakukan melalui kueri SQL untuk membaca, menambahkan, menghapus, dan memperbarui data pegawai. Metode readPegawai membaca data pegawai dari tabel pegawai, sementara createPegawai menambahkan pegawai baru ke dalam ArrayList dan menyisipkan data ke dalam tabel pegawai dalam database. Metode deletePegawai menghapus pegawai dari ArrayList dan database, sedangkan updatePegawai memperbarui informasi pegawai. 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import Connection.Database;
import java.sql.*;

/**
 *
 * @author User
 */
public class Employee {
    String id_pegawai;
    String nama_pegawai;
    String nohp_pegawai;
    String jabatan_pegawai;
    String pass_pegawai;
    String pegawaiIdPegawai;
    
    static Database db = new Database();
    static ArrayList<Employee> arrayPegawai = new ArrayList<>();

    public Employee(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
        this.nohp_pegawai = nohp_pegawai;
        this.jabatan_pegawai = jabatan_pegawai;
        this.pass_pegawai = pass_pegawai;
        this.pegawaiIdPegawai = pegawaiIdPegawai; 
    }

    public String getPegawaiIdPegawai() {
        return pegawaiIdPegawai;
    }

    public void setPegawaiIdPegawai(String pegawaiIdPegawai) {
        this.pegawaiIdPegawai = pegawaiIdPegawai;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getNohp_pegawai() {
        return nohp_pegawai;
    }

    public void setNohp_pegawai(String nohp_pegawai) {
        this.nohp_pegawai = nohp_pegawai;
    }

    public String getJabatan_pegawai() {
        return jabatan_pegawai;
    }

    public void setJabatan_pegawai(String jabatan_pegawai) {
        this.jabatan_pegawai = jabatan_pegawai;
    }

    public String getPass_pegawai() {
        return pass_pegawai;
    }

    public void setPass_pegawai(String pass_pegawai) {
        this.pass_pegawai = pass_pegawai;
    }
    
    public static ArrayList<Employee> readPegawai() throws SQLException{
        arrayPegawai.clear();
        String query = "SELECT * FROM pegawai";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id = rs.getString("id_pegawai");
            String nama = rs.getString("nama_pegawai");
            String pass = rs.getString("pass_pegawai");
            String nohp = rs.getString("nohp_pegawai");
            String jabatan = rs.getString("jabatan");
            String idPegawai = rs.getString("Pegawai_id_Pegawai");
            Employee barangBaru = new Employee(id, nama, pass, nohp, jabatan, idPegawai);
            
        }return arrayPegawai;
    }
    
    public static void createPegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) throws SQLException{
        Employee barangBaru = new Employee(id_pegawai, nama_pegawai, nohp_pegawai, jabatan_pegawai, pass_pegawai, pegawaiIdPegawai);
        arrayPegawai.add(barangBaru);
        String query = String.format("INSERT INTO pegawai VALUES ('%s','%s','%s','%s','%s','%s')",
                barangBaru.getId_pegawai(),barangBaru.getNama_pegawai(),barangBaru.getPass_pegawai(),barangBaru.getNohp_pegawai(),barangBaru.getJabatan_pegawai(),barangBaru.getPegawaiIdPegawai());
        db.executeUpdateQuery(query);
    }
    
    public static void deletePegawai(String idPegawai) throws SQLException{
        for (Employee pegawai : arrayPegawai) {
            if (pegawai.getId_pegawai().equals(idPegawai)) {
                arrayPegawai.remove(pegawai);
                String query = String.format("DELETE FROM pegawai WHERE id_barang = '%s'", pegawai.getId_pegawai());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updatePegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) throws SQLException{
        for (Employee pegawai : arrayPegawai) {
            if (pegawai.getId_pegawai().equals(id_pegawai)) {
                pegawai.setNama_pegawai(nama_pegawai);
                pegawai.setPass_pegawai(pass_pegawai);
                pegawai.setNohp_pegawai(nohp_pegawai);
                pegawai.setJabatan_pegawai(jabatan_pegawai);
                pegawai.setPegawaiIdPegawai(pegawaiIdPegawai);
            String query = String.format("UPDATE pegawai SET nama_pegawai = '%s', pass_pegawai = '%s' , nohp_pegawai = '%s', jabatan = '%s', Pegawai_id_Pegawai = '%s' WHERE id_Pegawai = '%s'",
                    pegawai.getNama_pegawai(),pegawai.getPass_pegawai(),pegawai.getNohp_pegawai(),pegawai.getJabatan_pegawai(),pegawai.getPegawaiIdPegawai(),pegawai.getId_pegawai());
            db.executeUpdateQuery(query);
            }
        }
    }
}
 
d.	Truck
Kelas Vehicle dan menciptakan kelas baru bernama Truck dalam paket Entity. Kelas Truck menambahkan atribut baru seperti jenis_bak dan kapasitas_maksimum yang berkaitan dengan truk. Metode-metodenya melibatkan interaksi dengan database, termasuk pembacaan, penambahan, penghapusan, dan pembaruan data truk. Metode readTruk menggunakan JOIN untuk menggabungkan data dari tabel kendaraan dan truk. Pada metode createTruk, truk baru ditambahkan ke ArrayList ArrayTruk dan data disisipkan ke dalam tabel truk dalam database. Metode deleteTruk menghapus truk dari ArrayList dan database, sedangkan updateTruk memperbarui informasi truk. Penggunaan ArrayList untuk menyimpan objek truk perlu diperhatikan agar tidak menimbulkan masalah penghapusan elemen sambil mengulangi.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Connection.Database;

/**
 *
 * @author User
 */
public class Truck extends Vehicle {
    String jenis_bak;
    String kapasitas_maksimum;
    static Database db = new Database();
    static ArrayList<Truck> ArrayTruk = new ArrayList<>();

    public Truck(String jenis_bak, String kapasitas_maksimum, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) {
        super(id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
        this.jenis_bak = jenis_bak;
        this.kapasitas_maksimum = kapasitas_maksimum;
    }

    public String getJenis_bak() {
        return jenis_bak;
    }

    public void setJenis_bak(String jenis_bak) {
        this.jenis_bak = jenis_bak;
    }

    public String getKapasitas_maksimum() {
        return kapasitas_maksimum;
    }

    public void setKapasitas_maksimum(String kapasitas_maksimum) {
        this.kapasitas_maksimum = kapasitas_maksimum;
    }

    @Override
    public String getId_Kendaraan() {
        return id_Kendaraan;
    }

    @Override
    public void setId_Kendaraan(String id_Kendaraan) {
        this.id_Kendaraan = id_Kendaraan;
    }

    @Override
    public String getUkuranmesin_Kendaraan() {
        return ukuranmesin_Kendaraan;
    }

    @Override
    public void setUkuranmesin_Kendaraan(String ukuranmesin_Kendaraan) {
        this.ukuranmesin_Kendaraan = ukuranmesin_Kendaraan;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getWarna() {
        return warna;
    }

    @Override
    public void setWarna(String warna) {
        this.warna = warna;
    }
    public static ArrayList<Truck> readTruk() throws SQLException {
        ArrayTruk.clear();
        String query = "SELECT kendaraan.*, truk.jenis_bak, truk.kapasitas_maksimum FROM kendaraan INNER JOIN truk ON kendaraan.id_kendaraan = truk.id_kendaraan";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {
            String id = rs.getString("id_kendaraan");
            String ukuranMesin = rs.getString("ukuran_mesin");
            String model = rs.getString("model");
            String warna = rs.getString("warna");
            String idPegawai = rs.getString("Pegawai_id_Pegawai");
            String jenisBak = rs.getString("jenis_bak");
            String kapasitas = rs.getString("kapasitas_maksimum");
            Truck trukBaru = new Truck(jenisBak, kapasitas, id, ukuranMesin, model, warna, idPegawai);
            ArrayTruk.add(trukBaru);
        }
        return ArrayTruk;
    }

        public static void createTruk(String jenis_bak, String kapasitas_maksimum, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) throws SQLException {
            Truck trukBaru = new Truck(jenis_bak, kapasitas_maksimum, id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
            ArrayTruk.add(trukBaru); // Use the correct object name
            String query = String.format("INSERT INTO truk VALUES ('%s','%s','%s')",
                    trukBaru.getId_Kendaraan(),trukBaru.getJenis_bak(), trukBaru.getKapasitas_maksimum());
            db.executeUpdateQuery(query);
        }

        public static void deleteTruk(String id_Kendaraan) throws SQLException {
            for (Truck truk : ArrayTruk) {
                if (truk.getId_Kendaraan().equals(id_Kendaraan)) {
                    ArrayTruk.remove(truk); // Use the correct object name
                    String query = String.format("DELETE FROM truk WHERE id_kendaraan = '%s'", truk.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }


        public static void updateTruk(String bak,String kapasitas, String idTruk) throws SQLException{
            for (Truck truk : ArrayTruk) {
                if (truk.getId_Kendaraan().equals(idTruk)) {
                    truk.setKapasitas_maksimum(kapasitas);
                    truk.setJenis_bak(bak);
                    String query = String.format("UPDATE truk SET jenis_bak = '%s', kapasitas_maksimum = '%s' WHERE id_kendaraan = '%s' ",
                            truk.getJenis_bak(),truk.getKapasitas_maksimum(),truk.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }
    
}
 
e.	Vehicle
Kelas “Vehicle” dalam paket Entity yang digunakan untuk merepresentasikan entitas kendaraan. Kelas ini memiliki atribut seperti ID kendaraan, ukuran mesin, model, warna, dan ID pegawai. Interaksi dengan database dilakukan melalui kueri SQL, termasuk operasi SELECT, INSERT, DELETE, dan UPDATE untuk tabel kendaraan. Metode-metodenya memungkinkan pembacaan, penambahan, penghapusan, dan pembaruan data kendaraan, dengan penanganan pengecualian untuk mengatasi situasi ID kendaraan yang duplikat. Penggunaan ArrayList untuk menyimpan instansi kendaraan dapat menimbulkan masalah, seperti penghapusan elemen sembari mengulangi, yang mungkin memerlukan pendekatan lebih cermat.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
import Connection.Database;

/**
 *
 * @author Dinnu & Aufa
 */
public class Vehicle {
    String id_Kendaraan;
    String ukuranmesin_Kendaraan;
    String model;
    String warna;
    String idPegawai;
    static Database db = new Database();
    static ArrayList<Vehicle> arrayDokumen = new ArrayList<>();
    
    public Vehicle(String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna,String idPegawai) {
        this.id_Kendaraan = id_Kendaraan;
        this.ukuranmesin_Kendaraan = ukuranmesin_Kendaraan;
        this.model = model;
        this.warna = warna;
        this.idPegawai = idPegawai;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getId_Kendaraan() {
        return id_Kendaraan;
    }

    public void setId_Kendaraan(String id_Kendaraan) {
        this.id_Kendaraan = id_Kendaraan;
    }

    public String getUkuranmesin_Kendaraan() {
        return ukuranmesin_Kendaraan;
    }

    public void setUkuranmesin_Kendaraan(String ukuranmesin_Kendaraan) {
        this.ukuranmesin_Kendaraan = ukuranmesin_Kendaraan;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public static ArrayList<Vehicle> readKendaraan() throws SQLException{
        arrayDokumen.clear();
        String query = "SELECT * FROM kendaraan";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id_kendaraan = rs.getString("id_kendaraan");
            String ukuran_mesin = rs.getString("ukuran_mesin");
            String model = rs.getString("model");
            String warna = rs.getString("warna");
            String idPegawai = rs.getString("Pegawai_id_pegawai");
            Vehicle kendaraanBaru = new Vehicle(id_kendaraan, ukuran_mesin, model, warna,idPegawai);
            arrayDokumen.add(kendaraanBaru);
        }
        return arrayDokumen;
    }
    
    public static void createKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna,String idPegawai) throws SQLException{
        try {
            Vehicle kendaraanBaru = new Vehicle(id_kendaraan, ukuran_mesin, model, warna,idPegawai);
            arrayDokumen.add(kendaraanBaru);
            String query = String.format("INSERT INTO kendaraan VALUES ('%s','%s','%s','%s','%s')",
                    kendaraanBaru.getId_Kendaraan(),kendaraanBaru.getUkuranmesin_Kendaraan(),kendaraanBaru.getModel(),kendaraanBaru.getWarna(),kendaraanBaru.getIdPegawai());
            db.executeUpdateQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Duplicate Vehicle ID!");
        }
    }
    
    public static void deleteKendaraan(String id_Kendaraan) throws SQLException{
        for (Vehicle kendaraan : arrayDokumen) {
            if (kendaraan.getId_Kendaraan().equals(id_Kendaraan)) {
                arrayDokumen.remove(kendaraan);
                String query = String.format("DELETE FROM kendaraan WHERE id_kendaraan = '%s'", kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updateKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna,String idKaryawan) throws SQLException{
        for (Vehicle kendaraan : arrayDokumen) {
            if (kendaraan.getId_Kendaraan().equals(id_kendaraan)) {
                kendaraan.setUkuranmesin_Kendaraan(ukuran_mesin);
                kendaraan.setModel(model);
                kendaraan.setWarna(warna);
                kendaraan.setIdPegawai(idKaryawan);
                String query = String.format("UPDATE Kendaraan SET ukuran_mesin = '%s', model = '%s',warna = '%s', Pegawai_id_Pegawai = '%s' WHERE id_kendaraan = '%s' ",
                        kendaraan.getUkuranmesin_Kendaraan(),kendaraan.getModel(),kendaraan.getWarna(), kendaraan.getIdPegawai(),kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
            }
        }
    }
} 
3.	GUI
a.	CRUD
CRUD (Create, Read, Update, Delete) pada data kendaraan. Kelas utama, yaitu CRUD, mengextend kelas javax.swing.JFrame, menandakan bahwa aplikasi ini menggunakan perpustakaan Swing untuk membangun antarmuka grafis. Konstruktor kelas menerima parameter idPegawai yang sepertinya berhubungan dengan identifikasi karyawan.
Dalam konstruktor, terdapat pemanggilan beberapa metode seperti initComponents, serta metode-metode readMobil, readTruk, dan readKendaraan, yang mungkin bertugas membaca data dari berbagai jenis kendaraan, seperti mobil, truk, dan kendaraan umum. Metode setTableContent digunakan untuk mengisi tabel pada antarmuka pengguna, tergantung pada jenis kendaraan yang dipilih melalui komponen ChooseVehicle.
Komponen Swing seperti JFrame, JTable, dan JComboBox digunakan untuk membangun antarmuka pengguna yang interaktif. Terdapat dua jenis tabel, satu untuk mobil dan satu lagi untuk truk, dan kontennya diperbarui sesuai dengan pilihan jenis kendaraan.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Entity.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CRUD extends javax.swing.JFrame {

    String idPegawai;
    
    public CRUD(String idPegawai) throws SQLException {
        initComponents();
        this.idPegawai = idPegawai;
        Car.readMobil();
        Truck.readTruk();
        Vehicle.readKendaraan();
        setTableContent();
        TableMobil.getTableHeader().setReorderingAllowed(false);
        CreateTableTruk.getTableHeader().setReorderingAllowed(false);
    }
    
    private void setTableContent(){
        if (ChooseVehicle.getSelectedItem().equals("CAR")) {
            DefaultTableModel modelMobil = (DefaultTableModel) TableMobil.getModel();
            modelMobil.setRowCount(0);
            try {
                jPanel1.removeAll();
                jPanel1.repaint();
                jPanel1.revalidate();
                
                jPanel1.add(menuMobil);
                jPanel1.repaint();
                jPanel1.revalidate();
                ArrayList<Car> dataMobil = Car.readMobil();
                for (Car mobil : dataMobil) {
                    String id = mobil.getId_Kendaraan();
                    String mesin = mobil.getUkuranmesin_Kendaraan();
                    String model = mobil.getModel();
                    String warna = mobil.getWarna();
                    String idPegawai = mobil.getIdPegawai();
                    String jumlahPenumpang = String.valueOf(mobil.getJumlah_penumpang());
                    String jumlahPintu = String.valueOf(mobil.getJumlah_pintu());
                    String[] row = {id,mesin,model,warna,jumlahPintu,jumlahPenumpang,idPegawai};
                    modelMobil.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                DefaultTableModel modeltruk = (DefaultTableModel) CreateTableTruk.getModel();
                modeltruk.setRowCount(0);
                jPanel1.removeAll();
                jPanel1.repaint();
                jPanel1.revalidate();
                
                jPanel1.add(menuTruk);
                jPanel1.repaint();
                jPanel1.revalidate();
                ArrayList<Truck> dataMobil = Truck.readTruk();
                for (Truck truk : dataMobil) {
                    String id = truk.getId_Kendaraan();
                    String mesin = truk.getUkuranmesin_Kendaraan();
                    String model = truk.getModel();
                    String warna = truk.getWarna();
                    String idPegawai = truk.getIdPegawai();
                    String jenisBak = truk.getJenis_bak();
                    String kapasitas = truk.getKapasitas_maksimum();
                    String[] row = {id,mesin,model,warna,jenisBak,kapasitas,idPegawai};
                    modeltruk.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
b.	DocumentGUI
Kode DocumentGUI merupakan implementasi GUI Java untuk menampilkan data dokumen. Dalam konstruktor, metode showTable dipanggil untuk mengambil data dokumen dari kelas Document dan menampilkannya dalam tabel. Desain antarmuka pengguna terdiri dari satu tabel dengan kolom "Document ID," "Document Type," dan "Publication Date." Variabel seperti jPanel1, jScrollPane1, dan jTable1 digunakan untuk menangani antarmuka grafis.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Entity.Document;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DocumentGUI extends javax.swing.JFrame {


    public DocumentGUI() throws SQLException {
        initComponents();
        showTable();
    }
    
    private void showTable() throws SQLException{
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ArrayList<Document> data = Document.readDokumen();
        for (Document dokumen : data) {
            String id = dokumen.getId_Dokumen();
            String jenis = dokumen.getJenis_Dokumen();
            String tanggal = dokumen.getTanggalterbit_Dokumen();
            String [] row = {id,jenis,tanggal};
            model.addRow(row);
        }
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Document ID", "Document Type", "Publication Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(450, 90));
        jTable1.setMinimumSize(new java.awt.Dimension(450, 90));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DocumentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DocumentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DocumentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocumentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DocumentGUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DocumentGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}
c.	Login
Kode yang disediakan adalah implementasi antarmuka login dalam aplikasi Java menggunakan Java Swing. Kelas Login berfungsi sebagai frame untuk antarmuka login, dengan elemen-elemen seperti field untuk username (txt_username), password (txt_password), tombol login (jButton1), dan pilihan role user (cb_pilihuser). Metode initComponents digenerate oleh NetBeans dan menangani konfigurasi elemen-elemen GUI.
Ketika tombol login ditekan (jButton1ActionPerformed), aplikasi mengambil nilai username, password, dan role dari elemen GUI. Selanjutnya, aplikasi melakukan query ke database menggunakan metode executeSelectQuery dari objek db (kelas Database). Jika hasil query tidak kosong, aplikasi memeriksa role pengguna dan menampilkan frame yang sesuai. Jika tidak, muncul pesan kesalahan. Elemen GUI diatur dalam layout menggunakan org.netbeans.lib.awtextra.AbsoluteLayout.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Entity.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Connection.Database;


    
/**
 *
 * @author User
 */
public class Login extends javax.swing.JFrame {
    PreparedStatement ps;
    ResultSet rs;
    
    Database db = new Database();
    

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        cb_pilihuser = new javax.swing.JComboBox<>();
        txt_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel1.setText("USERNAME");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 100, 30));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel2.setText("PASSWORD");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 110, 30));
        jPanel1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 120, 30));

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 120, 30));

        cb_pilihuser.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        cb_pilihuser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MANAGER", "ADMIN" }));
        cb_pilihuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihuserActionPerformed(evt);
            }
        });
        jPanel1.add(cb_pilihuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, 30));

        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 120, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 80, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 800, 40));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled design (1).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        String nama = txt_username.getText();
        String password = txt_password.getText();
        String role = cb_pilihuser.getSelectedItem().toString();

        try {
            rs = db.executeSelectQuery(String.format("SELECT * FROM pegawai WHERE nama_pegawai = '%s' AND pass_pegawai = '%s'", nama,password));
            if (rs.next()) {
                String r = rs.getString("jabatan");
                String id = rs.getString("id_pegawai");
                if (role.equalsIgnoreCase("MANAGER") && r.equalsIgnoreCase("MANAGER")) {
                    ManagerForm manajerForm = new ManagerForm();
                    manajerForm.setVisible(true);
                    manajerForm.pack();
                    manajerForm.setLocationRelativeTo(null);
                    manajerForm.setDefaultCloseOperation(ManagerForm.EXIT_ON_CLOSE);
                    this.dispose();
                } else if (role.equalsIgnoreCase("ADMIN") && r.equalsIgnoreCase("ADMIN")) {
                    CRUD pmt = new CRUD(id);
                    pmt.setVisible(true);
                    pmt.pack();
                    pmt.setLocationRelativeTo(null);
                    pmt.setDefaultCloseOperation(CRUD.EXIT_ON_CLOSE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Role is not correct!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Please check your username or your password!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException x) {
            System.out.println("" + x);
        }
    }

d.	ManagerForm
Kode ManagerForm merupakan implementasi frame GUI dalam aplikasi Java Swing yang bertujuan untuk menampilkan data kendaraan, baik mobil (TableMobil) maupun truk (CreateTableTruk1). Konstruktor kelas ini memanggil metode initComponents dan dua metode lainnya, yaitu setTableMobil dan setTableTruk.
Metode setTableMobil digunakan untuk mengisi tabel mobil (TableMobil). Data mobil diambil dari ArrayList mobil yang dibaca melalui metode statis readMobil dari kelas Car. Informasi seperti ID kendaraan, mesin, model, warna, jumlah penumpang, jumlah pintu, dan ID pegawai ditampilkan dalam tabel mobil.
Metode setTableTruk mirip dengan setTableMobil, tetapi mengisi tabel truk (CreateTableTruk1) dengan data dari ArrayList truk yang dibaca melalui metode statis readTruk dari kelas Truck. Informasi yang ditampilkan mencakup ID kendaraan, mesin, model, warna, ID pegawai, jenis bak, dan kapasitas maksimum.
Komponen GUI seperti tabel dan header tabel diatur menggunakan DefaultTableModel untuk memudahkan pengelolaan data. Tabel mobil dan truk dinonaktifkan untuk di-reorder dengan menggunakan metode setReorderingAllowed(false) pada header tabel.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Entity.Car;
import Entity.Truck;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ManagerForm extends javax.swing.JFrame {

    /**
     * Creates new form ManajerForm
     */
    public ManagerForm() throws SQLException {
        initComponents();
        setTableTruk();
        setTableMobil();
        TableMobil.getTableHeader().setReorderingAllowed(false);
        CreateTableTruk1.getTableHeader().setReorderingAllowed(false);
    }
    
    private void setTableMobil() throws SQLException{
        DefaultTableModel modelMobil = (DefaultTableModel) TableMobil.getModel();
        modelMobil.setRowCount(0);
        ArrayList<Car> dataMobil = Car.readMobil();
        for (Car mobil : dataMobil) {
            String id = mobil.getId_Kendaraan();
            String mesin = mobil.getUkuranmesin_Kendaraan();
            String model = mobil.getModel();
            String warna = mobil.getWarna();
            String idPegawai = mobil.getIdPegawai();
            String jumlahPenumpang = String.valueOf(mobil.getJumlah_penumpang());
            String jumlahPintu = String.valueOf(mobil.getJumlah_pintu());
            String[] row = {id,mesin,model,warna,jumlahPintu,jumlahPenumpang,idPegawai};
            modelMobil.addRow(row);
        }
    }
    
    private void setTableTruk() throws SQLException{
        DefaultTableModel modeltruk = (DefaultTableModel) CreateTableTruk1.getModel();
        modeltruk.setRowCount(0);
        ArrayList<Truck> dataMobil = Truck.readTruk();
        for (Truck truk : dataMobil) {
            String id = truk.getId_Kendaraan();
            String mesin = truk.getUkuranmesin_Kendaraan();
            String model = truk.getModel();
            String warna = truk.getWarna();
            String idPegawai = truk.getIdPegawai();
            String jenisBak = truk.getJenis_bak();
            String kapasitas = truk.getKapasitas_maksimum();
            String[] row = {id,mesin,model,warna,jenisBak,kapasitas,idPegawai};
            modeltruk.addRow(row);
        }
        
    }
 

## Screenshot Output Program
### Login sebagai Manajer
![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/37f30e93-110d-4f43-942d-9ed6edd54882)

Pada role Manager, hanya dapat melakukan Read. Pada menu manager, akan ditampilkan table pendataan konfigurasi kendaraan truk dan mobil.

![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/ddf41bdf-0edf-49d2-8724-2104aabccaf0)


Pada menu manager juga terdapat pilihan “DOCUMENT”, dimana berisi ID dokumen, tipe dokumen dan tanggal dibuat dokumen tersebut.

![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/858fda3e-9a43-40cc-9161-3fb24b9ba5e4)
![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/a25b3230-cf01-49b5-a44a-907282ebedf9)


### Login sebagai Admin

Terdapat beberapa pilihan yang dapat dipilih oleh admin, yaitu:

![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/005ed4ae-8267-42b6-86e1-69585d8cc2cd)


- **Truk** - Pada halaman ini, admin dapat menambahkan, menghapus, dan mengubah data truk. 
    - **Tambah Truk** - Pada halaman ini, admin dapat menambahkan data truk.
    - **Hapus Truk** - Pada halaman ini, admin dapat menghapus data truk.
    - **Ubah Truk** - Pada halaman ini, admin dapat mengubah data truk.

![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/fc79fd1f-e685-4fec-b0fc-e91754b0e52f)


- **Car** - Pada halaman ini, admin dapat menambahkan, menghapus, dan mengubah data mobil. 
    - **Tambah Mobil** - Pada halaman ini, admin dapat menambahkan data mobil.
    - **Hapus Mobil** - Pada halaman ini, admin dapat menghapus data mobil.
    - **Ubah Mobil** - Pada halaman ini, admin dapat mengubah data mobil.
      
 ![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/675419e5-57d2-4552-8aff-64121153622a)



