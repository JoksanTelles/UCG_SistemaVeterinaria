package DAO;

import POJO.MascotasPOJO;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MascotasDAO {
    // gh
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarMascota(MascotasPOJO mascota) {
        String sql = """
                     INSERT INTO mascotas(
                        tipo_mascota,
                        descripcion,
                        costo
                     ) VALUES (?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mascota.getTipoMascota());
            ps.setString(2, mascota.getDescripcion());
            ps.setDouble(3, mascota.getCosto());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro de mascota exitoso.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public List<MascotasPOJO> listarMascotas() {
        List<MascotasPOJO> listaMascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        
        try {
            con = cn.getConnection();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while (rs.next()) {
                MascotasPOJO mascota = new MascotasPOJO();
                
                mascota.setIdMascota(rs.getInt("id_mascota"));
                mascota.setTipoMascota(rs.getString("tipo_mascota"));
                mascota.setDescripcion(rs.getString("descripcion"));
                mascota.setCosto(rs.getDouble("costo"));
                
                listaMascotas.add(mascota);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return listaMascotas;
    }
    
    public boolean modificarMascota(MascotasPOJO mascota) {
        String sql = """
                     UPDATE mascotas
                     SET tipo_mascota = ?, descripcion = ?, costo = ?
                     WHERE id_mascota = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mascota.getTipoMascota());
            ps.setString(2, mascota.getDescripcion());
            ps.setDouble(3, mascota.getCosto());
            ps.setInt(4, mascota.getIdMascota());
            
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean eliminarMascota(int id) {
        String sql = "DELETE FROM mascotas WHERE id_mascota = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Mascota eliminado exitosamente.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
}
