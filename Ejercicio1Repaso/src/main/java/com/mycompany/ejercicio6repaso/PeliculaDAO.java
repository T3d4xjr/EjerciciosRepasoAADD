/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio6repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class PeliculaDAO {
    
    public static void listarPeliculas(){
        String sql ="SELECT * FROM pelicula";
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()){
            
            while (rs.next()) { 
                int id =rs.getInt("id");
                String titulo=rs.getString("titulo");
                String director=rs.getString("director");
                String anio=rs.getString("anio");
                String genero=rs.getString("genero");
                
                System.out.println(id+" , "+titulo+" , "+director+" , "+anio+" , "+genero);
                
                
            }
        } catch (SQLException e) {
        }
    }
    public static void listarPorFragmentoTitulo(String fragmento){
    
        String sql ="SELECT * FROM pelicula WHERE titulo LIKE ?";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, "%"+fragmento+"%");
            
            try(ResultSet rs =stmt.executeQuery()){
                while (rs.next()) { 
                int id =rs.getInt("id");
                String titulo=rs.getString("titulo");
                String director=rs.getString("director");
                String anio=rs.getString("anio");
                String genero=rs.getString("genero");
                
                System.out.println(id+" , "+titulo+" , "+director+" , "+anio+" , "+genero);
                    
                }
            }
            
        } catch (SQLException e) {
        }
    }
    public static void listarPorAño(String agno){
        String sql ="SELECT * FROM pelicula WHERE anio = ?";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, agno);
            
            try(ResultSet rs =stmt.executeQuery()){
                while (rs.next()) { 
                int id =rs.getInt("id");
                String titulo=rs.getString("titulo");
                String director=rs.getString("director");
                String anio=rs.getString("anio");
                String genero=rs.getString("genero");
                
                System.out.println(id+" , "+titulo+" , "+director+" , "+anio+" , "+genero);
                    
                }
            }
            
        } catch (SQLException e) {
        }
    }
    public  static void anadirPelicula(String titulo,String director, String año, String genero){
        
        String sql ="INSERT INTO pelicula (titulo,director,anio,genero) VALUES (?,?,?,?)";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setString(3, año);
            stmt.setString(4, genero);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Pelicula añadido");
            }else{
                System.out.println("Error");
            }
            
            
        } catch (SQLException e) {
        }
    }
    public static void modificarPelicula(int id,String titulo,String director, String año, String genero){
    
        String sql ="UPDATE pelicula SET titulo = ?,director = ?,anio = ?, genero = ? WHERE id = ? ";
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setString(3, año);
            stmt.setString(4, genero);
            stmt.setInt(5, id);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Pelicula modificado");
            }else{
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
    public  static void eliminarPelicula(int id ){
        String sql ="DELETE FROM pelicula WHERE id = ?";
        
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            int rowsaffect =stmt.executeUpdate();
            
            if(rowsaffect>0){
                System.out.println("Pelicula eliminado");
            }else{
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
}
