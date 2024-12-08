
package Modelo;

public record DetalleReservas(
        int IdReserva,        
        Habitaciones Habitacion,
        int Duracion      
        ) implements IFuncionalidad{
    
    public float Importe(){return Habitacion.Costo()*Duracion;}

    @Override
    public String[] getFilaDatos() {
        return new String[]{String.valueOf(Habitacion.IdHabitacion()),Habitacion.Nro(),Habitacion.Descripcion(),
            String.valueOf(Duracion),String.format("%.2f", Habitacion.Costo()),String.format("%.2f", Importe())};
    }

}
