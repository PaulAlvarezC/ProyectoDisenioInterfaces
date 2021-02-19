<%-- 
    Document   : profile
    Created on : 02/01/2021, 17:30:30
    Author     : paul.alvarez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    HttpSession objsesion = request.getSession(false);
    String usuario = (String) objsesion.getAttribute("usuario");
    String nombre = (String) objsesion.getAttribute("pnombre");
    String direccion = (String) objsesion.getAttribute("pdireccion");
    String dni = (String) objsesion.getAttribute("pdni");
    String email = (String) objsesion.getAttribute("pemail");
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
        <div class="container mt-5">
            <div class="col-sm-3">
                
            </div>
            <center>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">
                            <img src=https://png.pngtree.com/png-clipart/20190924/original/pngtree-user-vector-avatar-png-image_4830521.jpg title="User Image" alt="User image" style="width: 100px; height: 100px">
                            <strong>Información de Usuario</strong>                            
                        </div>
                        <form action="ControladorCliente" method="post">                                                    
                            <div class="card-body">
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" value="<% out.println(dni); %>" name="dni" placeholder="Dni" aria-label="Dni" aria-describedby="basic-addon1" disabled>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" value="<% out.println(email); %>" name="email" placeholder="Email" aria-label="Email" aria-describedby="basic-addon1">
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" value="<% out.println(nombre); %>" name="nombre" placeholder="Nombre" aria-label="Nombre" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-group mb-3"> 
                                    <input type="text" class="form-control" value="<% out.println(direccion); %>" name="direccion" placeholder="Direccion" aria-label="Direccion" aria-describedby="basic-addon1" required>
                                </div>
                                <input type="submit" class="btn btn-primary" value="Guardar"/>
                            </div>
                        </form>
                    </div>
                </div>
            </center>
            <div class="col-sm-3">
                
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
