package DAO;

import Modelo.Clientes;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

    public static boolean guardar(Clientes cliente) {
        var res = 0;
        var sql = "insert into clientes(nombres,apellidos,dni,genero,direccion) values(?,?,?,?,?)";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {            
            ps.setString(1, cliente.Nombres());
            ps.setString(2, cliente.Apellidos());
            ps.setString(3, cliente.Dni());
            ps.setString(4, cliente.Genero());
            ps.setString(5, cliente.Direccion());
            res=ps.executeUpdate();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return res>0;
    }
    public static boolean actualizar(Clientes cliente) {
        var res = 0;
        var sql = "update clientes set nombres=?,apellidos=?,dni=?,genero=?,direccion=? where idcliente=?";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.Nombres());
            ps.setString(2, cliente.Apellidos());
            ps.setString(3, cliente.Dni());
            ps.setString(4, cliente.Genero());
            ps.setString(5, cliente.Direccion());
            ps.setInt(6, cliente.IdCliente());
            res=ps.executeUpdate();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return res>0;
    }
    public static boolean eliminar(Clientes cliente) {
        var res = 0;
        var sql = "delete from clientes where idcliente=?";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {            
            ps.setInt(1, cliente.IdCliente());
            res=ps.executeUpdate();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return res>0;
    }
    public static List<Clientes> listaClientes() {
        var lista=new ArrayList<Clientes>();      
        var sql = "select idcliente,nombres,apellidos,dni,genero,direccion from clientes";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {           
            var rs=ps.executeQuery();
            while (rs.next()) {                
                lista.add(new Clientes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public static List<Clientes> listaClientes(String dni) {
        var lista=new ArrayList<Clientes>();      
        var sql = "select idcliente,nombres,apellidos,dni,genero,direccion from clientes where dni like ?+'%'";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {     
            ps.setString(1, dni);
            var rs=ps.executeQuery();
            while (rs.next()) {                
                lista.add(new Clientes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
