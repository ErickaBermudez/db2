package project1;

import connection.SCOTTConnection;
import controller.ControllerEmployee;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
import model.Employee;

public class Project1 {

    private static final int ADD_EMPLOYEE = 1;
    private static final int UPDATE_EMPLOYEE = 2;
    private static final int DELETE_EMPLOYEE = 3;
    public static void main(String[] args) {
            Calendar cal = Calendar.getInstance();

            ControllerEmployee cont = new ControllerEmployee();
            
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
            
            System.out.println("BIENVENIDO A SCOTT");
            while (option != 0) {
                System.out.println("¿Qué desea hacer?");
                System.out.println("1 - Añadir nuevo empleado");
                System.out.println("2 - Actualizar empleado");
                System.out.println("3 - Eliminar empleado");
                System.out.println("0 - Salir");
                System.out.println("---------------------------");

                option = in.nextInt();

                switch (option) {
                    case ADD_EMPLOYEE:
                        employee = newEmployee();
                        cont.addEmployee(employee);
                        break;
                    case UPDATE_EMPLOYEE: 
                        System.out.println("ID empleado: ");
                        empNo = in.nextInt();
                        employee = newEmployee();
                        cont.updateEmployee(empNo, employee);
                        break;
                    case DELETE_EMPLOYEE:
                        System.out.println("ID empleado: ");
                        empNo = in.nextInt();
                        cont.deleteEmployee(empNo);
                        break;

                }
            }
            System.out.println("Hasta pronto!");
    }

    public static Employee newEmployee() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());

        Scanner in = new Scanner(System.in);

        System.out.println("ID: ");
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

}
