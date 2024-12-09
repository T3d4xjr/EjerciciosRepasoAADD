/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio6repaso;

import com.mycompany.ejercicio5repaso.*;
import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Menu {
    
    

    public static void main(String[] args) {
      Scanner sc =new Scanner(System.in);
    int opcion;
        PeliculaDAO peliculaDAO =new PeliculaDAO();
        do {       
            System.out.println("Consultas ad_ej1");
            System.out.println("1.Listar peliculas ");
            System.out.println("2.Buscar pelicula por fragmento de titulo");
            System.out.println("3.Buscar por año");
            System.out.println("4.Añadir una pelicula");
            System.out.println("5.Actualizar una pelicula");
            System.out.println("6.Eliminar un pelicula");
            System.out.println("7.Salir");
            opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    listarPeliculas();
                    break;
                case 2:
                    listaPorFragmentoTitulo(sc);
                    break;
                case 3:
                    listaPorAgno(sc);
                    break;
                case 4:
                    anadirPelicula(sc);
                    break;
                case 5:
                    modificarEmpleado(sc);
                    break;
                case 6:
                    eliminarEmpleado(sc);
                    break;
                
                case 7:
                    System.out.println("Saliendo...");
                    break;
                
                default:
                    throw new AssertionError();
            }
            
        } while (opcion!=7);  
    }
    private static void listarPeliculas(){
    PeliculaDAO.listarPeliculas();
    }
    private static void listaPorFragmentoTitulo(Scanner sc){
        sc.nextLine();
        
        System.out.println("Introduzca el fragmento de titulo");
        
        String fragmento =sc.nextLine();
        
        PeliculaDAO.listarPorFragmentoTitulo(fragmento);
    }
    private static void listaPorAgno(Scanner sc){
        sc.nextLine();
        
        System.out.println("Introduzca el año");
        
        String agno =sc.nextLine();
        
        PeliculaDAO.listarPorAño(agno);
    }
    private  static  void anadirPelicula(Scanner sc){
        sc.nextLine();
        
        System.out.println("Introduce un titulo");
        String titulo =sc.nextLine();
        System.out.println("Introduce un director");
        String director =sc.nextLine();
        System.out.println("Introduce una fecha");
        String año =sc.nextLine();
        System.out.println("Introduce un genero");
        String genero =sc.nextLine();
        
        PeliculaDAO.anadirPelicula(titulo, director, año, genero);
        
       
        
    
    }
    
    private static void modificarEmpleado(Scanner sc){
        
        sc.nextLine();
        System.out.println("Introduce el id a modificar");
        int id =sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Introduce un titulo");
        String titulo =sc.nextLine();
        System.out.println("Introduce un director");
        String director =sc.nextLine();
        System.out.println("Introduce una fecha");
        String año =sc.nextLine();
        System.out.println("Introduce un genero");
        String genero =sc.nextLine();
        
        PeliculaDAO.modificarPelicula(id, titulo, director, año, genero);
    
    }
    private static void eliminarEmpleado(Scanner sc){
        sc.nextLine();
        System.out.println("Introduce el id a eliminar");
        int id =sc.nextInt();
        
        PeliculaDAO.eliminarPelicula(id);
        
    }
}
