
package Modelo;

public record Habitaciones(
        int IdHabitacion,
        String Nro,
        String Descripcion,
        float Costo
        )implements IFuncionalidad {

    @Override
    public String[] getFilaDatos() {
        return new String[]{String.valueOf(IdHabitacion),Nro,Descripcion,String.format("%.2f", Costo)};
    }

}
