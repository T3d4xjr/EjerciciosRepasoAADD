/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5repaso;

import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Menu {
    EmpleadoDAO empleadoDAO =new EmpleadoDAO();
    

    public static void main(String[] args) {
      Scanner sc =new Scanner(System.in);
    int opcion;
    
        do {       
            System.out.println("Consultas ad_ej1");
            System.out.println("1.Listar empleados ");
            System.out.println("2.Añadir un empleado ");
            System.out.println("3.Actualizar empleado");
            System.out.println("4.Eliminar un empleado");
            System.out.println("5.Salir");
            opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                   listarEmpleado();
                    break;
                case 2:
                    anadirEmpleado(sc);
                    break;
                case 3:
                    modificarEmpleado(sc);
                    break;
                case 4:
                    eliminarEmpleado(sc);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                
                default:
                    throw new AssertionError();
            }
            
        } while (opcion!=5);  
    }
    private static void listarEmpleado(){
        System.out.println("Id,Nombre,Puesto,Salario,Fecha_ingreso");
    EmpleadoDAO.listarEmpleados();
    }
    private  static  void anadirEmpleado(Scanner sc){
        sc.nextLine();
        
        System.out.println("Introduce un nombre");
        String nombre =sc.nextLine();
        System.out.println("Introduce un puesto");
        String puesto =sc.nextLine();
        System.out.println("Introduce un salario");
        double salario =sc.nextDouble();
        sc.nextLine();
        System.out.println("Introduce un fecha en (YYYY-MM-DD)");
        String fecha_ingreso =sc.nextLine();
        
        EmpleadoDAO.anadirEmpleado(nombre, puesto, salario, fecha_ingreso);
        
    
    }
    
    private static void modificarEmpleado(Scanner sc){
        
        sc.nextLine();
        System.out.println("Introduce el id a modificar");
        int id =sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Introduce un nombre");
        String nombre =sc.nextLine();
        System.out.println("Introduce un puesto");
        String puesto =sc.nextLine();
        System.out.println("Introduce un salario");
        double salario =sc.nextDouble();
        sc.nextLine();
        System.out.println("Introduce un fecha en (YYYY-MM-DD)");
        String fecha_ingreso =sc.nextLine();
        
        EmpleadoDAO.modificarEmpleado(id, nombre, puesto, salario, fecha_ingreso);
    
    }
    private static void eliminarEmpleado(Scanner sc){
        sc.nextLine();
        System.out.println("Introduce el id a eliminar");
        int id =sc.nextInt();
        
        EmpleadoDAO.eliminarEmpleado(id);
        
    }
}
