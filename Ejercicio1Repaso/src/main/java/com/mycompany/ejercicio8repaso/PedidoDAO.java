/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio8repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tedax
 */
public class PedidoDAO {
    public static void listarPedidos(){
         String query = "SELECT p.id,p.fecha,p.cliente,SUM(dp.subtotal) AS total FROM pedido p "
                 + "JOIN detalle_pedido dp ON dp.id_pedido = p.id "
                 + "GROUP BY p.id, p.fecha, p.cliente";
         

        try (Connection conn = ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(query);ResultSet rs =stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String fecha = rs.getString("fecha");
                String cliente = rs.getString("cliente");
                double total = rs.getDouble("total");
                
                
                System.out.println(id+","+fecha+","+cliente+","+total);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }

    }
    public static void listarDetallePedido(int id){
         String query = "SELECT p.id, p.fecha, p.cliente, SUM(dp.subtotal) AS total, pp.nombre, dp.cantidad, dp.subtotal FROM pedido p "
                 + "JOIN detalle_pedido dp ON dp.id_pedido = p.id "
                 + "JOIN producto pp ON pp.id =dp.id_producto "
                 + "WHERE p.id = ? "
                 + "GROUP BY p.id, p.fecha, p.cliente, pp.nombre, dp.cantidad, dp.subtotal";
         

        try (Connection conn = ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            try(ResultSet rs =stmt.executeQuery())
            {
            while (rs.next()) {
                id = rs.getInt("id");
                String fecha = rs.getString("fecha");
                String cliente = rs.getString("cliente");
                double total = rs.getDouble("total");
                
                System.out.println("Detalles pedido; " +id);
                System.out.println("Fecha; " +fecha);
                System.out.println("Cliente: " +cliente);
                System.out.println("Total: " +total);
                
                 System.out.println("\nProductos (nombre, cantidad, subtotal):");
                 
                do {
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double subtotal = rs.getDouble("subtotal");

                    System.out.println(nombre + ", " + cantidad + ", " + subtotal);
                } while (rs.next()); // Continuamos hasta el siguiente producto
                
                
            }
            }
            
            
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }

    }
    
        public boolean registrarProductoEnPedido(int idProducto, int cantidad, String fecha, String cliente) throws SQLException {
        // Esta función maneja toda la lógica transaccional
        try (Connection conn = ConexionDB.connection()) {
            // Desactivar el auto-commit para manejar la transacción
            conn.setAutoCommit(false);

            // Registrar el pedido en la tabla `pedido`
            String queryPedido = "INSERT INTO pedido (fecha, cliente) VALUES (?, ?)";
            try (PreparedStatement psPedido = conn.prepareStatement(queryPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPedido.setString(1, fecha);
                psPedido.setString(2, cliente);
                psPedido.executeUpdate();

                // Obtener el ID del nuevo pedido
                int idPedido = 0;
                try (ResultSet rs = psPedido.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPedido = rs.getInt(1);
                    }
                }

                // Verificar si hay suficiente stock
                String queryStock = "SELECT stock FROM producto WHERE id = ?";
                try (PreparedStatement psStock = conn.prepareStatement(queryStock)) {
                    psStock.setInt(1, idProducto);
                    try (ResultSet rsStock = psStock.executeQuery()) {
                        if (rsStock.next()) {
                            int stockActual = rsStock.getInt("stock");

                            if (stockActual < cantidad) {
                                // Si no hay suficiente stock, deshacemos la transacción
                                conn.rollback();
                                System.out.println("No hay suficiente stock para el producto con ID: " + idProducto);
                                return false;
                            } else {
                                // Obtener el precio del producto
                                String queryPrecio = "SELECT precio FROM producto WHERE id = ?";
                                double precio = 0;
                                try (PreparedStatement psPrecio = conn.prepareStatement(queryPrecio)) {
                                    psPrecio.setInt(1, idProducto);
                                    try (ResultSet rsPrecio = psPrecio.executeQuery()) {
                                        if (rsPrecio.next()) {
                                            precio = rsPrecio.getDouble("precio");
                                        }
                                    }
                                }

                                double subtotal = precio * cantidad;

                                // Insertar en detalle_pedido
                                String queryDetalle = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
                                try (PreparedStatement psDetalle = conn.prepareStatement(queryDetalle)) {
                                    psDetalle.setInt(1, idPedido);
                                    psDetalle.setInt(2, idProducto);
                                    psDetalle.setInt(3, cantidad);
                                    psDetalle.setDouble(4, subtotal);
                                    psDetalle.executeUpdate();
                                }

                                // Actualizar el stock
                                String queryUpdateStock = "UPDATE producto SET stock = stock - ? WHERE id = ?";
                                try (PreparedStatement psUpdateStock = conn.prepareStatement(queryUpdateStock)) {
                                    psUpdateStock.setInt(1, cantidad);
                                    psUpdateStock.setInt(2, idProducto);
                                    psUpdateStock.executeUpdate();
                                }
                            }
                        }
                    }
                }

                // Si todo ha ido bien, confirmamos la transacción
                conn.commit();
                return true;

            } catch (SQLException e) {
                // Si ocurre un error en el proceso, revertimos la transacción
                conn.rollback();
                System.err.println("Error al registrar el pedido: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            // Si ocurre un error en la conexión o en la transacción, gestionamos el rollback
            System.err.println("Error en la conexión o en la transacción: " + e.getMessage());
            return false;
        }finally{
            try(Connection conn =ConexionDB.connection()){
                conn.setAutoCommit(false);
            }
         
        }
    }

}
