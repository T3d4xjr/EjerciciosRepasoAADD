/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio10repaso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class EmpleadoDAO {

    public static void añadirEmpleado(String nombre, String dni, String departamento, double sueldo, String fecha_contratacion) {
        
        String sqlCheck = "SELECT id FROM empleado WHERE dni = ?";
        
        String sqlInsert = "INSERT INTO empleado(nombre, dni, departamento, sueldo, fecha_contratacion) VALUES(?,?,?,?,?)";
       
        String sqlUpdate = "UPDATE empleado SET nombre = ?, departamento = ?, sueldo = ?, fecha_contratacion = ?, fecha_finalizacion = NULL WHERE id = ?";

        try {
            
            PreparedStatement stmtCheck = Conexion.getPreparedStatement(sqlCheck);
            stmtCheck.setString(1, dni);
            ResultSet rsCheck = stmtCheck.executeQuery();

            if (rsCheck.next()) {  
          
                int idEmpleado = rsCheck.getInt("id");

      
                PreparedStatement stmtUpdate = Conexion.getPreparedStatement(sqlUpdate);
                stmtUpdate.setString(1, nombre);
                stmtUpdate.setString(2, departamento);
                stmtUpdate.setDouble(3, sueldo);
                stmtUpdate.setString(4, fecha_contratacion);
                stmtUpdate.setInt(5, idEmpleado);

                int rowUpdate = stmtUpdate.executeUpdate();

                if (rowUpdate == 0) {
                    System.err.println("Error al actualizar el empleado");
                } else {
                    System.out.println("Empleado actualizado correctamente");
                }
            } else {

                PreparedStatement stmtInsert = Conexion.getPreparedStatementReturnKeys(sqlInsert);
                stmtInsert.setString(1, nombre);
                stmtInsert.setString(2, dni);
                stmtInsert.setString(3, departamento);
                stmtInsert.setDouble(4, sueldo);
                stmtInsert.setString(5, fecha_contratacion);

                int rowInsert = stmtInsert.executeUpdate();

                if (rowInsert > 0) {

                    ResultSet rsInsert = stmtInsert.getGeneratedKeys();
                    if (rsInsert.next()) {
                        int idEmpleado = rsInsert.getInt(1);
                        System.out.println("Empleado añadido correctamente con ID: " + idEmpleado);
                    }
                } else {
                    System.err.println("Error al añadir el empleado");
                }
            }

        } catch (SQLException e) {

            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public static void modificarEmpleado(int id_empleado, String nombre, String departamento, double sueldo) {

        String sql = "UPDATE empleado SET nombre = ?,departamento = ?,sueldo = ? WHERE id = ?";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);

            stmt.setString(1, nombre);
            stmt.setString(2, departamento);
            stmt.setDouble(3, sueldo);
            stmt.setInt(4, id_empleado);

            int row = stmt.executeUpdate();

            if (row == 0) {
                System.err.println("Error al modificar el empleado ");
            } else {
                System.out.println("Empleado añadir correctamente");
            }

        } catch (SQLException e) {
        }

    }

    public static void despidirEmpleado(int id_empleado, String fecha_finalizacion) {

        String sql = "UPDATE empleado SET fecha_finalizacion = ? WHERE id = ?";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);

            stmt.setString(1, fecha_finalizacion);
            stmt.setInt(2, id_empleado);

            int row = stmt.executeUpdate();

            if (row == 0) {
                System.err.println("Error al despedir el empleado ");
            } else {
                System.out.println("Empleado despedido correctamente");
            }

        } catch (SQLException e) {
        }
    }

    public static void listarEmpleadosActivos() {
        String sql = "SELECT id,nombre,dni,departamento,fecha_contratacion FROM empleado WHERE fecha_finalizacion IS NULL";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Lista de empleados activos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String departamento = rs.getString("departamento");
                String fecha_contratacion = rs.getString("fecha_contratacion");

                System.out.println(id + "," + nombre + "," + dni + "," + departamento + "," + fecha_contratacion);

            }

        } catch (Exception e) {
        }
    }

    public static void listarEmpleadosDespedidos() {
        String sql = "SELECT id,nombre,dni,departamento,fecha_contratacion FROM empleado WHERE fecha_finalizacion IS NOT NULL";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Lista de empleados despedidos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String departamento = rs.getString("departamento");
                String fecha_contratacion = rs.getString("fecha_contratacion");

                System.out.println(id + "," + nombre + "," + dni + "," + departamento + "," + fecha_contratacion);

            }

        } catch (Exception e) {
        }
    }
}
