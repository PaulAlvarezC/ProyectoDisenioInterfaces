/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author paul.alvarez
 */
public class Conexion {
    private static Connection con = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/tiendaonline?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "Palvarez11";
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion: " + con);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return con;
    }
    
}
