/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5repaso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tedax
 */
public class ConexionDB {
    private static final String URL="jdbc:mysql://localhost:3306/ad_ej5";
    private static  final String USER="2dam";
    private static final String PASSWORD="2dam";
    
    public static Connection connection() throws SQLException{
    return  DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
