/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.modeloDAO;

import com.itq.palvarez.config.Conexion;
import static com.itq.palvarez.config.Conexion.getConnection;
import com.itq.palvarez.modelo.Carrito;
import com.itq.palvarez.modelo.Compra;
import com.itq.palvarez.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paul.alvarez
 */
public class CompraDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    Producto p = new Producto();
    
    public int GenerarCompra(Compra compra){
        int idcompras;
        String sql;
        String sqlUpdate;
        int cantidadProducto;
        int difCantidad;
        int idProduct;
        boolean success = false;
        int idCliente;
        int idPago = compra.getIdpago();
        String fecha = compra.getFecha();
        String estado = compra.getEstado();
        Double monto = 0.0; 
        try {
            idCliente = compra.getCliente().getId();
            cantidadProducto = 0;
            for (int i = 0; i < compra.getDetallecompras().size(); i ++) {
                idProduct = compra.getDetallecompras().get(i).getIdProducto();
                System.out.println("PRODUCT ID: " + idProduct);
                monto = compra.getDetallecompras().get(i).getPrecioCompra();
                sqlUpdate = "select * from producto where idProducto = " + idProduct;
                ps = getConnection().prepareStatement(sqlUpdate);
                rs = ps.executeQuery();
                while(rs.next()){
                    p.setId(rs.getInt(1));
                    p.setNombres(rs.getString(2));
                    p.setFoto(rs.getBinaryStream(3));
                    p.setDescripcion(rs.getString(4));
                    p.setPrecio(rs.getDouble(5));
                    p.setStock(rs.getInt(6));
                    cantidadProducto = p.getStock();                    
                    System.out.println("STOCK: " + cantidadProducto);                    
                }
                if(cantidadProducto > 0){
                    
                    //ACTUALIZO EL STOCK
                    difCantidad = cantidadProducto - compra.getDetallecompras().get(i).getCantidad();
                    System.out.println("CANTIDAD: " + compra.getDetallecompras().get(i).getCantidad());
                    System.out.println("STOCK - CANTIDAD: " + difCantidad);
                                        
                    sqlUpdate = "update producto set Stock = ? where idProducto = ?";
                    ps = getConnection().prepareStatement(sqlUpdate);
                    ps.setInt(1, difCantidad);
                    ps.setInt(2, idProduct);
                    int res = ps.executeUpdate();
                    if(res == 1){
                        System.out.println("UPDATE STOCK PRODUCTO: " + res);                        
                        success = true;
                    }
                }else{
                    System.out.println("No hay stock!");
                    success = false;
                }
            }
            
            if(success){
                //INSERTO LA COMPRA
                sql = "insert into compras(idCliente, FechaCompras, Monto, Estado, idPago) values(?,?,?,?,?)";
                con = Conexion.getConnection();
                ps = con.prepareStatement(sql);
                System.out.println("Cliente: " + idCliente + " Fecha: " + fecha + " Monto: " + monto + " Estado: " + estado);
                ps.setInt(1, idCliente);
                ps.setString(2, compra.getFecha());
                ps.setDouble(3, compra.getMonto());
                ps.setString(4, compra.getEstado());
                ps.setInt(5, idPago);
                r = ps.executeUpdate();
                
                sql = "Select @@IDENTITY AS idCompras";
                rs = ps.executeQuery(sql);
                rs.next();
                idcompras = rs.getInt("idCompras");
                rs.close();

                for (Carrito detalle : compra.getDetallecompras()) {
                    sql = "insert into detalle_compras(idProducto, idCompras, Cantidad, PrecioCompra) values(?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, detalle.getIdProducto());
                    ps.setInt(2, idcompras);
                    ps.setInt(3, detalle.getCantidad());
                    ps.setDouble(4, detalle.getPrecioCompra());
                    r = ps.executeUpdate(); 
                }
            }else{
                System.out.println("Success: " + success);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        return r;
    }
    
    public List listar(int idCliente){
        List<Compra> compras = new ArrayList();
        String sql = "select * from compras where idCliente = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra c = new Compra();
                c.setId(rs.getInt(1));
                //c.getCliente().setId(rs.getInt(2));
                c.setIdpago(rs.getInt(3));
                c.setFecha(rs.getString(4));
                c.setMonto(rs.getDouble(5));
                c.setEstado(rs.getString(6));
                compras.add(c);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Error al listar compras");
        }
        return compras;
    }
}
