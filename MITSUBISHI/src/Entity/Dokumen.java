/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import static Entity.Kendaraan.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Dokumen {
    String id_Dokumen;
    String jenis_Dokumen;
    String tanggalterbit_Dokumen;
    static ArrayList<Dokumen> arrayDokumen = new ArrayList<>();

    public Dokumen(String id_Dokumen, String jenis_Dokumen, String tanggalterbit_Dokumen) {
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
    
    public static ArrayList<Dokumen> readDokumen() throws SQLException{
        arrayDokumen.clear();
        String query = "SELECT * FROM dokumen";
        ResultSet rs = db.executeSelectQuery(query);
        while (rs.next()) {            
            String id = rs.getString("id_dokumen");
            String jenis = rs.getString("jenis_dokumen");
            String tanggal = rs.getString("tanggalterbit_dokumen");
            Dokumen dokumenBaru = new Dokumen(id, jenis, tanggal);
            arrayDokumen.add(dokumenBaru);
        }
        return arrayDokumen;
    }
    
    public static void createKendaraan(String id_Dokumen, String jenis_Dokumen, String tanggalterbit_Dokumen) throws SQLException{
        Dokumen dokumenBaru = new Dokumen(id_Dokumen, jenis_Dokumen, tanggalterbit_Dokumen);
        arrayDokumen.add(dokumenBaru);
        String query = String.format("INSERT INTO `dokumen`(`jenis_dokumen`, `tanggalterbit_dokumen`) VALUES ('%s','%s');",
                dokumenBaru.getJenis_Dokumen(),dokumenBaru.getTanggalterbit_Dokumen());
        db.executeUpdateQuery(query);
    } 
}

