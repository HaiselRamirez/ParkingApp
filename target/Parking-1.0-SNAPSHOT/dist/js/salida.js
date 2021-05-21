$(function(){
  $("#tReg").change(function (e) {
    e.preventDefault(); 
    let placa =  $(this).val();
    let largo =placa.length;
    let datos ={"accion":"detalles", "placa": placa};
    if(largo > 6){
      $.ajax({
        type: "post",
        url: "Salida",
        data: datos,
        dataType: "json",
        success: function (data) {
          if(data.id > 0){
            cargarInput(data);
          }else{
            msjError("No existe un registro activo con esa placa");
            limpiar();
          }
        }
      });
    }
  });

  $("#tPago").change(function(e){
    e.preventDefault();
    let deuda = $("#tDeuda").val();
    let pago = $(this).val();
    if(deuda >parseInt(pago)){
      msjError("Su deuda es mayor que lo pagado");
    }else{
      $("#tDevuelta").val(pago-deuda);
    }
  });
});

$("#frmSalida").submit(function (e) { 
  e.preventDefault();
  $.ajax({
    type: "POST",
    url: "Salida",
    data: $(this).serialize(),
    dataType: "json",
    success: function (da) {
      if(da.code == "error"){
        msjError(da.mensaje);
      }else{
        msjSuccess("da.mensaje");
      }
    }
  });
});


function cargarInput(data){
  let deuda = calcularDeuda(data.fecha, data.tarifa);
  let id_registro = data.id;
  let id_parqueo = data.id_parqueo;
  $("#tDeuda").val(Math.round(deuda));
  $("#tIdRegistro").val(id_registro);
  $("#tIdParqueo").val(id_parqueo);
}



function limpiar(){
  $("#tDeuda").val('');
  $("#tIdRegistro").val('');
  $("#tIdParqueo").val('');
  $("#tReg").val('');
  $("#tPago").val('');
  $("#tDevuelta").val('');
  $("#tObserva").val('');
  $("#tReg").focus();
}

function calcularDeuda(fecha, tarifa){
  var horaMils = 1000*60*60,
      desde = new Date(fecha),
      hoy = new Date(),
      hasta = new Date(hoy);
      dif = hasta.getTime() - desde.getTime() +horaMils,
      deuda =Math.round(tarifa * (dif/horaMils));
  return deuda;
  
}