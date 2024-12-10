/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio10repaso;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class ProyectoDAO {
    
    public static void añadirProyecto(String nombre ,String fecha_inicio,String fecha_fin){
        String sql = "INSERT INTO proyecto (nombre,fecha_inicio,fecha_fin) VALUES (?,?,?)";
        
        try {
            PreparedStatement stmt =Conexion.getPreparedStatementReturnKeys(sql);
            
            Date fechaInicio =Date.valueOf(fecha_inicio);
            Date fechaFin =Date.valueOf(fecha_fin);
            
            if (fechaFin.before(fechaInicio)) {
                
                System.out.println("La fecha finalizacion debe ser posterior a la fecha de inicio.");
            }
            
            stmt.setString(1, nombre);
            stmt.setDate(2, fechaInicio);
            stmt.setDate(3, fechaFin);
            
            int row =stmt.executeUpdate();
            
            if(row == 0){
                System.err.println("No se puedo modificar el proyecto");
            }else{
                ResultSet rs =stmt.getGeneratedKeys();
                
                if (rs.next()) {
                    int id =rs.getInt(1);
                    System.out.println("Este es el id del proyecto insertado:" +id);
                }
            }
            
        } catch (SQLException e) {
        }
       
    }
    
    public static void modificarProyecto(int id_proyecto ,String nombre ,String fecha_inicio,String fecha_fin){
        
        String sql ="UPDATE proyecto SET nombre = ?,fecha_inicio = ?,fecha_fin = ? WHERE id = ?";
        
        try {
            PreparedStatement stmt =Conexion.getPreparedStatement(sql);
            
            stmt.setString(1, nombre);
            stmt.setString(2, fecha_inicio);
            stmt.setString(3, fecha_fin);
            stmt.setInt(4, id_proyecto);
            
            int row =stmt.executeUpdate();
            
            if(row == 0){
                System.err.println("No se puedo modificar el proyecto");
            }else{
                System.out.println("Proyecto modificado correctamente");
            }
            
        } catch (SQLException e) {
        }
    }
    
    public static void listarProyectosFuturos(){
     String sql ="SELECT id,nombre,fecha_inicio,fecha_fin FROM proyecto WHERE CURRENT_DATE < fecha_inicio";
        
        try {
             PreparedStatement stmt =Conexion.getPreparedStatement(sql);ResultSet rs =stmt.executeQuery();
             System.out.println("Lista de proyectos futuros:");
             while (rs.next()) {     
                 int id =rs.getInt("id");
                 String nombre =rs.getString("nombre");
                 String fecha_inicio =rs.getString("fecha_inicio");
                 String fecha_fin=rs.getString("fecha_fin");
                 
                 System.out.println(id+","+nombre+","+fecha_inicio+","+fecha_fin);
                 
                
            }
             
            
            
        } catch (SQLException e) {
        }
    }
    public static void listarProyectosPasados(){
     String sql ="SELECT id,nombre,fecha_inicio,fecha_fin FROM proyecto WHERE CURRENT_DATE > fecha_fin";
        
        try {
             PreparedStatement stmt =Conexion.getPreparedStatement(sql);ResultSet rs =stmt.executeQuery();
             System.out.println("Lista de proyectos pasados:");
             while (rs.next()) {     
                 int id =rs.getInt("id");
                 String nombre =rs.getString("nombre");
                 String fecha_inicio =rs.getString("fecha_inicio");
                 String fecha_fin=rs.getString("fecha_fin");
                 
                 System.out.println(id+","+nombre+","+fecha_inicio+","+fecha_fin);
                 
                
            }
             
            
            
        } catch (SQLException e) {
        }
    }
    public static void listarProyectosActivos(){
     String sql ="SELECT id,nombre,fecha_inicio,fecha_fin FROM proyecto WHERE CURRENT_DATE BETWEEN fecha_inicio AND fecha_fin";
        
        try {
             PreparedStatement stmt =Conexion.getPreparedStatement(sql);ResultSet rs =stmt.executeQuery();
             System.out.println("Lista de proyectos pasados:");
             while (rs.next()) {     
                 int id =rs.getInt("id");
                 String nombre =rs.getString("nombre");
                 String fecha_inicio =rs.getString("fecha_inicio");
                 String fecha_fin=rs.getString("fecha_fin");
                 
                 System.out.println(id+","+nombre+","+fecha_inicio+","+fecha_fin);
                 
                
            }
             
            
            
        } catch (SQLException e) {
        }
    }
    public static void listarDetallesProyecto(int id_proyecto) {
        String sql = """
            SELECT 
                p.id AS id_proyecto,
                p.nombre AS nombre_proyecto,
                p.fecha_inicio AS fecha_inicio_proyecto,
                p.fecha_fin AS fecha_fin_proyecto,
                e.id AS id_empleado,
                e.nombre AS nombre_empleado,
                e.dni,
                e.departamento,
                CASE 
                    WHEN e.fecha_finalizacion IS NULL THEN 'Activo'
                    ELSE 'Despedido'
                END AS estado
            FROM 
                proyecto p
            LEFT JOIN 
                empleado_proyecto pe ON p.id = pe.id_proyecto
            LEFT JOIN 
                empleado e ON pe.id_empleado = e.id
            WHERE 
                p.id = ?;
        """;

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);
            stmt.setInt(1, id_proyecto);

            try (ResultSet rs = stmt.executeQuery()) {
               

                while (rs.next()) {
                   
                  

                    int idProyectoRes = rs.getInt("id_proyecto");
                    String nombreProyecto = rs.getString("nombre_proyecto");
                    String fechaInicioProyecto = rs.getString("fecha_inicio_proyecto");
                    String fechaFinProyecto = rs.getString("fecha_fin_proyecto");
                    int idEmpleado = rs.getInt("id_empleado");
                    String nombreEmpleado = rs.getString("nombre_empleado");
                    String dni = rs.getString("dni");
                    String departamento = rs.getString("departamento");
                    String estado = rs.getString("estado");
                    
                    System.out.println(idProyectoRes + "," + nombreProyecto + "," + fechaInicioProyecto + "," + fechaFinProyecto + "," + idEmpleado + "," + nombreEmpleado + "," + dni + "," + departamento + "," + estado);
                }

               

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión o consulta: " + e.getMessage());
        }
    }

    
}
