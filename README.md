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
![WhatsApp Image 2023-11-03 at 23 30 57_3fa23591](https://github.com/kelompok-23-PA-Mitsubishi/pbo-dbd-pa/assets/122195566/2257dd9a-520f-4fba-abc5-9f8c30b63f5f)

### Relational model
![WhatsApp Image 2023-11-03 at 23 30 10_ae030ea7](https://github.com/kelompok-23-PA-Mitsubishi/pbo-dbd-pa/assets/122195566/2760e3e6-4625-4162-99f5-e97154e3376a)

## Hirarki Kelas
![image](https://github.com/kelompok-23-PA-Mitsubishi/pbo-dbd-pa/assets/122031507/f9fe68fe-d94c-467e-bda7-b00f71a78b56)

## Screenshot Coding Program
Terdapat 4 Package pada program ini, yaitu:
- **Entity** - Package ini berisi kelas-kelas yang berfungsi untuk menyimpan data-data yang dibutuhkan pada program.
- **Koneksi** - Package ini berisi kelas-kelas yang berfungsi untuk menghubungkan program dengan database.
- **project_akhir_pbo** - Package ini berisi kelas-kelas yang berfungsi untuk mengatur tampilan program.

### Package Entity
#### Dokumen.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author User
 */
public class Dokumen {
    String id_Dokumen;
    String jenis_Dokumen;
    String tanggalterbit_Dokumen;
    int id_Kendaraan;

    public Dokumen(String id_Dokumen, String jenis_Dokumen, String tanggalterbit_Dokumen, int id_Kendaraan) {
        this.id_Dokumen = id_Dokumen;
        this.jenis_Dokumen = jenis_Dokumen;
        this.tanggalterbit_Dokumen = tanggalterbit_Dokumen;
        this.id_Kendaraan = id_Kendaraan;
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

    public int getId_Kendaraan() {
        return id_Kendaraan;
    }

    public void setId_Kendaraan(int id_Kendaraan) {
        this.id_Kendaraan = id_Kendaraan;
    }    
    
}
```
Pada class Dokumen.java terdapat atribut id_Dokumen, jenis_Dokumen, tanggalterbit_Dokumen, dan id_Kendaraan. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Class ini berfungsi untuk menyimpan data-data yang dibutuhkan untuk dokumen kendaraan. 

#### Kendaraan.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.sql.*;
import koneksi.Database;

/**
 *
 * @author Dinnu & Aufa
 */
public class Kendaraan {
    String id_Kendaraan;
    String ukuranmesin_Kendaraan;
    String model;
    String warna;
    static Database db = new Database();
    static ArrayList<Kendaraan> ArrayKendaraan = new ArrayList<>();
    
    public Kendaraan(String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna) {
        this.id_Kendaraan = id_Kendaraan;
        this.ukuranmesin_Kendaraan = ukuranmesin_Kendaraan;
        this.model = model;
        this.warna = warna;
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

    public static ArrayList<Kendaraan> readKendaraan() throws SQLException{
        ArrayKendaraan.clear();
        String query = "SELECT * FROM Kendaraan";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id_kendaraan = rs.getString("ID Kendaraan");
            String ukuran_mesin = rs.getString("Ukuran mesin");
            String model = rs.getString("Model");
            String warna = rs.getString("Warna");
            Kendaraan kendaraanBaru = new Kendaraan(id_kendaraan, ukuran_mesin, model, warna);
            ArrayKendaraan.add(kendaraanBaru);
        }
        return ArrayKendaraan;
    }
    
    public static void createKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna) throws SQLException{
        Kendaraan kendaraanBaru = new Kendaraan(id_kendaraan, ukuran_mesin, model, warna);
        ArrayKendaraan.add(kendaraanBaru);
        String query = String.format("INSERT INTO Kendaraan VALUES ('%s','%s','%s')",
                kendaraanBaru.getId_Kendaraan(),kendaraanBaru.getUkuranmesin_Kendaraan(),kendaraanBaru.getModel(),kendaraanBaru.getWarna());
        db.executeUpdateQuery(query);
    }
    
    public static void deleteKendaraan(String id_Kendaraan) throws SQLException{
        for (Kendaraan kendaraan : ArrayKendaraan) {
            if (kendaraan.getId_Kendaraan().equals(id_Kendaraan)) {
                ArrayKendaraan.remove(kendaraan);
                String query = String.format("DELETE FROM kendaraan WHERE id_kendaraan = '%s'", kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updateKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna) throws SQLException{
        for (Kendaraan kendaraan : ArrayKendaraan) {
            if (kendaraan.getId_Kendaraan().equals(id_kendaraan)) {
                kendaraan.setUkuranmesin_Kendaraan(ukuran_mesin);
                kendaraan.setModel(model);
                kendaraan.setWarna(warna);
                String query = String.format("UPDATE Kendaraan SET ukuran_mesin = '%s', model = '%s' WHERE id_kendaraan = '%s' ",
                        kendaraan.getUkuranmesin_Kendaraan(),kendaraan.getModel(),kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
            }
        }
    }
    
}

```
Pada class Kendaraan.java terdapat atribut id_Kendaraan, ukuranmesin_Kendaraan, model, dan warna. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Terdapat juga method readKendaraan(), createKendaraan(), deleteKendaraan(), dan updateKendaraan(). Class ini berfungsi untuk menyimpan data-data yang dibutuhkan untuk kendaraan.

#### Mobil.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import static Entity.Kendaraan.ArrayKendaraan;
import static Entity.Kendaraan.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import koneksi.Database;

/**
 *
 * @author User
 */
public class Mobil {
    String id_mobil;
    int jumlah_penumpang;
    int jumlah_pintu;
    static Database db = new Database();
    static ArrayList<Mobil> ArrayMobil = new ArrayList<>();

    public Mobil(String id_mobil, int jumlah_penumpang, int jumlah_pintu) {
        this.id_mobil = id_mobil;
        this.jumlah_penumpang = jumlah_penumpang;
        this.jumlah_pintu = jumlah_pintu;
    }

    private Mobil(int penumpang, int pintu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getId_mobil() {
        return id_mobil;
    }

    public void setId_mobil(String id_mobil) {
        this.id_mobil = id_mobil;
    }

    public int getJumlah_penumpang() {
        return jumlah_penumpang;
    }

    public void setJumlah_penumpang(int jumlah_penumpang) {
        this.jumlah_penumpang = jumlah_penumpang;
    }

    public int getJumlah_pintu() {
        return jumlah_pintu;
    }

    public void setJumlah_pintu(int jumlah_pintu) {
        this.jumlah_pintu = jumlah_pintu;
    }

    public static Database getDb() {
        return db;
    }

    public static void setDb(Database db) {
        Mobil.db = db;
    }

    public static ArrayList<Mobil> getArrayMobil() {
        return ArrayMobil;
    }

    public static void setArrayMobil(ArrayList<Mobil> ArrayMobil) {
        Mobil.ArrayMobil = ArrayMobil;
    }

public static ArrayList<Mobil> readMobil() throws SQLException {
    ArrayList<Mobil> ArrayMobil = new ArrayList<Mobil>(); // Create a new ArrayList
    String query = "SELECT * FROM Mobil";
    ResultSet rs = db.executeSelectQuery(query);
    while (rs.next()) {
        int jumlah_penumpang = rs.getInt("Jumlah Penumpang");
        int jumlah_pintu = rs.getInt("Jumlah Pintu");
        Mobil mobilBaru = new Mobil(jumlah_penumpang, jumlah_pintu);
        ArrayMobil.add(mobilBaru);
    }
    return ArrayMobil;
}
    
public static void createMobil(int pintu, int penumpang) throws SQLException {
    Mobil mobilBaru = new Mobil(penumpang, pintu);
    ArrayMobil.add(mobilBaru); // Use the correct object name
    String query = String.format("INSERT INTO mobil VALUES ('%s','%s')",
            mobilBaru.getJumlah_penumpang(), mobilBaru.getJumlah_pintu());
    db.executeUpdateQuery(query);
}
    
public static void deleteMobil(String id_Kendaraan) throws SQLException {
    for (Mobil mobil : ArrayMobil) {
        if (mobil.getId_mobil().equals(id_Kendaraan)) {
            ArrayMobil.remove(mobil); // Use the correct object name
            String query = String.format("DELETE FROM Mobil WHERE id_kendaraan = '%s'", mobil.getId_mobil());
            db.executeUpdateQuery(query);
            break;
        }
    }
}

    
    public static void updateKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna) throws SQLException{
        for (Mobil kendaraan : ArrayMobil) {
            if (kendaraan.getId_Kendaraan().equals(id_kendaraan)) {
                kendaraan.setUkuranmesin_Kendaraan(ukuran_mesin);
                kendaraan.setModel(model);
                kendaraan.setWarna(warna);
                String query = String.format("UPDATE Kendaraan SET ukuran_mesin = '%s', model = '%s' WHERE id_kendaraan = '%s' ",
                        kendaraan.getUkuranmesin_Kendaraan(),kendaraan.getModel(),kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
            }
        }
    }
    
}

```
Pada class Mobil.java terdapat atribut id_mobil, jumlah_penumpang, dan jumlah_pintu. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Terdapat juga method readMobil(), createMobil(), deleteMobil(), dan updateMobil(). Class ini berfungsi untuk menyimpan data-data yang dibutuhkan untuk mobil.

#### Pegawai.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import koneksi.Database;
import java.sql.*;

/**
 *
 * @author User
 */
public class Pegawai {
    String id_pegawai;
    String nama_pegawai;
    String nohp_pegawai;
    String jabatan_pegawai;
    String pass_pegawai;
    String pegawaiIdPegawai;
    
    static Database db = new Database();
    static ArrayList<Pegawai> arrayPegawai = new ArrayList<>();

    public Pegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) {
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
    
    public static ArrayList<Pegawai> readPegawai() throws SQLException{
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
            Pegawai barangBaru = new Pegawai(id, nama, pass, nohp, jabatan, idPegawai);
            
        }return arrayPegawai;
    }
    
    public static void createPegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) throws SQLException{
        Pegawai barangBaru = new Pegawai(id_pegawai, nama_pegawai, nohp_pegawai, jabatan_pegawai, pass_pegawai, pegawaiIdPegawai);
        arrayPegawai.add(barangBaru);
        String query = String.format("INSERT INTO pegawai VALUES ('%s','%s','%s','%s','%s','%s')",
                barangBaru.getId_pegawai(),barangBaru.getNama_pegawai(),barangBaru.getPass_pegawai(),barangBaru.getNohp_pegawai(),barangBaru.getJabatan_pegawai(),barangBaru.getPegawaiIdPegawai());
        db.executeUpdateQuery(query);
    }
    
    public static void deletePegawai(String idPegawai) throws SQLException{
        for (Pegawai pegawai : arrayPegawai) {
            if (pegawai.getId_pegawai().equals(idPegawai)) {
                arrayPegawai.remove(pegawai);
                String query = String.format("DELETE FROM barang WHERE id_barang = '%s'", pegawai.getId_pegawai());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updatePegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) throws SQLException{
        for (Pegawai pegawai : arrayPegawai) {
            if (pegawai.getId_pegawai().equals(id_pegawai)) {
                pegawai
                String query = String.format("UPDATE barang SET nama_barang = '%s', harga_barang = '%s' WHERE id_barang = '%s' ",
                        barang.getNamaBarang(),barang.getHargaBarang(),barang.getIdBarang());
                db.executeUpdateQuery(query);
            }
        }
    }
}
```
Pada class Pegawai.java terdapat atribut id_pegawai, nama_pegawai, nohp_pegawai, jabatan_pegawai, pass_pegawai, dan pegawaiIdPegawai. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Terdapat juga method readPegawai(), createPegawai(), deletePegawai(), dan updatePegawai(). Class ini berfungsi untuk menyimpan data-data yang dibutuhkan untuk pegawai.

#### Truk.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import koneksi.Database;

/**
 *
 * @author User
 */
public class Truk extends Kendaraan {
    String jenis_bak;
    String kapasitas_maksimum;
    static Database db = new Database();
    static ArrayList<Truk> ArrayTruk = new ArrayList<>();

    public Truk(String jenis_bak, String kapasitas_maksimum, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna) {
        super(id_Kendaraan, ukuranmesin_Kendaraan, model, warna);
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
    
}

```
Pada class Truk.java terdapat atribut jenis_bak dan kapasitas_maksimum. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Class ini berfungsi untuk menyimpan data-data yang dibutuhkan untuk truk. Class ini merupakan subclass dari class Kendaraan.

Terdapat Array List untuk setiap class yang berfungsi untuk menyimpan data-data yang dibutuhkan untuk class tersebut.

### Package Koneksi
Pada package Koneksi terdapat class Database.java yang berfungsi untuk menghubungkan program dengan database.

#### Database.java
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Database {
    public static Connection mysqlconfig;
    private Statement st;
    private Connection con;
    private ResultSet rs;

    public Database(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitsubishi","root","");
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nyalakan MYSQL terlebih dahulu");
        }
    }
    
    public ResultSet executeSelectQuery(String qq) throws SQLException{
        rs = st.executeQuery(qq);
        return rs;
    }
    
    public void executeUpdateQuery(String query) throws SQLException{
        st.execute(query);
    }
}
```
Pada class Database.java terdapat atribut mysqlconfig, st, con, dan rs. Terdapat juga constructor, getter, dan setter untuk atribut-atribut tersebut. Terdapat juga method executeSelectQuery() dan executeUpdateQuery(). Class ini berfungsi untuk menghubungkan program dengan database.

- executeSelectQuery() berfungsi untuk mengeksekusi query yang mengembalikan nilai.
- executeUpdateQuery() berfungsi untuk mengeksekusi query yang tidak mengembalikan nilai.

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
    - 
 ![image](https://github.com/kelompok-23-PA-Mitsubishi/UAS-PBO/assets/122031507/675419e5-57d2-4552-8aff-64121153622a)



