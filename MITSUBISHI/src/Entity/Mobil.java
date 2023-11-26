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
public class Mobil extends Kendaraan{
    String jumlah_penumpang;
    String jumlah_pintu;
    static Database db = new Database();
    static ArrayList<Mobil> ArrayMobil = new ArrayList<>();

    public Mobil(String jumlah_penumpang, String jumlah_pintu, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) {
        super(id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
        this.jumlah_penumpang = jumlah_penumpang;
        this.jumlah_pintu = jumlah_pintu;
    }

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
    
    public static ArrayList<Mobil> readMobil() throws SQLException {
        ArrayMobil.clear();
        String query = "SELECT kendaraan.*, mobil.jumlah_penumpang, mobil.jumlah_pintu FROM kendaraan INNER JOIN mobil ON kendaraan.id_kendaraan = mobil.id_kendaraan;";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {
            String id = rs.getString("id_kendaraan");
            String ukuranMesin = rs.getString("ukuran_mesin");
            String model = rs.getString("model");
            String warna = rs.getString("warna");
            String idPegawai = rs.getString("Pegawai_id_Pegawai");
            String jumlahPenumpang = rs.getString("jumlah_penumpang");
            String jumlahPintu = rs.getString("jumlah_pintu");
            Mobil mobilBaru = new Mobil(jumlahPenumpang, jumlahPintu, id, ukuranMesin, model, warna, idPegawai);
            ArrayMobil.add(mobilBaru);
        }
        return ArrayMobil;
    }

        public static void createMobil(String jumlah_penumpang, String jumlah_pintu, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) throws SQLException {
            Mobil mobilBaru = new Mobil(jumlah_penumpang, jumlah_pintu, id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
            ArrayMobil.add(mobilBaru); // Use the correct object name
            String query = String.format("INSERT INTO mobil VALUES ('%s','%s','%s')",
                    mobilBaru.getId_Kendaraan(),mobilBaru.getJumlah_penumpang(), mobilBaru.getJumlah_pintu());
            db.executeUpdateQuery(query);
        }

        public static void deleteMobil(String id_Kendaraan) throws SQLException {
            for (Mobil mobil : ArrayMobil) {
                if (mobil.getId_Kendaraan().equals(id_Kendaraan)) {
                    ArrayMobil.remove(mobil); // Use the correct object name
                    String query = String.format("DELETE FROM mobil WHERE id_kendaraan = '%s'", mobil.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }


        public static void updateMobil(String pintu, String penumpang, String idMobil) throws SQLException{
            for (Mobil mobil : ArrayMobil) {
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