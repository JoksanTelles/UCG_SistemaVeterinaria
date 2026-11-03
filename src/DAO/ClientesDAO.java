package DAO;

import POJO.ClientesPOJO;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarCliente(ClientesPOJO cliente) {
        String sql = """
                     INSERT INTO clientes(
                        nombre,
                        apellido_p,
                        apellido_m,
                        telefono,
                        correo,
                        direccion,
                        fecha_registro
                     ) VALUES (?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoP());
            ps.setString(3, cliente.getApellidoM());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getDireccion());
            ps.setDate(7, cliente.getFechaRegistro());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro de cliente exitoso.");
            
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
    
    public List<ClientesPOJO> listarClientes() {
        List<ClientesPOJO> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        
        try {
            con = cn.getConnection();
            ps  = con.prepareStatement(sql);
            rs  = ps.executeQuery();
            
            while (rs.next()) {
                ClientesPOJO cliente = new ClientesPOJO();
                
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidoP(rs.getString("apellido_p"));
                cliente.setApellidoM(rs.getString("apellido_m"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setFechaRegistro(rs.getDate("fecha_registro"));
                
                listaClientes.add(cliente);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return listaClientes;
    }
    
    public boolean modificarCliente(ClientesPOJO cliente) {
        String sql = """
                     UPDATE clientes
                     SET nombre = ?, apellido_p = ?, apellido_m = ?,
                        telefono = ?, correo = ?, direccion = ?, fecha_registro = ?
                     WHERE id_cliente = ?
                     """;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoP());
            ps.setString(3, cliente.getApellidoM());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getDireccion());
            ps.setDate(7, cliente.getFechaRegistro());
            ps.setInt(8, cliente.getIdCliente());
            
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
    
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");
            
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
