
package DAO;

import Modelo.Habitaciones;
import java.util.ArrayList;
import java.util.List;


public class HabitacionesDAO {
    public static List<Habitaciones> listaHabitaciones(String nro) {
        var lista=new ArrayList<Habitaciones>();      
        var sql = "select idhabitacion,nro,descripcion,costo from habitaciones where nro like ?+'%'";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {     
            ps.setString(1, nro);
            var rs=ps.executeQuery();
            while (rs.next()) {                
                lista.add(new Habitaciones(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
