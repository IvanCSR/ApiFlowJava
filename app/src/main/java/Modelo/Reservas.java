package Modelo;

import java.time.LocalDate;
import java.util.List;

public record Reservas(
        int IdReserva,
        Clientes cliente,
        LocalDate fecha,
        int Duracion,
        String estado,
        List<DetalleReservas> ListaReserva) implements IFuncionalidad {

    @Override
    public String[] getFilaDatos() {
        return new String[]{String.valueOf(IdReserva), cliente.Nombres(), fecha.toString(),
            String.valueOf(IdReserva), estado};
    }

}
