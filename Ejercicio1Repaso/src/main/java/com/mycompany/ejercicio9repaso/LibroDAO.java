/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio9repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class LibroDAO {
    
    public static void listarLibros(){
    
        String sql ="SELECT * FROM libro";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()) {
            
            System.out.println("Libros (ID, título, autor, año publicación, categoría, cantidad disponible):");
            while (rs.next()) {     
               int id =rs.getInt("id");
               String titulo =rs.getString("titulo");
               String autor =rs.getString("autor");
               int año =rs.getInt("anio_publicacion");
               String categoria=rs.getString("categoria");
               int cantidadDisponible=rs.getInt("cantidad_disponible");
               
                System.out.println(id+","+titulo+","+autor+","+año+","+categoria+","+cantidadDisponible);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
