package DAO;

import POJO.ServiciosPOJO;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ServiciosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarServicio(ServiciosPOJO servicio) {
        String sql = """
                     INSERT INTO servicios(
                        tipo_servicio,
                        descripcion,
                        costo
                     ) VALUES (?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, servicio.getTipoServicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getCosto());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro de servicio exitoso.");
            
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
    
    public List<ServiciosPOJO> listarServicios() {
        List<ServiciosPOJO> listaServicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios";
        
        try {
            con = cn.getConnection();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while (rs.next()) {
                ServiciosPOJO servicio = new ServiciosPOJO();
                
                servicio.setIdServicio(rs.getInt("id_servicio"));
                servicio.setTipoServicio(rs.getString("tipo_servicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setCosto(rs.getDouble("costo"));
                
                listaServicios.add(servicio);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return listaServicios;
    }
    
    public boolean modificarServicio(ServiciosPOJO servicio) {
        String sql = """
                     UPDATE servicios
                     SET tipo_servicio = ?, descripcion = ?, costo = ?
                     WHERE id_servicio = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, servicio.getTipoServicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getCosto());
            ps.setInt(4, servicio.getIdServicio());
            
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
    
    public boolean eliminarServicio(int id) {
        String sql = "DELETE FROM servicios WHERE id_servicio = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Servicio eliminado exitosamente.");
            
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
