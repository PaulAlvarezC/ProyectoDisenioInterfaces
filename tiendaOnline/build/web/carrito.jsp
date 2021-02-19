<%-- 
    Document   : carrito
    Created on : 02/01/2021, 17:32:35
    Author     : paul.alvarez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("usuario");
    if(usuario.equals("")){
        usuario = "usuario";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Online</title>
        <link rel="shortcut icon" href="favicon.ico"/>
        <link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <img src=https://www.toptechegypt.com/ibm/img/TOPTECH-logo.png title="User Image" alt="User image" style="width: 230px; height: 80px" />
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Controlador?accion=home">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=verOfertas">Ofertas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus"> ( <label style="color: red">${contador}</label> )</i></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=AgregarProducto">Agregar Producto</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=UserProfile">Bienvenido <% out.println(usuario); %></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Configuraciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="Controlador?accion=MisProductos">Mis Productos</a></li>
                                <li><a class="dropdown-item" href="Controlador?accion=MisCompras">Mis Compras</a></li>
                                <li><hr class="dropdown-divider"></li>                                
                                <li><a class="dropdown-item" href="Controlador?accion=Login">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h3>Carrito</h3><br>
            <div class="row">
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ITEM</th>
                                <th>NOMBRE</th>
                                <th>DESCRIPCIÓN</th>
                                <th>PRECIO</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>
                                <th>ACCIONES</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carrito}">
                                <tr>
                                    <td>${car.getItem()}</td>
                                    <td>${car.getNombres()}</td>
                                    <td>${car.getDescripcion()}
                                    </td>
                                    <td>${car.getPrecioCompra()}</td>
                                    <td>
                                        <input type="hidden" id="idpro" value="${car.getIdProducto()}">
                                        <input type="number" id="Cantidad" value="${car.getCantidad()}" class="form-control text-center" min="1">
                                    </td>
                                    <td>${car.getSubTotal()}</td>
                                    <td>
                                        <input type="hidden" id="idp" value="${car.getIdProducto()}">
                                        <a href="#" id="btnDelete" class="">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">                    
                    <div class="card">
                        <div class="card-header">
                            <h3>Detalle de Compra</h3>
                        </div>
                        <div class="card-body">
                            <label>SubTotal:</label>
                            <input type="text" value="$ ${subtotal}0" readonly="" class="form-control"/>                            
                            <label>IVA (12%):</label>
                            <input type="text" value="$ ${iva}0" readonly="" class="form-control"/>
                            <label>Total a Pagar:</label>
                            <input type="text" value="$ ${totalPagar}0" readonly="" class="form-control"/>
                            <div class="card-header">
                                <h3>Realizar el Pago</h3>
                            </div>
                            <form action="Controlador?accion=GenerarCompra" method="post">
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="cardName" placeholder="Nombre de Tarjeta" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="number" maxlength="16" class="form-control" name="cardNumber" placeholder="Número de Tarjeta" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="cardDate" placeholder="Fecha Caducidad" required>
                                </div>
                                <div class="input-group mb-2"> 
                                    <input type="password" maxlength="3" class="form-control" name="cardCode" placeholder="CVV" required>
                                </div> 
                                <center>
                                    <input type="submit" class="btn btn-success btn-block" value="Realizar Pago"/>
                                </center>                                
                            </form>
                        </div>
                    </div>
                </div>                
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
    </body>
</html>
