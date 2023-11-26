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
public class Kendaraan {
    String id_Kendaraan;
    String ukuranmesin_Kendaraan;
    String model;
    String warna;
    String idPegawai;
    static Database db = new Database();
    static ArrayList<Kendaraan> arrayDokumen = new ArrayList<>();
    
    public Kendaraan(String id_Kendaraan, String ukuranmesin_Kendaraan, String model, String warna,String idPegawai) {
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

    public static ArrayList<Kendaraan> readKendaraan() throws SQLException{
        arrayDokumen.clear();
        String query = "SELECT * FROM kendaraan";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id_kendaraan = rs.getString("id_kendaraan");
            String ukuran_mesin = rs.getString("ukuran_mesin");
            String model = rs.getString("model");
            String warna = rs.getString("warna");
            String idPegawai = rs.getString("Pegawai_id_pegawai");
            Kendaraan kendaraanBaru = new Kendaraan(id_kendaraan, ukuran_mesin, model, warna,idPegawai);
            arrayDokumen.add(kendaraanBaru);
        }
        return arrayDokumen;
    }
    
    public static void createKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna,String idPegawai) throws SQLException{
        try {
            Kendaraan kendaraanBaru = new Kendaraan(id_kendaraan, ukuran_mesin, model, warna,idPegawai);
            arrayDokumen.add(kendaraanBaru);
            String query = String.format("INSERT INTO kendaraan VALUES ('%s','%s','%s','%s','%s')",
                    kendaraanBaru.getId_Kendaraan(),kendaraanBaru.getUkuranmesin_Kendaraan(),kendaraanBaru.getModel(),kendaraanBaru.getWarna(),kendaraanBaru.getIdPegawai());
            db.executeUpdateQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Duplicate Vehicle ID!");
        }
    }
    
    public static void deleteKendaraan(String id_Kendaraan) throws SQLException{
        for (Kendaraan kendaraan : arrayDokumen) {
            if (kendaraan.getId_Kendaraan().equals(id_Kendaraan)) {
                arrayDokumen.remove(kendaraan);
                String query = String.format("DELETE FROM kendaraan WHERE id_kendaraan = '%s'", kendaraan.getId_Kendaraan());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updateKendaraan(String id_kendaraan, String ukuran_mesin, String model, String warna,String idKaryawan) throws SQLException{
        for (Kendaraan kendaraan : arrayDokumen) {
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
