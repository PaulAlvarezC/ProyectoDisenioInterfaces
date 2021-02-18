$(document).ready(function() {
    $("tr #btnDelete ").click(function (){
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "Estas seguro?",
            text: "Una vez eliminado, puedes volver a agregar más productos al carrito!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(idp);
              swal("Listo! Elemento eliminado del carrito!", {
                icon: "success",
              }) .then((willDelete) => {
                  if(willDelete ){
                      parent.location.href="Controlador?accion=Carrito";
                  }
              });
            } else {
              swal("Registro no eliminado!");
            }
        });
    });
    
    function eliminar(idp){
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp,
            success:function (data, textStatus, jqXHR){
            }
        });
    }
    
    $("tr #Cantidad").click(function (){
        var idp = $(this).parent().find("#idpro").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp + "&Cantidad=" + cantidad,
            success:function (data, textStatus, jqXHR){
                location.href="Controlador?accion=Carrito";
            }
        });
    });
});
