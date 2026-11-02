package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    Connection con;
    String URL = "jdbc:mysql://localhost:3306/veterinaria";
    String USER = "root";
    String PASS = "";
    
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USER,PASS);
            
            System.out.println("Conexion exitosa.");
            
            return con;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        Conexion c = new Conexion();
        c.getConnection();
    }
}
