
package controller;

import connection.SCOTTConnection;
import static controller.ControllerEmployee.connection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import model.Department;

public class ControllerDepartment {
    
    public static Connection connection = null;

    public ControllerDepartment() {
        
        if(connection == null){
            connection = new SCOTTConnection().getConnection();
        }
        
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
    }
    
    public boolean addDepartment(Department department){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call add_depto( ? , ? , ? ) }");
            
            cstmt.setInt(1, department.getDeptNo());
            cstmt.setString(2, department.getdName());
            cstmt.setString(3, department.getLoc());
            
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("NO SE PUDO AÑADIR DEPARTAMENTO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);
            return false;
        }
    }
    
    
    public boolean updateDepartment(int deptNo, Department department){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call update_depto( ? , ? , ? , ?) }");
            
            cstmt.setInt(1, deptNo);
            cstmt.setInt(2, department.getDeptNo());
            cstmt.setString(3, department.getdName());
            cstmt.setString(4, department.getLoc());
            
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("NO SE PUDO ACTUALIZAR DEPARTAMENTO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);
            return false;
        }
    }
    
        
    public boolean deleteDepto(int deptNo){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call delete_depto( ? )}");
            cstmt.setInt(1,deptNo);
            cstmt.execute();
            System.out.println("Departamento con número " + deptNo + " ha sido eliminado");
            return true;
        } catch (Exception e) {
            System.out.println("NO SE PUDO ELIMINAR DEPARTAMENTO");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);;
            return false;
        }
    }
    
    public int getNumberOfEmployees(int deptNo){
        try{
            CallableStatement cstmt = connection.prepareCall("{ ? = call noemp_depto( ? )}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2,deptNo);
            cstmt.execute();
            return cstmt.getInt(1);
            
        }catch(Exception e){
            System.out.println("NO SE PUDO ENCONTRAR LA SUMA");
            System.out.println("DETALLES DEL ERROR ");
            System.out.println("--------------------------");
            System.out.println(e);;
        }
        
        return 0;
    }
    
}
