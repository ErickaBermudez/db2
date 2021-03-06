
package controller;

import connection.SCOTTConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import model.Employee;

public class ControllerEmployee {
    
    public static Connection connection = null;

    public ControllerEmployee() {
        
        if(connection == null){
            connection = new SCOTTConnection().getConnection();
        }
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
    }
    
    public boolean addEmployee(Employee employee){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call add_emp( ? , ? , ? , ? , ? , ? , ? , ? ) }");
            
            cstmt.setInt(1, employee.getEmpNo());
            cstmt.setString(2, employee.geteName());
            cstmt.setString(3, employee.getJob());
            cstmt.setInt(4, employee.getMgr());
            cstmt.setDate(5, (java.sql.Date)employee.getHireDate());
            cstmt.setInt(6, employee.getSal());
            cstmt.setInt(7, employee.getComm());
            cstmt.setInt(8, employee.getDeptNo());
            
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("NO SE PUDO AÑADIR EMPLEADO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updateEmployee(int empNo, Employee employee){
        try {
            
            CallableStatement cstmt = connection.prepareCall("{ call update_emp( ? , ? , ? , ? , ? , ? , ? , ? , ? ) }");
            //IN values
            cstmt.setInt(1, empNo);
            cstmt.setInt(2, employee.getEmpNo());
            cstmt.setString(3, employee.geteName());
            cstmt.setString(4, employee.getJob());
            cstmt.setInt(5, employee.getMgr());
            cstmt.setDate(6, employee.getHireDate());
            cstmt.setInt(7, employee.getSal());
            cstmt.setInt(8, employee.getComm());
            cstmt.setInt(9, employee.getDeptNo());
            
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("NO SE PUDO ACTUALIZAR EMPLEADO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deleteEmployee(int empNo){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call delete_emp( ? )}");
            cstmt.setInt(1,empNo);
            cstmt.execute();
            System.out.println("Empleado con ID " + empNo + " ha sido eliminado");
            return true;
        } catch (Exception e) {
              System.out.println("NO SE PUDO ELIMINAR EMPLEADO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);
            return false;
        }
    }
}
