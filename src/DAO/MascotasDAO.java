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
                        nombre,
                        especie,
                        raza,
                        sexo,
                        peso,
                        vacuna,
                        edad,
                        id_cliente
                     ) VALUES (?,?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPeso());
            ps.setString(6, mascota.getVacuna());
            ps.setInt(7, mascota.getEdad());
            ps.setInt(8, mascota.getIdCliente());
            
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
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setSexo(rs.getString("sexo"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setVacuna(rs.getString("vacuna"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                
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
                     SET nombre = ?, especie = ?, raza = ?, sexo = ?,
                        peso = ?, vacuna = ?, edad = ?, id_cliente = ?
                     WHERE id_mascota = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPeso());
            ps.setString(6, mascota.getVacuna());
            ps.setInt(7, mascota.getEdad());
            ps.setInt(8, mascota.getIdCliente());
            ps.setInt(9, mascota.getIdMascota());
            
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
