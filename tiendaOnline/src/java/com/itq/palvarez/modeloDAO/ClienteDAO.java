/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.modeloDAO;

import com.itq.palvarez.config.Conexion;
import com.itq.palvarez.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author paul.alvarez
 */
public class ClienteDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Cliente datosCliente(String email){
        String sql = "select * from cliente where Email = '" + email + "'";
        System.out.println("EMAIL DAO: " + email);
        Cliente c = new Cliente();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setCorreo(rs.getString(5));
                c.setPassword(rs.getString(6));
                             
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return c;
    }
}
