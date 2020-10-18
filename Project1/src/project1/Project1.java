package project1;

import controller.ControllerDepartment;
import controller.ControllerEmployee;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Employee;

public class Project1 {

    private static final int ADD_EMPLOYEE = 1;
    private static final int UPDATE_EMPLOYEE = 2;
    private static final int DELETE_EMPLOYEE = 3;
    private static final int ADD_DEPARTMENT = 4;
    private static final int UPDATE_DEPARTMENT = 5;
    private static final int DELETE_DEPARTMENT = 6;
    private static final int GET_COUNT = 7;
    public static void main(String[] args) {
            ControllerEmployee contEmp = new ControllerEmployee();
            ControllerDepartment contDept = new ControllerDepartment();
            
            // test
            /*
            java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
            Employee ericka = new Employee(4032, "ERICKA", "ANALYST", 7566, today, 4000, 5, 20);
            cont.updateEmployee(2020, ericka);
            */
            Scanner in = new Scanner(System.in);
            int option = 1;
            Employee employee;
            int empNo;
            
            Department department;
            int deptNo;
            
            System.out.println("BIENVENIDO A SCOTT");
            while (option != 0) {
                System.out.println("---------------------------");
                System.out.println("¿Qué desea hacer?");
                System.out.println("1 - Añadir nuevo empleado");
                System.out.println("2 - Actualizar empleado");
                System.out.println("3 - Eliminar empleado");
                System.out.println("4 - Crear departamento");
                System.out.println("5 - Actualizar departamento");
                System.out.println("6 - Eliminar departamento");
                System.out.println("7 - Ver número de empleados en departamento");
                System.out.println("0 - Salir");
                System.out.println("---------------------------");

                option = in.nextInt();

                switch (option) {
                    case ADD_EMPLOYEE:
                        employee = newEmployee();
                        contEmp.addEmployee(employee);
                        break;
                    case UPDATE_EMPLOYEE: 
                        System.out.println("ID empleado: ");
                        empNo = in.nextInt();
                        employee = newEmployee();
                        contEmp.updateEmployee(empNo, employee);
                        break;
                    case DELETE_EMPLOYEE:
                        System.out.println("ID empleado: ");
                        empNo = in.nextInt();
                        contEmp.deleteEmployee(empNo);
                        break;
                    case ADD_DEPARTMENT: 
                        department = newDepartment();
                        contDept.addDepartment(department);
                        break;
                    case UPDATE_DEPARTMENT: 
                        System.out.println("Número de departamento: ");
                        deptNo = in.nextInt();
                        department = newDepartment();
                        contDept.updateDepartment(deptNo, department);
                        break;
                    case DELETE_DEPARTMENT: 
                        System.out.println("Número de departamento: ");
                        deptNo = in.nextInt();
                        contDept.deleteDepto(deptNo);
                        break;
                    case GET_COUNT: 
                        System.out.println("Número de departamento: ");
                        deptNo = in.nextInt();
                        int count = contDept.getNumberOfEmployees(deptNo);
                        System.out.println("El departamento #" + deptNo + " tiene " +  count + " empleados");
                }
            }
            
        try {
            contEmp.closeConnection();
            contDept.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Hasta pronto!");
    }

    public static Employee newEmployee() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());

        Scanner in = new Scanner(System.in);

        System.out.println("ID Nuevo: ");
        int id = in.nextInt();
        System.out.println("Nombre: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.println("Trabajo: ");
        String job = in.nextLine();
        System.out.println("Manager ID: ");
        int manager = in.nextInt();
        System.out.println("Salario: ");
        int salary = in.nextInt();
        System.out.println("Comision: ");
        int commission = in.nextInt();
        System.out.println("Departamento: ");
        int department = in.nextInt();

        Employee employee = new Employee(id, name, job, manager, today, salary, commission, department);
        return employee;

    }
    
    public static Department newDepartment(){
        Scanner in = new Scanner(System.in);
        System.out.println("Número de departamento Nuevo: ");
        int deptNo = in.nextInt();
        System.out.println("Nombre del departamento: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.println("Ubicación del departamento: ");
        String loc = in.nextLine();
        
        Department department = new Department(deptNo, name,  loc);
        return department; 
    }

}
