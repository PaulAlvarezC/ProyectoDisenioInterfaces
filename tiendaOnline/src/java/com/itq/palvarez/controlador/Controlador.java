/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itq.palvarez.controlador;

import com.itq.palvarez.config.Fecha;
import com.itq.palvarez.modelo.Carrito;
import com.itq.palvarez.modelo.Cliente;
import com.itq.palvarez.modelo.Compra;
import com.itq.palvarez.modelo.Producto;
import com.itq.palvarez.modeloDAO.ClienteDAO;
import com.itq.palvarez.modeloDAO.CompraDAO;
import com.itq.palvarez.modeloDAO.ProductoDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author paul.alvarez
 */
public class Controlador extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    CompraDAO cdao = new CompraDAO();
    Producto p = new Producto();
    Cliente cliente;
    List<Producto> productos = new ArrayList<>();
    List<Compra> compras = new ArrayList<>();
    List<Carrito> listaCarrito = new ArrayList<>();
    CompraDAO dao;
    ClienteDAO clienteDao = new ClienteDAO();
    int item = 0;
    double totalPagar = 0.0;
    double iva = 0.0;
    double subTotal = 0.0;
    int cantidad = 1;
    int idp;
    Carrito car;
    String userEmail;
    Boolean isStock;
    int sizeCarrito = 0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String accion = request.getParameter("accion");
        productos = pdao.listar();        
        switch (accion) {
            case "AgregarCarrito":
                int pos = 0;
                cantidad = 1;
                idp = Integer.parseInt(request.getParameter("id"));
                pdao = new ProductoDAO();
                isStock = pdao.stockDisponible(idp);
                System.out.println("STOCK: " + isStock);
                if(isStock){
                    p = pdao.listarId(idp);
                    if(listaCarrito.size() > 0){
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            if(idp == listaCarrito.get(i).getIdProducto()){
                                pos = i;
                            }
                        }
                        if(idp == listaCarrito.get(pos).getIdProducto()){
                            cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                            double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                            listaCarrito.get(pos).setCantidad(cantidad);
                            listaCarrito.get(pos).setSubTotal(subtotal);
                        }else {
                            item = item + 1;
                            car = new Carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());
                            car.setRuta(p.getRuta());
                            listaCarrito.add(car);
                        }
                    }else{
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        car.setRuta(p.getRuta());
                        listaCarrito.add(car);
                    }

                    sizeCarrito = listaCarrito.size();
                    request.setAttribute("contador", sizeCarrito);                    
                    request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                }else{
                    request.getRequestDispatcher("errorStock.jsp").forward(request, response);
                }
                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if(listaCarrito.get(i).getIdProducto() == idproducto){
                        listaCarrito.remove(i);
                    }
                }
                break;
            case "ActualizarCantidad": 
                int idpro = Integer.parseInt(request.getParameter("idp"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if(listaCarrito.get(i).getIdProducto() == idpro){
                        listaCarrito.get(i).setCantidad(cant);
                        double st = listaCarrito.get(i).getPrecioCompra() * cant;
                        listaCarrito.get(i).setSubTotal(st);
                    }
                }
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                iva = totalPagar * 0.12;
                subTotal = totalPagar - iva;
                sizeCarrito = listaCarrito.size();
                request.setAttribute("iva", iva);
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("subtotal", subTotal);
                request.setAttribute("contador", sizeCarrito); 
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "Comprar":
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                pdao = new ProductoDAO();
                isStock = pdao.stockDisponible(idp);
                System.out.println("STOCK: " + isStock);
                item = 0;
                if(isStock){
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());
                    car.setRuta(p.getRuta());
                    listaCarrito.add(car);
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                    }
                    iva = totalPagar * 0.12;
                    subTotal = totalPagar - iva;
                    request.setAttribute("iva", iva);
                    request.setAttribute("totalPagar", totalPagar);
                    request.setAttribute("subtotal", subTotal);
                    request.setAttribute("contador", listaCarrito.size());
                    request.setAttribute("carrito", listaCarrito);
                    request.getRequestDispatcher("carrito.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("errorStock.jsp").forward(request, response);
                }
                break;
            case "GenerarCompra":
                HttpSession objsesionUs = request.getSession(true);
                userEmail = String.valueOf(objsesionUs.getAttribute("usuario"));
                System.out.println("USERMAIL: " + userEmail);
                clienteDao = new ClienteDAO();
                cliente = clienteDao.datosCliente(userEmail);
                Cliente cli = new Cliente();
                int idCliente = cliente.getId();
                cli.setId(idCliente);
                
                System.out.println("USER ID: " + idCliente);
                dao = new CompraDAO();
                Compra compra = new Compra(cli, 1, Fecha.FechaBD(), totalPagar, "Cancelado", listaCarrito);
                int res = dao.GenerarCompra(compra);
                System.out.println("COMPRA RES: " + res);
                System.out.println("TOTAL: " + totalPagar);
                System.out.println("SIZE CARRITO: " + listaCarrito.size());
                if(res != 0 && totalPagar > 0){
                    listaCarrito.clear();
                    sizeCarrito = listaCarrito.size();
                    System.out.println("SIZE CARRITO: " + listaCarrito.size());
                    request.setAttribute("contador", sizeCarrito); 
                    String cardName = request.getParameter("cardName");
                    String cardNumber = request.getParameter("cardNumber");
                    String cardDate = request.getParameter("cardDate");
                    String cardCode = request.getParameter("cardCode");
                    System.out.println("CARD DATA: " + cardName + " " + cardNumber + " " + cardDate + " " + cardCode);
                    boolean cardIsSaving;
                    cardIsSaving = clienteDao.datosTarjeta(cardName, cardNumber, cardDate, cardCode);
                    if(cardIsSaving){
                        System.out.println("CARD DATA SAVE...");
                    }
                    item = 0;
                    request.getRequestDispatcher("confirmacion.jsp").forward(request, response);                    
                }else {
                    request.getRequestDispatcher("errorStock.jsp").forward(request, response);
                }
                break;
            case "Login":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "Register":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "AgregarProducto":
                request.setAttribute("contador", sizeCarrito);  
                request.getRequestDispatcher("agregarProducto.jsp").forward(request, response);
                break;
            case "GuardarProducto":
                ArrayList<String> lista = new ArrayList<>();
                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem)items.get(i);
                        if(!fileItem.isFormField()){
                            File f = new File("/Applications/XAMPP/xamppfiles/htdocs/img/" + fileItem.getName());
                            fileItem.write(f);
                            p.setRuta("http://localhost/img/" + fileItem.getName());
                        }else{
                            lista.add(fileItem.getString());
                        }
                    }
                    p.setNombres(lista.get(0));
                    p.setDescripcion(lista.get(1));
                    p.setPrecio(Double.parseDouble(lista.get(2)));
                    p.setStock(Integer.parseInt(lista.get(3)));
                    pdao.crearProducto(p);
                    productos = pdao.listar();
                    request.setAttribute("productos", productos);                    
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "MisProductos":
                
                break;
            case "MisCompras":
                HttpSession objsesionComp = request.getSession(true);
                userEmail = String.valueOf(objsesionComp.getAttribute("usuario"));
                System.out.println("EMAIL COMPRAS: " + userEmail);
                clienteDao = new ClienteDAO();
                Cliente clie = clienteDao.datosCliente(userEmail);                
                int idClient = clie.getId();
                System.out.println("ID CLIENTE: " + idClient);
                compras = cdao.listar(idClient);
                request.setAttribute("compras", compras);
                request.setAttribute("contador", sizeCarrito);  
                request.getRequestDispatcher("miscompras.jsp").forward(request, response);
                break;
            case "CerrarSesion":
                HttpSession objsesion = request.getSession(true);
                objsesion.setAttribute("usuario", "");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "UserProfile":                
                HttpSession objsesionPr = request.getSession(true);
                userEmail = String.valueOf(objsesionPr.getAttribute("usuario"));
                System.out.println("EMAIL: " + userEmail);
                clienteDao = new ClienteDAO();
                Cliente clis = clienteDao.datosCliente(userEmail);
                objsesionPr.setAttribute("pdni", clis.getDni());
                objsesionPr.setAttribute("pnombre", clis.getNombre());
                objsesionPr.setAttribute("pdireccion", clis.getDireccion());
                objsesionPr.setAttribute("pemail", userEmail);
                objsesionPr.setAttribute("ppassword", clis.getPassword());
                request.setAttribute("contador", sizeCarrito);  
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
            case "verOfertas":
                request.setAttribute("contador", sizeCarrito);  
                request.getRequestDispatcher("ofertas.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("contador", sizeCarrito); 
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
