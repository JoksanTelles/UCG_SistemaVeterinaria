package DAO;

import POJO.CitasPOJO;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CitasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarCita(CitasPOJO citas) {
        String sql = """
                     INSERT INTO citas(
                        fecha,
                        hora,
                        motivo,
                        id_mascota,
                        id_servicio
                     ) VALUES (?,?,?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, citas.getFecha());
            ps.setString(2, citas.getHora());
            ps.setString(3, citas.getMotivo());
            ps.setInt(4, citas.getIdMascota());
            ps.setInt(5, citas.getIdServicio());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro de citas exitoso.");
            
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
    
    public List<CitasPOJO> listarCitas() {
        List<CitasPOJO> listaCitas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        
        try {
            con = cn.getConnection();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while (rs.next()) {
                CitasPOJO citas = new CitasPOJO();
                
                citas.setIdCita(rs.getInt("id_citas"));
                citas.setFecha(rs.getDate("fecha"));
                citas.setHora(rs.getString("hora"));
                citas.setMotivo(rs.getString("motivo"));
                citas.setIdMascota(rs.getInt("id_mascota"));
                citas.setIdServicio(rs.getInt("id_servicio"));
                
                listaCitas.add(citas);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return listaCitas;
    }
    
    public boolean modificarCita(CitasPOJO citas) {
        String sql = """
                     UPDATE citas
                     SET fecha = ?, hora = ?, motivo = ?,
                        id_mascota = ?, id_servicio = ?
                     WHERE id_citas = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, citas.getFecha());
            ps.setString(2, citas.getHora());
            ps.setString(3, citas.getMotivo());
            ps.setInt(4, citas.getIdMascota());
            ps.setInt(5, citas.getIdServicio());
            ps.setInt(6, citas.getIdCita());
            
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
    
    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id_citas = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Cita eliminado exitosamente.");
            
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
