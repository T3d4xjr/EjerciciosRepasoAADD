/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio9repaso;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Menu {
    
    public static void main(String[] args) throws SQLException {
        Scanner sc =new  Scanner(System.in);
        
        int opcion;
        
        do {     
            System.out.println("Ejercicio 9");
            System.out.println("1.Listar libros");
            System.out.println("2.Registrar prestamo");
            System.out.println("3.Devolver prestamo");
            System.out.println("4.Listar prestamos pendientes");
            System.out.println("5.Listar prestamos");
            System.out.println("6.Salir");
            opcion =sc.nextInt();
            
            switch (opcion) {
                case 1:
                 listarLibros();
                    break;
                case 2:
                    registrarPrestamo(sc);
                    break;
                case 3:
                    devolverPrestamo(sc);
                    break;
                case 4:
                    listarPrestamosPendientes();
                    break;
                case 5:
                    listarPrestamos();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (opcion !=6);
    }
    
    private static void listarLibros(){
    LibroDAO.listarLibros();
    }
    private static void registrarPrestamo(Scanner sc){
        sc.nextLine();
        
        System.out.println("Ingrese una fecha en (YYYY-MM-DD):");
        String fecha =sc.nextLine();
        System.out.println("Ingrese el nombre del cliente lector");
        String cliente =sc.nextLine();
        System.out.println("Introduzca el id del libro");
        int id_libro =sc.nextInt();
        
        PrestamoDAO.registrarPrestamo(fecha, cliente, id_libro);
        
    }
    private static void devolverPrestamo(Scanner sc) throws SQLException{
        System.out.println("Dime el id del prestamo a devover");
        int id_prestamo=sc.nextInt();
        
        PrestamoDAO.devolverPrestamo(id_prestamo);
    
    }
    private static void listarPrestamosPendientes(){
    PrestamoDAO.listarPrestamosPendientes();
    }
    private static void listarPrestamos(){
    PrestamoDAO.listarPrestamos();
    }
    
    
}
