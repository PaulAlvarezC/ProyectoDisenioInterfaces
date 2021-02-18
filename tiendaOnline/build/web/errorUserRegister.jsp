<%-- 
    Document   : errorUser
    Created on : 03/01/2021, 11:09:40
    Author     : paul.alvarez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <title>Alvarez Store</title>
        <link rel="shortcut icon" href="favicon.ico"/>
    </head>
    <body>
        <div class="container mt-4">
            <center>
                <div class="col-sm-4">
                    <div class="alert alert-error" role="alert">
                        <h4 class="alert-heading">
                            Error al registrar usuario, intente más tarde!
                        </h4>
                        <a href="Controlador?accion=Login" class="btn btn-warning">Volver</a>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
