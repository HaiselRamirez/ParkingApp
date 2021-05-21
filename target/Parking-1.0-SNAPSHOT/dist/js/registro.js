$(function(){
  getTarifa();
});


function getTarifa(){
  $("#tAuto").change(function (e) { 
    e.preventDefault();
    let tipo = $(this).val();
    let dato = {"id":tipo, "accion":"list"};
    $.ajax({
      type: "POST",
      url: "Vehiculo",
      data: dato,
      dataType: "json",
      success: function (data) {
        $("#tTarifa").val(data.tarifa)
      }
    });
  });
}

$("#frmRegistro").submit(function(e){ 
  e.preventDefault();
  $.ajax({
    type: "POST",
    url: "Registro",
    data: $(this).serialize(),
    dataType: "json",
    success: function (data) {
      if(data.code == "error"){
        msjError(data.mensaje);
      }else{
        console.log(data);
        showFactura(data);
      }
    }
  });
});

function showFactura(datos){
    debugger;
  let f = new Date(datos.fecha);
  $("#lFecha").html(f);
  $("#lPlaca").html(datos.placa);
  $("#lParqueo").html(datos.parqueo);
  $("#lTipo").html(datos.vehiculo);
  $("#lTarifa").html(datos.tarifa + "/hora");
  $("#lUsuario").html(datos.usuario);
  $("#lPlaca").html(datos.placa);
  $("#modelId").modal("show");
}


