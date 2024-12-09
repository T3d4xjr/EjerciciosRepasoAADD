/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio7repaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class MatriculaDAO {
    
    public static void matriculaEstudianteAsignatura(String emailEstudiante,String nombreAsignatura,int año){
        String estudiante ="SELECT id FROM estudiantes WHERE email = ?";
        String asignatura ="SELECT id FROM asignaturas WHERE nombre= ?";
        String matricula  ="INSERT INTO matriculas (id_estudiante,id_asignatura,año,estado,calificacion) VALUES (?,?,?,?,?)";
        
        try(Connection conn =ConexionDB.connection();
                PreparedStatement stmt1 =conn.prepareStatement(estudiante);
                PreparedStatement stmt2 =conn.prepareStatement(asignatura);
                PreparedStatement stmt3 =conn.prepareStatement(matricula)) {
            
            stmt1.setString(1, emailEstudiante);
            try(ResultSet rs1 =stmt1.executeQuery()){
                if(!rs1.next()){
                    System.err.println("Error al pillar el "+emailEstudiante);
                }
                int idEstudiante =rs1.getInt("id");
                
                stmt2.setString(1, nombreAsignatura);
                try(ResultSet rs2 =stmt2.executeQuery()){
                    if(!rs2.next()){
                        System.err.println("Error al pillar el "+nombreAsignatura);
                    }
                    int idAsignatura =rs2.getInt("id");
                    
                    
                    stmt3.setInt(1, idEstudiante);
                    stmt3.setInt(2, idAsignatura);
                    stmt3.setInt(3, año);
                    stmt3.setString(4, "cursando");
                    stmt3.setDouble(5, 0.0);
                    
                    int filasAfectadas =stmt3.executeUpdate();
            
                    if(filasAfectadas>0){
                        System.out.println("Matricula añadida");
                    }else{
                        System.err.println("Error al añadir");
                    }
                    
                }
                
            }
            
        } catch (Exception e) {
        }

    }
        public static void añadirCalificacion(String emailEstudiante, String nombreAsignatura, int año, double calificacion) {
        String estudiante = "SELECT id FROM estudiantes WHERE email = ?";
        String asignatura = "SELECT id FROM asignaturas WHERE nombre = ?";
        String matricula = "UPDATE matriculas SET calificacion = ?, estado = ? WHERE id_estudiante = ? AND id_asignatura = ? AND año = ?";

        try (Connection conn = ConexionDB.connection();
             PreparedStatement stmt1 = conn.prepareStatement(estudiante);
             PreparedStatement stmt2 = conn.prepareStatement(asignatura);
             PreparedStatement stmt3 = conn.prepareStatement(matricula)) {

            stmt1.setString(1, emailEstudiante);
            try (ResultSet rs1 = stmt1.executeQuery()) {
                if (!rs1.next()) {
                    System.err.println("Error: no se encontró el estudiante con email " + emailEstudiante);
                    return;
                }
                int idEstudiante = rs1.getInt("id");

                stmt2.setString(1, nombreAsignatura);
                try (ResultSet rs2 = stmt2.executeQuery()) {
                    if (!rs2.next()) {
                        System.err.println("Error: no se encontró la asignatura con nombre " + nombreAsignatura);
                        return;
                    }
                    int idAsignatura = rs2.getInt("id");

                    String estado = (calificacion >= 5) ? "superada" : "no superada";

                    stmt3.setDouble(1, calificacion);
                    stmt3.setString(2, estado);
                    stmt3.setInt(3, idEstudiante);
                    stmt3.setInt(4, idAsignatura);
                    stmt3.setInt(5, año);

                    int filasAfectadas = stmt3.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("Calificación añadida correctamente.");
                    } else {
                        System.err.println("No se encontró una matrícula que coincida con los criterios especificados: " +
                                "idEstudiante=" + idEstudiante + ", idAsignatura=" + idAsignatura + ", año=" + año);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void historialMatriculaEstudiante(String emailEstudiante){
    String sql = "SELECT m.año, e.nombre AS estudiante_nombre, a.nombre AS asignatura_nombre, m.estado, m.calificacion " +
                 "FROM matriculas m " +
                 "JOIN estudiantes e ON e.id = m.id_estudiante " +
                 "JOIN asignaturas a ON a.id = m.id_asignatura " +
                 "WHERE e.email = ? " +
                 "ORDER BY m.año DESC";

    
        try (Connection conn =ConexionDB.connection();PreparedStatement stmt =conn.prepareStatement(sql)){
            
            stmt.setString(1, emailEstudiante);
            try(ResultSet rs =stmt.executeQuery()) {
                while (rs.next()) {
                String nombreEstudiante =rs.getString("estudiante_nombre");
                String nombreAsignatura =rs.getString("asignatura_nombre");
                int año =rs.getInt("año");
                String estado =rs.getString("estado");
                double calificacion=rs.getDouble("calificacion");
                
                    System.out.println("Historial de matriculas de " +nombreEstudiante);
                    
                    System.out.println(año+","+nombreEstudiante+","+nombreAsignatura+","+estado+","+calificacion);
                    
                }
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public static void cancelarMatricula(String emailEstudiante, String nombreAsignatura, int año) {
        String estudiante = "SELECT id FROM estudiantes WHERE email = ?";
        String asignatura = "SELECT id FROM asignaturas WHERE nombre = ?";
        String matricula = "UPDATE matriculas SET estado = 'cancelada' WHERE id_estudiante = ? AND id_asignatura = ? AND año = ? AND estado = 'cursando'";

        try (Connection conn = ConexionDB.connection();
             PreparedStatement stmt1 = conn.prepareStatement(estudiante);
             PreparedStatement stmt2 = conn.prepareStatement(asignatura);
             PreparedStatement stmt3 = conn.prepareStatement(matricula)) {

            stmt1.setString(1, emailEstudiante);
            try (ResultSet rs1 = stmt1.executeQuery()) {
                if (!rs1.next()) {
                    System.err.println("Error: no se encontró el estudiante con email " + emailEstudiante);
                    return;
                }
                int idEstudiante = rs1.getInt("id");

                stmt2.setString(1, nombreAsignatura);
                try (ResultSet rs2 = stmt2.executeQuery()) {
                    if (!rs2.next()) {
                        System.err.println("Error: no se encontró la asignatura con nombre " + nombreAsignatura);
                        return;
                    }
                    int idAsignatura = rs2.getInt("id");

                    stmt3.setInt(1, idEstudiante);
                    stmt3.setInt(2, idAsignatura);
                    stmt3.setInt(3, año);

                    int filasAfectadas = stmt3.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("Matricula cancelada.");
                    } else {
                        System.err.println("No se encontró una matrícula que coincida con los criterios especificados: " +
                                "idEstudiante=" + idEstudiante + ", idAsignatura=" + idAsignatura + ", año=" + año);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
