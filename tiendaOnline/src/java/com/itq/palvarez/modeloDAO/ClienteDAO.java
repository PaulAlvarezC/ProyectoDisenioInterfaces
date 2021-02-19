/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.modeloDAO;

import com.itq.palvarez.config.Conexion;
import static com.itq.palvarez.config.Conexion.getConnection;
import com.itq.palvarez.modelo.Cliente;
import com.itq.palvarez.modelo.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            con = Conexion.getConnection();
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
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        return c;
    }
    
    public Boolean datosTarjeta(String cardName, String cardNumber, String cardDate, String cardCode) throws SQLException{
        String sql = "select * from tarjeta where cardNumber = ?";
        System.out.println("cardName: " + cardName);
        System.out.println("cardName: " + cardNumber);
        System.out.println("cardName: " + cardDate);
        System.out.println("cardName: " + cardCode);
        Tarjeta t = new Tarjeta();
        
        try {
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, cardNumber);
            rs = ps.executeQuery();

            if(rs.absolute(1)){
                System.out.println("TARJETA YA EXISTE");
                return false;                
            }else{
                System.out.println("TARJETA NO EXISTE... GUARDANDO");
                String sqli = "insert into tarjeta (CardName, CardNumber, CardDate, CardCode)values(?,?,?,?)";
                ps = getConnection().prepareStatement(sqli);
                ps.setString(1, cardName);
                ps.setString(2, cardNumber);
                ps.setString(3, cardDate);
                ps.setString(4, cardCode);

                if(ps.executeUpdate() == 1){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e);
        }
        return false;
    }
}
