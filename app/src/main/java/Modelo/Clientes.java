
package Modelo;


public record Clientes(
        int IdCliente,
        String Nombres,
        String Apellidos,
        String Dni,
        String Genero,
        String Direccion
        )implements IFuncionalidad {
    
    @Override
    public String[] getFilaDatos() {
        return new String[]{String.valueOf(IdCliente),Nombres,Apellidos,Dni,Genero,Direccion};
    }

}
