/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio7repaso;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Menu {
    
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int opcion;
        
        EstudianteDAO estudianteDAO =new EstudianteDAO();
        AsignaturaDAO asignaturaDAO =new AsignaturaDAO();
        MatriculaDAO matriculaDAO =new MatriculaDAO();
        
        do {
            System.out.println("Ejercio ad_ej7");
            System.out.println("1.Añadir estudiante");
            System.out.println("2.Añadir asignatura");
            System.out.println("3.Matricular estudiante en asignatura");
            System.out.println("4.Añadir calificacion");
            System.out.println("5.Ver historial de matricula de un estudiante");
            System.out.println("6.Cancelar Matricula.");
            System.out.println("7.Salir");
            System.out.println("8.Ingresar 3 estudiantes.");
            opcion =sc.nextInt();
            switch (opcion) {
                case 1:
                    anadirEstudiante(sc);
                    break;
                case 2:
                    anadirAsignatura(sc);
                    break;
                case 3:
                    matricularEstudianteAsignatura(sc);
                    break;
                case 4:
                    añadirCalificacion(sc);
                    break;
                case 5:
                    historialMatriculaEstudiante(sc);
                    break;
                case 6:
                    cancelarMatricula(sc);
                    break;
                case 8:
                    introduce3Estudiantes(sc);
                    break;
                case 7:
                    System.out.println("Saliendo....");
                    break;
                default:
                    throw new AssertionError();
            }
        
            
        } while (opcion !=8);
    }
    
    private static void anadirEstudiante(Scanner sc){
        
        sc.nextLine();
        
        System.out.println("Dime el nombre del estudiante");
        String nombre =sc.nextLine();
        System.out.println("Dime el email del estudiante");
        String email =sc.nextLine();
        System.out.println("Dime el telefono del estudiante");
        String telefono =sc.nextLine();
        System.out.println("Dime la direccion del estudiante");
        String direccion =sc.nextLine();
        
        EstudianteDAO.añadirEstudiante(nombre, email, telefono, direccion);
        
    
    }
    private static void anadirAsignatura(Scanner sc ){
        sc.nextLine();
        
        System.out.println("Dime el nombre del la asignatura");
        String nombre =sc.nextLine();
        System.out.println("Dime el curso del la asignatura");
        String curso =sc.nextLine();
        System.out.println("Dime las horas del la asignatura");
        String horas =sc.nextLine();
        
        AsignaturaDAO.añadirAsignatura(nombre, curso, horas);
        
    }
    private static void matricularEstudianteAsignatura(Scanner sc){
     
        sc.nextLine();
        
        System.out.println("Dime el email del estudiante");
        
        String emailEstudiante =sc.nextLine();
        
        System.out.println("Dime el nombre de la asignatura");
        
        String nombreAsignatura =sc.nextLine();
        
        System.out.println("Dime el año a matricular");
        
        int año =sc.nextInt();
        
        MatriculaDAO.matriculaEstudianteAsignatura(emailEstudiante, nombreAsignatura, año);
    }
    private static void añadirCalificacion(Scanner sc){
        sc.nextLine();
        
        System.out.println("Dime el email del estudiante");
        
        String emailEstudiante =sc.nextLine();
        
        System.out.println("Dime el nombre de la asignatura");
        
        String nombreAsignatura =sc.nextLine();
        
        System.out.println("Dime el año a matricular");
        
        int año =sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Dime una calificacion");
        
        double calificacion =sc.nextDouble();
        
        sc.nextLine();
        
        MatriculaDAO.añadirCalificacion(emailEstudiante, nombreAsignatura, año, calificacion);
    }
    
    private static void historialMatriculaEstudiante(Scanner sc){
        sc.nextLine();
        
        System.out.println("Dime el email del estudiante");
        
        String emailEstudiante =sc.nextLine();
        
        MatriculaDAO.historialMatriculaEstudiante(emailEstudiante);
    
    }
    
    private static void cancelarMatricula(Scanner sc){
        sc.nextLine();
        
        System.out.println("Dime el email del estudiante");
        
        String emailEstudiante =sc.nextLine();
        
        System.out.println("Dime el nombre de la asignatura");
        
        String nombreAsignatura =sc.nextLine();
        
        System.out.println("Dime el año a matricular");
        
        int año =sc.nextInt();
        
        MatriculaDAO.cancelarMatricula(emailEstudiante, nombreAsignatura, año);
    }
    private static void introduce3Estudiantes(Scanner sc ){
        // Aquí solo se llama al método introduceEstudiantes() directamente
        System.out.println("Vamos a introducir 3 estudiantes.");
        introduce3Estudiantes(sc);// Llamada al método que ya has escrito
    }
}
