/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class EmpleadoDAO {
    
    public static void listarEmpleados(){
        String sql="SELECT * FROM empleado";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()){
            
            while (rs.next()) {
            int id =rs.getInt("id");
            String nombre =rs.getString("nombre");
            String puesto =rs.getString("puesto");
            Double salario =rs.getDouble("salario");
            String fecha_ingreso=rs.getString("fecha_ingreso");
            
                System.out.println(id+" , "+nombre+" , "+puesto+" , "+salario+" , "+fecha_ingreso);
                
            }
            
            
            
        } catch (Exception e) {
        }
        
    }
    public  static void anadirEmpleado(String nombre,String puesto, Double salario, String fecha_ingreso){
        
        String sql ="INSERT INTO empleado (nombre,puesto,salario,fecha_ingreso) VALUES (?,?,?,?)";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, nombre);
            stmt.setString(2, puesto);
            stmt.setDouble(3, salario);
            stmt.setString(4, fecha_ingreso);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Cliente añadido");
            }else{
                System.out.println("Error");
            }
            
            
        } catch (SQLException e) {
        }
    }
    public static void modificarEmpleado(int id,String nombre,String puesto, Double salario, String fecha_ingreso){
    
        String sql ="UPDATE empleado SET nombre = ?,puesto = ?,salario = ?, fecha_ingreso = ? WHERE id = ? ";
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, puesto);
            stmt.setDouble(3, salario);
            stmt.setString(4, fecha_ingreso);
            stmt.setInt(5, id);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Cliente modificado");
            }else{
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
    public  static void eliminarEmpleado(int id ){
        String sql ="DELETE FROM empleado WHERE id = ?";
        
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Cliente eliminado");
            }else{
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
    
    
}
