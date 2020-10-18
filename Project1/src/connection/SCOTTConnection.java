/**
 *
 * @author Marce & Ericka
 */

package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SCOTTConnection {
    Connection connection = null;
    
    public SCOTTConnection(){
        openConnection();
    }
    
    private void openConnection(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "scott", "scott");
            if(connection != null){
                System.out.println("Conexión exitosa a SCOTT");
            }
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println("Error de conexión a SCOTT");
            System.out.println(ex);
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection(){
        try {
            connection.close();
            System.out.println("Conexión a SCOTT cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(SCOTTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
