/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio10repaso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joseg
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/ad_ej10";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    
    private static Connection conexion = null;

    private static Connection getConexion() throws SQLException {
        if(conexion == null){
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
    
    public static Statement createStatement() throws SQLException{
        return getConexion().createStatement();
    }
    
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException{
        return getConexion().prepareStatement(sql);
    }
    
    public static PreparedStatement getPreparedStatementReturnKeys(String sql) throws SQLException{
        return getConexion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    public static void startTransaction() throws SQLException{
        getConexion().setAutoCommit(false);
    }
    
    public static void commit() throws SQLException{
        getConexion().commit();
        getConexion().setAutoCommit(true);
    }
    
    public static void rollback() throws SQLException{
        getConexion().rollback();
        getConexion().setAutoCommit(true);
    }

    public static void close() throws SQLException{
        getConexion().close();
    }
}
