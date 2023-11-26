/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercedes-benz","root","");
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