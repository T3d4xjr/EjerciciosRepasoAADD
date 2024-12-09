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
public class EstudianteDAO {
    
    public static void añadirEstudiante(String nombre,String email,String telefono,String direccion){
        
        String sql ="INSERT INTO estudiantes (nombre,email,telefono,direccion) VALUES (?,?,?,?)";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, direccion);
            
            int filasAfectadas =stmt.executeUpdate();
            
            if(filasAfectadas>0){
                System.out.println("Estudiante añadido");
            }else{
                System.err.println("Error al añadir");
            }
            
        } catch (SQLException e) {
        }
    
    }
    public void introduceEstudiantes() throws SQLException {
         String query = "INSERT INTO estudiantes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

         try (Connection conn = ConexionDB.connection(); 
              PreparedStatement stmt = conn.prepareStatement(query)) {

             conn.setAutoCommit(false);  // Iniciar la transacción

             try {
                 // Primer estudiante
                 stmt.setString(1, "Nombre1");
                 stmt.setString(2, "n@gmail.com");
                 stmt.setString(3, "666");
                 stmt.setString(4, "c/");
                 stmt.addBatch();

                 // Segundo estudiante
                 stmt.setString(1, "Nombre1");
                 stmt.setString(2, "hola@gmail.com");
                 stmt.setString(3, "666");
                 stmt.setString(4, "c/");
                 stmt.addBatch();

                 // Tercer estudiante
                 stmt.setString(1, "Nombre1");
                 stmt.setString(2, "n@gmail.com");
                 stmt.setString(3, "666");
                 stmt.setString(4, "c/");
                 stmt.addBatch();

                 // Ejecutar el batch
                 int[] filasAfectadas = stmt.executeBatch();

                 // Confirmar la transacción
                 conn.commit();
                 System.out.println("Estudiantes introducidos correctamente.");
             } catch (SQLException e) {
                 // Si ocurre un error, revertimos los cambios
                 conn.rollback();
                 System.err.println("Error al insertar estudiantes: " + e.getMessage());
             }

         } catch (SQLException e) {
             // Manejo de error en la conexión o en la preparación del statement
             System.err.println("Error de conexión o SQL: " + e.getMessage());
         } finally {
             try {
                 // Asegurarse de restaurar el auto-commit
                 Connection conn = ConexionDB.connection(); // Recuperamos la conexión nuevamente
                 conn.setAutoCommit(true);  // Restablecer auto-commit
             } catch (SQLException e) {
                 System.err.println("Error al restaurar el auto-commit: " + e.getMessage());
             }
         }
     }

}
