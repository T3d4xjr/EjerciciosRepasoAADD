/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1repaso;

import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Menu {
    ClienteDAO clienteDAO =new ClienteDAO();
    
    public static void main(String[] args) {
    Scanner sc =new Scanner(System.in);
    int opcion;
    
        do {       
            System.out.println("Consultas ad_ej1");
            System.out.println("1.Listar todos los clientes");
            System.out.println("2.Añadir un cliente");
            System.out.println("3.Buscar un cliente por fragmento de email");
            System.out.println("4.Modificar datos por un email");
            System.out.println("5.Borrar un cliente por email");
            System.out.println("6.Ranking clientes");
            System.out.println("7.Añadir pedido de forma  complicada");
            System.out.println("8.Modificar pedido por id");
            System.out.println("9.Eliminar pedido por id");
            System.out.println("10.Iniciar Sesion");
            System.out.println("11.Cambiar contraseña");
            System.out.println("12.Salir");
            opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    listarCliente();
                    break;
                case 2:
                    anadirCliente(sc);
                    break;
                case 3:
                    buscarFragmentoEmailCliente(sc);
                    break;
                case 4:
                    modificarClientePorEmail(sc);
                    break;
                case 5:
                    eliminarClientePorEmail(sc);
                    break;
                case 6:
                    rankingClientes();
                    break;
                case 7:
                    anadirPedido(sc);
                    break;
                case 8:
                    modificarPedidoId(sc);
                    break;
                case 9:
                    borrarPedidoId(sc);
                    break;
                 case 10:
                     iniciarSesion(sc);
                    break;
                case 11:
                     modificarContraseña(sc);
                    break;

                case 12:
                    System.out.println("Saliendo...");
                    break;
                
                default:
                    throw new AssertionError();
            }
            
        } while (opcion!=12);
        
    }
    private static void listarCliente(){
    System.out.println("Id,Nombre,Email,Ciudad,Telefono");
    ClienteDAO.listarCliente();
    }
    private static void anadirCliente(Scanner sc){
        sc.nextLine();
        System.out.println("Introduce un nombre");
        String nombre =sc.nextLine();
        System.out.println("Introduce un email");
        String email =sc.nextLine();
        System.out.println("Introduce una ciudad");
        String ciudad =sc.nextLine();
        System.out.println("Introduce un telefono");
        String telefono =sc.nextLine();
        
        if (ClienteDAO.anadirCliente(nombre, email, ciudad, telefono)) {
            System.out.println("Cliente añadido");
            
        } else {
            System.err.println("Error al añadir");
        }
        
    
    }
    private static void buscarFragmentoEmailCliente(Scanner sc){
        sc.nextLine();
        System.out.println("Di el fragmento a buscar");
        String fragmento =sc.nextLine();
        
        ClienteDAO.buscarFragmentoEmail(fragmento);
    
    }
    private static void modificarClientePorEmail(Scanner sc){
        sc.nextLine();
        System.out.println("Indica el email para modificar los datos de ese cliente");
        
        String email =sc.nextLine();
        
        System.out.println("Nuevo nombre:");
        String nuevoNombre =sc.nextLine();
        
        System.out.println("Nueva ciudad");
        String nuevaCiudad =sc.nextLine();
        
        System.out.println("Nuevo telefono");
        String nuevoTelefono =sc.nextLine();
        
        if(ClienteDAO.modificarporEmail(email, nuevoNombre, nuevaCiudad, nuevoTelefono)){
            System.out.println("Cliente modificado");
        }else{
            System.err.println("Error al modificar");
        }
    
    }
    private static void eliminarClientePorEmail(Scanner sc){
        sc.nextLine();
        System.out.println("Dinos el email para eliminar el cliente");
        String email =sc.nextLine();
        
        if(ClienteDAO.eliminarPorEmail(email)){
            System.out.println("Eliminado con exito");
        }else{
            System.err.println("Error");
        }
    
    }
    private static void rankingClientes(){
    System.out.println("Ranking clientes:nombre,email,fecha,gastoTotal");
        ClienteDAO.rankingClientes();
    }
    
    private static void anadirPedido(Scanner sc){
        
        sc.nextLine();
        System.out.println("Dime el email");
        
        String email=sc.nextLine();
        
       int clienteid= ClienteDAO.verificarEmail(email);
       
       if(clienteid == -1){
           System.out.println("Añadidomos un nuevo cliente ya que  no existe ese email");
           
           System.out.println("Nuevo nombre");
           String nombre =sc.nextLine();
           System.out.println("Nuevo ciudad");
           String ciudad =sc.nextLine();
           System.out.println("Nuevo telefono");
           String telefono =sc.nextLine();
           
           ClienteDAO.agregarCliente(nombre, email, ciudad, telefono);
           
           clienteid =ClienteDAO.verificarEmail(email);
           
       }
        System.out.println("Introduce fecha en (YYYY-MM-DD)");
        String fecha =sc.nextLine();
        System.out.println("Introduce precio_total");
        float precio_total=sc.nextFloat();
        
        ClienteDAO.insertarPedido(clienteid, fecha, precio_total);
    }
    
    private static void modificarPedidoId(Scanner sc){
    sc.nextLine();
    
        System.out.println("Dime el id a modificar");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.println("Dine la nueva fecha en (YYYY-MM-DD)");
        String fecha =sc.nextLine();
        System.out.println("Dime el nuevo precio_total");
        float precio_total =sc.nextFloat();
        
        ClienteDAO.modificarPedidoId(id, fecha, precio_total);
    }
    private static void borrarPedidoId(Scanner sc){
        System.out.println("Dime el id del pedido a eliminar");
        int id =sc.nextInt();
        
        ClienteDAO.borrarPedidoId(id);
    }
    private static void iniciarSesion(Scanner sc){
        sc.nextLine();
        
        System.out.println("Introduce el email");
        String email =sc.nextLine();
        
        System.out.println("Introduce la contraseña.");
        String contraseña=sc.nextLine();
        
        
        ClienteDAO.iniciarSesion(email, contraseña);
        
    }
    private static void modificarContraseña(Scanner sc){
        sc.nextLine();
        System.out.println("Indica el email al que le quieres cambiar la contraseña");
        
        String email=sc.nextLine();
        
        System.out.println("Nueva Contraseña");
        
        String contraseña=sc.nextLine();
        
        ClienteDAO.modificarContraseña(email, contraseña);
    
    }
}
