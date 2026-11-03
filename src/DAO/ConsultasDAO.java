package DAO;

import POJO.ConsultasPOJO;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarConsulta(ConsultasPOJO consultas) {
        String sql = """
                     INSERT INTO consultas(
                        fecha_consulta,
                        sintomas,
                        diagnostico,
                        tratamiento,
                        observaciones,
                        id_cita
                     ) VALUES (?,?,?,?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, consultas.getFechaConsulta());
            ps.setString(2, consultas.getSintomas());
            ps.setString(3, consultas.getDiagnostico());
            ps.setString(4, consultas.getTratamiento());
            ps.setString(5, consultas.getObservaciones());
            ps.setInt(6, consultas.getIdCita());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro de consultas exitoso.");
            
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
    
    public List<ConsultasPOJO> listarConsultas() {
        List<ConsultasPOJO> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        
        try {
            con = cn.getConnection();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while (rs.next()) {
                ConsultasPOJO consultas = new ConsultasPOJO();
                
                consultas.setIdConsulta(rs.getInt("id_consulta"));
                consultas.setFechaConsulta(rs.getDate("fecha_consulta"));
                consultas.setSintomas(rs.getString("sintomas"));
                consultas.setDiagnostico(rs.getString("diagnostico"));
                consultas.setTratamiento(rs.getString("tratamiento"));
                consultas.setObservaciones(rs.getString("observaciones"));
                consultas.setIdCita(rs.getInt("id_cita"));
                
                listaConsultas.add(consultas);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return listaConsultas;
    }
    
    public boolean modificarConsulta(ConsultasPOJO consultas) {
        String sql = """
                     UPDATE consultas
                     SET fecha_consulta = ?, sintomas = ?, diagnostico = ?,
                        tratamiento = ?, observaciones = ?, id_cita = ?
                     WHERE id_consulta = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, consultas.getFechaConsulta());
            ps.setString(2, consultas.getSintomas());
            ps.setString(3, consultas.getDiagnostico());
            ps.setString(4, consultas.getTratamiento());
            ps.setString(5, consultas.getObservaciones());
            ps.setInt(6, consultas.getIdCita());
            ps.setInt(7, consultas.getIdConsulta());
            
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
    
    public boolean eliminarConsulta(int id) {
        String sql = "DELETE FROM consultas WHERE id_consulta = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Consulta eliminado exitosamente.");
            
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
