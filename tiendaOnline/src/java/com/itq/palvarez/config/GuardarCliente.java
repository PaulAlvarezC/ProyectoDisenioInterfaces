/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author paul.alvarez
 */
public class GuardarCliente extends Conexion{
    
    public boolean saveClient(String nombre, String direccion, String email){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String sql = "update cliente set Nombres = ?, Direccion = ? where Email = ?";
            System.out.println("SQL: " + sql);
            
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, email);
            int res = ps.executeUpdate();
            
            if(res == 1){
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
}
