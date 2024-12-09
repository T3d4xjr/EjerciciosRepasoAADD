/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio7repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class AsignaturaDAO {
    
    public static void añadirAsignatura(String nombre,String curso,String horas){
        
        String sql ="INSERT INTO asignaturas (nombre,curso,horas) VALUES (?,?,?)";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, curso);
            stmt.setString(3, horas);
            
            int filasAfectadas =stmt.executeUpdate();
            
            if(filasAfectadas>0){
                System.out.println("Asignatura añadida");
            }else{
                System.err.println("Error al añadir");
            }
            
        } catch (SQLException e) {
        }
    
    }
    public void introduceEstudiante() throws SQLException{
        
        String query = "INSERT INTO estudiantes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";
        Connection conn = ConexionDB.connection();
       try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
           conn.setAutoCommit(false);
           

            stmt.setString(1,"Nombre1");
            stmt.setString(2,"n@gmail.com");
            stmt.setString(3, "666");
            stmt.setString(4,"c/");
            stmt.addBatch();
            
            stmt.setString(1,"Nombre1");
            stmt.setString(2,"hola@gmail.com");
            stmt.setString(3, "666");
            stmt.setString(4,"c/");
            stmt.addBatch();
            
            stmt.setString(1,"Nombre1");
            stmt.setString(2,"n@gmail.com");
            stmt.setString(3, "666");
            stmt.setString(4,"c/");
            stmt.addBatch();
            
            int[] filasAfectadas =stmt.executeBatch();

            conn.commit();
        } catch (SQLException e) {
           conn.rollback();
        }finally{
           conn.setAutoCommit(true);
       }
    }
}
