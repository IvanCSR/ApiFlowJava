package DAO;

import Modelo.Reservas;
import java.sql.Date;

public class ReservasDAO {

    public static boolean guardar(Reservas reserva) {
        var res = 0;
        var resulDet = 0;
        var idMax = 0;
        var sql = "insert into reservas(idcliente,fecha,duracion,estado) values(?,?,?,?)";
        try (var con = ConexionDB.Conexion(); var ps = con.prepareStatement(sql)) {
            ps.setInt(1, reserva.cliente().IdCliente());
            ps.setDate(2, Date.valueOf(reserva.fecha()));
            ps.setInt(3, reserva.Duracion());
            ps.setString(4, "Activo");
            res = ps.executeUpdate();
            //Obtener el idMaxReserva
            sql = "select max(idreserva) from reservas";
            try (var ps1 = con.prepareStatement(sql)) {
                var resConsulta = ps1.executeQuery();
                resConsulta.next();
                idMax = resConsulta.getInt(1);
                sql = "insert into detallereservas(idreserva,idhabitacion,duracion,costo) values(?,?,?,?)";
                try (var ps2 = con.prepareStatement(sql)) {
                    for (var ele : reserva.ListaReserva()) {
                        ps2.setInt(1, idMax);
                        ps2.setInt(2, ele.Habitacion().IdHabitacion());                      
                        ps2.setInt(3, ele.Duracion());
                        ps2.setFloat(4, ele.Habitacion().Costo());
                        resulDet = ps2.executeUpdate();
                    }
                } catch (Exception e) {                    
                    System.out.println("Detalle: "+e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res > 0 && resulDet > 0;
    }
}
