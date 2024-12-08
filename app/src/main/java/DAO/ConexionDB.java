
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

public class ConexionDB {
    public static Connection Conexion() throws SQLServerException{
        var dt= new SQLServerDataSource();
        dt.setUser("IvanSJB");
        dt.setPassword("123456");
        dt.setServerName("localhost");
        dt.setPortNumber(1433);
        dt.setDatabaseName("DBHotel");
        dt.setEncrypt("false");       
        return dt.getConnection();
    }
}
