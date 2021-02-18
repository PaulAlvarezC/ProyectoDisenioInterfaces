<%-- 
    Document   : error
    Created on : 03/01/2021, 11:09:40
    Author     : paul.alvarez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <title>TOPTECH</title>
        <link rel="shortcut icon" href="favicon.ico"/>
    </head>
    <body>
        <div class="container mt-8">
            <center>
                <div class="col-sm-8">
                    <div class="alert alert-error" role="alert">
                        <h4 class="alert-heading">
                            
                        </h4>
                        <div class="col-sm-6">
                            <div class="card mt-6">
                                <div class="card-header">
                                    <label><strong>El producto seleccionado ya no esta disponible!</strong></label>
                                </div>
                                <div class="card-body">
                                    <i></i>
                                    <img src="out.png" width="180" height="180">
                                </div>
                                <div class="card-footer text-center">
                                    <label></label>                                
                                    <div>
                                        <br>
                                        <a href="Controlador?accion=home" class="btn btn-warning">Volver</a>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
