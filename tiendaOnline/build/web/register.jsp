<%-- 
    Document   : register
    Created on : 02/01/2021, 17:30:30
    Author     : paul.alvarez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alvarez Store</title>
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
                        
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Volver</a>
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
                            <img src=https://www.toptechegypt.com/ibm/img/TOPTECH-logo.png title="User Image" alt="User image" style="width: 230px; height: 80px" />
                        </div>
                        <form action="RegistrarUsuario" method="post">
                            <div class="card-body">
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="ci" placeholder="Identificación" aria-label="Identificación" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="nombres" placeholder="Nombres" aria-label="Nombre" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="direccion" placeholder="Dirección" aria-label="Dirección" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="text" class="form-control" name="email" placeholder="Email" aria-label="Email" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-group mb-3">                        
                                    <input type="password" class="form-control" name="password" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" required>
                                </div>
                                <input type="submit" class="btn btn-primary" value="Registrar"/>
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
