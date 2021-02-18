/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.config;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author paul.alvarez
 */
public class Autenticacion extends Conexion{
    
    public boolean autenticacion(String user, String password){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String sql = "select * from cliente where Email = ? and Password = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            
            if(rs.absolute(1)){
                return true;
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        } finally {
            try {
                if(getConnection() != null) getConnection().close();
                if(ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("FINALLY ERROR: " + e);
            }
        }      
        return false;
    }
    
    
    public boolean registrar(String ci, String nombres, String direccion, String email, String password){
        PreparedStatement ps = null;
        
        try {
            String sql = "insert into cliente (Ci, Nombres, Direccion, Email, Password)values(?,?,?,?,?)";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, ci);
            ps.setString(2, nombres);
            ps.setString(3, direccion);
            ps.setString(4, email);
            ps.setString(5, password);
            
            if(ps.executeUpdate() == 1){
                return true;
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        } finally {
            try {
                if(getConnection() != null) getConnection().close();
                if(ps != null) ps.close();
            } catch (Exception e) {
                System.err.println("FINALLY ERROR: " + e);
            }
        } 
        return false;
    }
}
