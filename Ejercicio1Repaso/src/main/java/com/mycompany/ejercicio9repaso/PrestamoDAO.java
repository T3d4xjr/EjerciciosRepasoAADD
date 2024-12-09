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
public class PrestamoDAO {
    
    public static void registrarPrestamo(String fecha,String lector,int id_libro){
        
        String sql="INSERT  INTO prestamo (id_libro,lector,fecha_prestamo,estado) VALUES (?,?,?,?) ";
        
        String sql2="UPDATE libro SET cantidad_disponible = cantidad_disponible - 1 WHERE id = ?";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql2)) {
            
            stmt.setInt(1, id_libro);
            
            int row =stmt.executeUpdate();
            
            if (row > 0) {
                System.out.println("Libro actualizado");
            }
            try(PreparedStatement stmt2 =conn.prepareStatement(sql)) {
                
                stmt2.setInt(1, id_libro);
                stmt2.setString(2, lector);
                stmt2.setString(3, fecha);
                stmt2.setString(4, "Pendiente");
                
                int row2 =stmt2.executeUpdate();
            
            if (row2 > 0) {
                System.out.println("Prestamo añadido");
            }
                
            } catch (SQLException e) {
            }
            
        } catch (SQLException e) {
        }
    
    }
    public static void devolverPrestamo(int id_prestamo) throws SQLException {
    String sql = "SELECT id_libro FROM prestamo WHERE id = ?";
    String sql2 = "UPDATE prestamo SET estado = 'Devuelto' WHERE id = ?";
    String sql3 = "UPDATE libro SET cantidad_disponible = cantidad_disponible + 1 WHERE id = ?";

    try (Connection conn = ConexionDB.connection()) {
        conn.setAutoCommit(false);

        int idLibro = -1;


        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, id_prestamo);
            try (ResultSet rs = stmt1.executeQuery()) {
                if (rs.next()) {
                    idLibro = rs.getInt("id_libro");
                } else {
                    System.out.println("Préstamo no encontrado.");
                    conn.rollback();
                    return;
                }
            }
        }

        
        try (PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
            stmt2.setInt(1, id_prestamo);
            stmt2.executeUpdate();
        }


        try (PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
            stmt3.setInt(1, idLibro);
            stmt3.executeUpdate();
        }

        
        conn.commit();
        System.out.println("Préstamo devuelto con éxito.");

    } catch (SQLException e) {
        System.err.println("Error durante la operación. Transacción revertida: " + e.getMessage());

    }
}

   

    
    
    public static void listarPrestamosPendientes(){
    
        String sql = "SELECT p.id, p.fecha_prestamo,l.titulo, l.autor, p.lector, p.estado "
           + "FROM prestamo p "
           + "JOIN libro l ON l.id = p.id_libro "
                + "WHERE p.estado ='pendiente'";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()) {
            
            System.out.println("Préstamos (ID, fecha, título libro, autor, cliente, estado):");
            while (rs.next()) {     
               int id =rs.getInt("id");
               String fecha =rs.getString("fecha_prestamo");
               String titulo =rs.getString("titulo");
               String autor =rs.getString("autor");
               String cliente =rs.getString("lector");
               String estado =rs.getString("estado");
               
                System.out.println(id+","+fecha+","+titulo+","+autor+","+cliente+","+estado);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void listarPrestamos(){
    
        String sql = "SELECT p.id, p.fecha_prestamo,l.titulo, l.autor, p.lector, p.estado "
           + "FROM prestamo p "
           + "JOIN libro l ON l.id = p.id_libro";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()) {
            
            System.out.println("Préstamos (ID, fecha, título libro, autor, cliente, estado):");
            while (rs.next()) {     
               int id =rs.getInt("id");
               String fecha =rs.getString("fecha_prestamo");
               String titulo =rs.getString("titulo");
               String autor =rs.getString("autor");
               String cliente =rs.getString("lector");
               String estado =rs.getString("estado");
               
                System.out.println(id+","+fecha+","+titulo+","+autor+","+cliente+","+estado);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
