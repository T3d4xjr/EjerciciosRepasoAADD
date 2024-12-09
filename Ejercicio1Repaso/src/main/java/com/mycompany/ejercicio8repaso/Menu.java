/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio8repaso;

/**
 *
 * @author tedax
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;

        ProductoDAO productoDAO = new ProductoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        do {
            System.out.println("Gestión de productos y pedidos");
            System.out.println("1. Añadir producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Añadir pedido");
            System.out.println("4. Listar pedidos");
            System.out.println("5. Listar detalles de un pedido");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    añadirProducto(sc);
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    añadirPedido(sc, pedidoDAO);
                    break;
                case 4:
                    listarPedidos();
                    break;
                case 5:
                    listarDetallesPedido(sc);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        sc.close();
    }

    private static void añadirProducto(Scanner sc) {
        sc.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el stock del producto: ");
        int stock = sc.nextInt();
        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        ProductoDAO.añadirProducto(nombre, stock, precio);
    }

    private static void listarProductos() {
        System.out.println("Id,nombre,stock,precio");
        ProductoDAO.listarProductos();
    }
    private static void añadirPedido(Scanner sc, PedidoDAO pedidoDAO) throws SQLException {
        sc.nextLine(); // Limpiar el buffer
        System.out.println("Introduce la fecha del pedido (YYYY-MM-DD):");
        String fecha = sc.nextLine();

        System.out.println("Introduce el nombre del cliente:");
        String cliente = sc.nextLine();

        // Procesar los productos para el pedido
        while (true) {
            System.out.print("ID del producto (o 0 para terminar): ");
            int idProducto = sc.nextInt();
            if (idProducto == 0) {
                break;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();

            // Llamar al método del DAO para registrar el producto en el pedido
            boolean exito = pedidoDAO.registrarProductoEnPedido(idProducto, cantidad, fecha, cliente);
            if (exito) {
                System.out.println("Producto añadido al pedido con éxito.");
            } else {
                System.out.println("Error al añadir el producto al pedido. Verifica los datos o el stock.");
            }
        }
    }
        

    private static void listarPedidos() {
        System.out.println("Id,fecha,cliente,total");
       PedidoDAO.listarPedidos();
    }

    private static void listarDetallesPedido(Scanner sc) {
        sc.nextLine();
        
        System.out.println("Dime el id ");
        
        int id =sc.nextInt();
        
        PedidoDAO.listarDetallePedido(id);
    }
}
