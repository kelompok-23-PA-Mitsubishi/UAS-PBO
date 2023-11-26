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
                String query = String.format("DELETE FROM pegawai WHERE id_barang = '%s'", pegawai.getId_pegawai());
                db.executeUpdateQuery(query);
                break;
            }
        }
    }
    
    public static void updatePegawai(String id_pegawai, String nama_pegawai, String nohp_pegawai, String jabatan_pegawai, String pass_pegawai,String pegawaiIdPegawai) throws SQLException{
        for (Pegawai pegawai : arrayPegawai) {
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
