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
public class Empleado_proyectoDAO {

    public static void añadirProyectoConEmpleados(String nombre, String fecha_inicio, String fecha_fin, String empleados) throws SQLException {
        String sqlProyecto = "INSERT INTO proyecto (nombre, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";
        String sqlEmpleadoProyecto = "INSERT INTO empleado_proyecto (id_empleado, id_proyecto) VALUES (?, ?)";

        try {
            Conexion.startTransaction();
            // Convertir las fechas de String a java.sql.Date
            Date sqlFechaInicio = Date.valueOf(fecha_inicio);
            Date sqlFechaFin = Date.valueOf(fecha_fin);

            // Comprobar si la fecha de finalización es posterior a la de inicio
            if (sqlFechaFin.before(sqlFechaInicio)) {
                System.err.println("Error: La fecha de finalización debe ser posterior a la fecha de inicio.");
                return;  // Detenemos la ejecución si la condición no se cumple
            }

            // Insertar el proyecto
            PreparedStatement stmtProyecto = Conexion.getPreparedStatementReturnKeys(sqlProyecto);
            stmtProyecto.setString(1, nombre);
            stmtProyecto.setDate(2, sqlFechaInicio);
            stmtProyecto.setDate(3, sqlFechaFin);

            int row = stmtProyecto.executeUpdate();

            if (row == 0) {
                System.err.println("No se pudo añadir el proyecto.");
            } else {
                // Obtener el ID del proyecto insertado
                ResultSet rs = stmtProyecto.getGeneratedKeys();
                if (rs.next()) {
                    int idProyecto = rs.getInt(1);
                    System.out.println("Este es el ID del proyecto insertado: " + idProyecto);

                    // Insertar empleados en el proyecto
                    String[] idsEmpleados = empleados.split(" ");  // Separar los IDs de empleados
                        
                    PreparedStatement stmtEmpleadoProyecto = Conexion.getPreparedStatement(sqlEmpleadoProyecto);
                    
                    for (String idEmp : idsEmpleados) {
                        try {
                            int idEmpleado = Integer.parseInt(idEmp.trim());

                            // Preparar la inserción de la relación empleado-proyecto
                            
                            stmtEmpleadoProyecto.setInt(1, idEmpleado);
                            stmtEmpleadoProyecto.setInt(2, idProyecto);
                            stmtEmpleadoProyecto.addBatch();

                           
                        } catch (NumberFormatException e) {
                            System.err.println("Error: El ID del empleado " + idEmp + " no es válido.");
                        }
                    }
                    int[] row2 =stmtEmpleadoProyecto.executeBatch();
                }
            }
            Conexion.commit();
        } catch (SQLException e) {
            Conexion.rollback();
            System.err.println("Error en la base de datos: " + e.getMessage());
        }
    }

    public static void añadirEmpleadosAProyecto(int idProyecto, String empleados) throws SQLException {
         
        try {
            Conexion.startTransaction();

            // Si el proyecto existe, continuar insertando empleados
            String[] idsEmpleados = empleados.split(" ");  // Separar los IDs de empleados
            
            String sqlEmpleadoProyecto = "INSERT INTO empleado_proyecto (id_empleado, id_proyecto) VALUES (?, ?)";
            PreparedStatement stmtEmpleadoProyecto = Conexion.getPreparedStatement(sqlEmpleadoProyecto);
                  
            for (String idEmp : idsEmpleados) {
              
                    int idEmpleado = Integer.parseInt(idEmp.trim());
                    
                    // Insertar la relación empleado-proyectp
                    stmtEmpleadoProyecto.setInt(1, idEmpleado);
                    stmtEmpleadoProyecto.setInt(2, idProyecto);
                    stmtEmpleadoProyecto.addBatch();
                
            }
            int[] rowEmpleado = stmtEmpleadoProyecto.executeBatch();
            Conexion.commit();
        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            Conexion.rollback();
        }
    }

    public static void añadirEmpleadoProyecto(int id_proyecto, int id_empleado) {

        String sql = "INSERT INTO empleado_proyecto (id_empleado,id_proyecto) VALUES (?,?)";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);

            stmt.setInt(1, id_empleado);
            stmt.setInt(2, id_proyecto);

            int row = stmt.executeUpdate();

            if (row == 0) {
                System.err.println("Error al añadir el empleado del proyecto");
            } else {
                System.out.println("Empleado añadir correctamente");
            }

        } catch (SQLException e) {
        }
    }

    public static void EliminarEmpleadoProyecto(int id_proyecto, int id_empleado) {

        String sql = "DELETE FROM empleado_proyecto WHERE  id_proyecto = ? AND id_empleado = ?";

        try {
            PreparedStatement stmt = Conexion.getPreparedStatement(sql);

            stmt.setInt(1, id_proyecto);
            stmt.setInt(2, id_empleado);

            int row = stmt.executeUpdate();

            if (row == 0) {
                System.err.println("Error al eliminar el empleado del proyecto");
            } else {
                System.out.println("Empleado eliminado correctamente");
            }

        } catch (SQLException e) {
        }
    }
}
