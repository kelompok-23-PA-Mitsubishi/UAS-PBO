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
public class Truk extends Kendaraan {
    String jenis_bak;
    String kapasitas_maksimum;
    static Database db = new Database();
    static ArrayList<Truk> ArrayTruk = new ArrayList<>();

    public Truk(String jenis_bak, String kapasitas_maksimum, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) {
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
    public static ArrayList<Truk> readTruk() throws SQLException {
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
            Truk trukBaru = new Truk(jenisBak, kapasitas, id, ukuranMesin, model, warna, idPegawai);
            ArrayTruk.add(trukBaru);
        }
        return ArrayTruk;
    }

        public static void createTruk(String jenis_bak, String kapasitas_maksimum, String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna, String idPegawai) throws SQLException {
            Truk trukBaru = new Truk(jenis_bak, kapasitas_maksimum, id_Kendaraan, ukuranmesin_Kendaraan, model, warna, idPegawai);
            ArrayTruk.add(trukBaru); // Use the correct object name
            String query = String.format("INSERT INTO truk VALUES ('%s','%s','%s')",
                    trukBaru.getId_Kendaraan(),trukBaru.getJenis_bak(), trukBaru.getKapasitas_maksimum());
            db.executeUpdateQuery(query);
        }

        public static void deleteTruk(String id_Kendaraan) throws SQLException {
            for (Truk truk : ArrayTruk) {
                if (truk.getId_Kendaraan().equals(id_Kendaraan)) {
                    ArrayTruk.remove(truk); // Use the correct object name
                    String query = String.format("DELETE FROM truk WHERE id_kendaraan = '%s'", truk.getId_Kendaraan());
                    db.executeUpdateQuery(query);
                    break;
                }
            }
        }


        public static void updateTruk(String bak,String kapasitas, String idTruk) throws SQLException{
            for (Truk truk : ArrayTruk) {
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