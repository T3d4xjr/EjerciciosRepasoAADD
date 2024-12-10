/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio10repaso;

/**
 *
 * @author tedax
 */
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Empleados y Proyectos ---");
            System.out.println("1. Añadir empleado");
            System.out.println("2. Modificar empleado");
            System.out.println("3. Despedir empleado");
            System.out.println("4. Listar empleados activos");
            System.out.println("5. Listar empleados despedidos");
            System.out.println("6. Añadir proyecto");
            System.out.println("7. Añadir un proyecto con empleados");
            System.out.println("8. Modificar un proyecto");
            System.out.println("9. Añadir empleado a proyecto");
            System.out.println("10. Añadir varios empleados a un proyecto");
            System.out.println("11. Eliminar un empleado de un proyecto");
            System.out.println("12. Listar todos los proyectos futuros");
            System.out.println("13. Listar todos los proyectos pasados");
            System.out.println("14. Listar todos los proyectos activos");
            System.out.println("15. Listar los detalles de un proyecto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    añadirEmpleado(scanner);
                    break;
                case 2:
                    modificarEmpleado(scanner);
                    break;
                case 3:
                    despedirEmpleado(scanner);
                    break;
                case 4:
                    listarEmpleadosActivos();
                    break;
                case 5:
                    listarEmpleadosDespedidos();
                    break;
                case 6:
                    añadirProyecto(scanner);
                    break;
                case 7:
                    añadirProyectoConEmpleados(scanner);
                    break;
                case 8:
                    modificarProyecto(scanner);
                    break;
                case 9:
                    añadirEmpleadoAProyecto(scanner);
                    break;
                case 10:
                    añadirVariosEmpleadosAProyecto(scanner);
                    break;
                case 11:
                    eliminarEmpleadoDeProyecto(scanner);
                    break;
                case 12:
                    listarProyectosFuturos();
                    break;
                case 13:
                    listarProyectosPasados();
                    break;
                case 14:
                    listarProyectosActivos();
                    break;
                case 15:
                    listarDetallesProyecto(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
        private static void añadirEmpleado(Scanner scanner) {
        System.out.print("Ingrese nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese DNI del empleado: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese sueldo: ");
        double sueldo = scanner.nextDouble();
        System.out.print("Ingrese fecha de contratación (YYYY-MM-DD): ");
        scanner.nextLine(); // Limpiar el buffer
        String fechaContratacion = scanner.nextLine();

       EmpleadoDAO.añadirEmpleado(nombre, dni, departamento, sueldo, fechaContratacion);
    }

    private static void modificarEmpleado(Scanner scanner) {
        System.out.print("Ingrese ID del empleado a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nuevo departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese nuevo sueldo: ");
        double sueldo = scanner.nextDouble();

        EmpleadoDAO.modificarEmpleado(id, nombre, departamento, sueldo);
    }

    private static void despedirEmpleado(Scanner scanner) {
        System.out.print("Ingrese ID del empleado a despedir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese fecha de despido (YYYY-MM-DD): ");
        String fechaDespido = scanner.nextLine();

       EmpleadoDAO.despidirEmpleado(id, fechaDespido);
    }

    private static void listarEmpleadosActivos() {
      EmpleadoDAO.listarEmpleadosActivos();
    }

    private static void listarEmpleadosDespedidos() {
      EmpleadoDAO.listarEmpleadosDespedidos();
    }

    private static void añadirProyecto(Scanner scanner) {
        System.out.print("Ingrese nombre del proyecto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese fecha de finalización (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        ProyectoDAO.añadirProyecto(nombre, fechaInicio, fechaFin);
    }

    private static void añadirProyectoConEmpleados(Scanner scanner) throws SQLException {
        System.out.print("Ingrese nombre del proyecto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese fecha de finalización (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();
        System.out.print("Ingrese IDs de empleados separados por espacios: ");
        String empleados = scanner.nextLine();

        Empleado_proyectoDAO.añadirProyectoConEmpleados(nombre, fechaInicio, fechaFin, empleados);
    }

    private static void modificarProyecto(Scanner scanner) {
        System.out.print("Ingrese ID del proyecto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nueva fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese nueva fecha de finalización (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        ProyectoDAO.modificarProyecto(id, nombre, fechaInicio, fechaFin);
    }

    private static void añadirEmpleadoAProyecto(Scanner scanner) {
        System.out.print("Ingrese ID del proyecto: ");
        int idProyecto = scanner.nextInt();
        System.out.print("Ingrese ID del empleado: ");
        int idEmpleado = scanner.nextInt();

        Empleado_proyectoDAO.añadirEmpleadoProyecto(idProyecto, idEmpleado);
    }

    private static void añadirVariosEmpleadosAProyecto(Scanner scanner) {
        System.out.print("Ingrese ID del proyecto: ");
        int idProyecto = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese IDs de empleados separados por espacios: ");
        String empleados = scanner.nextLine();

        Empleado_proyectoDAO.añadirEmpleadosAProyecto(idProyecto, empleados);
    }

    private static void eliminarEmpleadoDeProyecto(Scanner scanner) {
        System.out.print("Ingrese ID del proyecto: ");
        int idProyecto = scanner.nextInt();
        System.out.print("Ingrese ID del empleado: ");
        int idEmpleado = scanner.nextInt();

       Empleado_proyectoDAO.EliminarEmpleadoProyecto(idProyecto, idEmpleado);
    }

    private static void listarProyectosFuturos() {
       ProyectoDAO.listarProyectosFuturos();
    }

    private static void listarProyectosPasados() {
     ProyectoDAO.listarProyectosPasados();
    }

    private static void listarProyectosActivos() {
      ProyectoDAO.listarProyectosActivos();
    }

    private static void listarDetallesProyecto(Scanner scanner) {
        System.out.print("Ingrese ID del proyecto: ");
        int id = scanner.nextInt();
        System.out.println("Detalles del proyecto con ID " + id + ":");
        
        ProyectoDAO.listarDetallesProyecto(id);
        
    }
}
