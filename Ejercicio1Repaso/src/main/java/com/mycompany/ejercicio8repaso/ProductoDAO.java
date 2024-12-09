/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio8repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class ProductoDAO {

     public static  void añadirProducto(String nombre, int stock, double precio) {
        String query = "INSERT INTO producto (nombre, stock, precio) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionDB.connection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, stock);
            stmt.setDouble(3, precio);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto añadido correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al añadir el producto: " + e.getMessage());
        }
    }
     public static void listarProductos(){
         String query = "SELECT * FROM producto";

        try (Connection conn = ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(query);ResultSet rs =stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");
                
                
                System.out.println(id+","+nombre+","+stock+","+precio);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }

    }
    
     
    
}
