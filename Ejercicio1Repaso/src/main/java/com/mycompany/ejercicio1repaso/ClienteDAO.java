/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tedax
 */
public class ClienteDAO {
    
    public static void listarCliente(){
        String sql ="SELECT * FROM cliente";
        
        String contarCliente="SELECT COUNT(*) AS total FROM CLIENTE";
        
        int totalClientes =0;
        
         try (Connection conn = ConexionDB.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs2 = stmt.executeQuery(contarCliente)) {

            if (rs2.next()) {
                totalClientes = rs2.getInt("total");
            }
            System.out.println("Hay: " + totalClientes + " clientes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(Connection conn =ConexionDB.connection(); Statement stmt =conn.createStatement();ResultSet rs =stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            int id =rs.getInt("id");
            String nombre=rs.getString("nombre");
            String email=rs.getString("email");
            String ciudad=rs.getString("ciudad");
            String telefono=rs.getString("telefono");
                
                
                System.out.println(id+" , "+nombre+" , "+email+" , "+ciudad+" , "+telefono);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public static boolean anadirCliente(String nombre, String email, String ciudad, String telefono) {
        String verificarEmailSQL = "SELECT * FROM cliente WHERE email = ?";
        String insertarClienteSQL = "INSERT INTO cliente (nombre, email, ciudad, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.connection();
             PreparedStatement stmt = conn.prepareStatement(verificarEmailSQL)) {

            // Verificar si el email ya está registrado
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("El email ya está registrado.");
                    return false;
                }
            }

            // Insertar el nuevo cliente
            try (PreparedStatement stmt2 = conn.prepareStatement(insertarClienteSQL)) {
                stmt2.setString(1, nombre);
                stmt2.setString(2, email);
                stmt2.setString(3, ciudad);
                stmt2.setString(4, telefono);

                int rowsAffected = stmt2.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cliente añadido con éxito.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("No se pudo añadir el cliente.");
        return false;
    }
    
    public static void buscarFragmentoEmail(String fragmento){
    String sql ="SELECT * FROM cliente WHERE email LIKE ? ";
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            
            stmt.setString(1,"%"+fragmento +"%");
            
            try(ResultSet rs=stmt.executeQuery()){
                while (rs.next()) {
                    int id =rs.getInt("id");
                    String nombre =rs.getString("nombre");
                    String email=rs.getString("email");
                    String ciudad =rs.getString("ciudad");
                    String telefono =rs.getString("telefono");

                    System.out.println(id+" , "+nombre+" , "+email+" , "+ciudad+" , "+telefono);

                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public static boolean modificarporEmail(String email,String nuevoNombre,String nuevaCiudad,String nuevoTelefono){
    
        String sql ="UPDATE cliente SET nombre = ? , ciudad = ? ,telefono = ? WHERE email = ?";
        
        try(Connection coon =ConexionDB.connection();PreparedStatement stmt =coon.prepareStatement(sql)) {
            
            stmt.setString(1,nuevoNombre);
            stmt.setString(2,nuevaCiudad);
            stmt.setString(3,nuevoTelefono);
            stmt.setString(4,email);
            
            int filasAfetadas =stmt.executeUpdate();
            
            return filasAfetadas >0;
            
        } catch (Exception e) {
        }
        return false;
    }
    public static boolean eliminarPorEmail(String email){
        String sql ="DELETE FROM cliente WHERE email = ? ";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            int filasAfectadas =stmt.executeUpdate();
            
            return filasAfectadas>0;
            
        } catch (Exception e) {
        }
        
        return false;
    
    }
    public static void rankingClientes(){
    String sql ="SELECT c.nombre, c.email, p.fecha, SUM(p.precio_total) AS gasto_total FROM cliente c"
            + " JOIN pedido p ON p.id_cliente=c.id"
            + " GROUP BY c.nombre,c.email,p.fecha"
            + " ORDER BY gasto_total DESC";
    
    
            
            try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()) {
                
                while (rs.next()) {
                String nombre =rs.getString("nombre");
                String email =rs.getString("email");
                String fecha =rs.getString("fecha");
                float precio_total=rs.getFloat("gasto_total");
                    
                    
                    System.out.println(nombre+" , "+email+" , "+fecha+" , "+precio_total);
                    
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static int verificarEmail(String email){
        String verificarEmail="SELECT id FROM cliente WHERE email = ?";
        
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(verificarEmail)) {
            
            stmt.setString(1, email);
            try(ResultSet rs =stmt.executeQuery()){
                
                while (rs.next()) {
                    
                    return rs.getInt("id");
                }
            }
            
        } catch (SQLException e) {
        }
        
        return -1;
    
    }
    public static void agregarCliente (String nombre,String email,String ciudad,String telefono ){
    String insertarCliente="INSERT INTO cliente (nombre,email,ciudad,telefono) VALUES (?,?,?,?)";
    
        try(Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(insertarCliente)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, ciudad);
            stmt.setString(4, telefono);
            
            int filasAfectadas =stmt.executeUpdate();
            
            if (filasAfectadas >0) {
                System.out.println("Cliente añadido");
                
            } else {
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
    public static void insertarPedido(int id_cliente,String fecha,Float precio_total){
        String insertarPedido="INSERT INTO pedido(id_cliente, fecha, precio_total) VALUES (?, ?, ?)";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(insertarPedido)){
            
            stmt.setInt(1, id_cliente);
            stmt.setString(2, fecha);
            stmt.setFloat(3, precio_total);
            
            int filasAfectadas =stmt.executeUpdate();
            
            if (filasAfectadas >0) {
                System.out.println("Pedido añadido");
                
            } else {
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
    }
    
    public static boolean modificarPedidoId(int id ,String fecha,Float precio_total){
    
        String sql ="UPDATE pedido SET fecha = ?,precio_total = ? WHERE id = ?";
        
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
           
            stmt.setString(1, fecha);
            stmt.setFloat(2, precio_total);
            stmt.setInt(3, id);
           
            
            int filasAfectadas =stmt.executeUpdate();
            
            if (filasAfectadas >0) {
                System.out.println("Pedido modificado");
                
            } else {
                System.out.println("Error");
            }
            
            
        } catch (SQLException e) {
        }
        
        return false;
    
    }
    public static boolean borrarPedidoId(int id){
        String sql ="DELETE FROM pedido WHERE id = ?";
        try(Connection conn=ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int filasAfectadas =stmt.executeUpdate();
            
            if (filasAfectadas >0) {
                System.out.println("Pedido eliminado");
                
            } else {
                System.out.println("Error");
            }
            
        } catch (SQLException e) {
        }
        
        return false;
    }
    
    public static void iniciarSesion(String email, String contraseña) {
        String sql = "SELECT nombre FROM cliente WHERE email = ? AND contraseña = ?";

        try (Connection conn = ConexionDB.connection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Configurar los parámetros del PreparedStatement
            stmt.setString(1, email);
            stmt.setString(2, contraseña);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Verificar si hay un resultado
                    System.out.println("Hola " + rs.getString("nombre"));
                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }
            }

        } catch (SQLException e) {
            // Manejo adecuado de errores
            e.printStackTrace();
            System.out.println("Ocurrió un error en la base de datos.");
        }
    }
    public static boolean modificarContraseña(String email,String contraseña){
    
        String sql=" UPDATE cliente SET contraseña = ? WHERE email = ? ";
        
        try (Connection conn=ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, contraseña);
            stmt.setString(2, email);
            
            int filasAfectadas =stmt.executeUpdate();
            
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
        }
        return false;
        
    }

    
        
}
