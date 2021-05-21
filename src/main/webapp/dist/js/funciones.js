
$(function(){
  
  iniciar ();
});

function iniciar (){
  startTime();
  getParking();
  getAutos();
}

function startTime() {
  var hoy = new Date();
  var meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Augosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
  var dias = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
  var dia = dias[hoy.getDay()];
  var nomDia = hoy.getDate();
  var mes = meses[hoy.getMonth()];
  var anio = hoy.getFullYear();
  var fecha = dia+", "+nomDia+" "+mes+" "+anio;
  $("#date, #date1").html(fecha)
  var time = setTimeout(function(){ startTime() }, 1000);
};

$("#btnSalir").click(function(e){
  Swal.fire({
    title: 'Quiere salir del sistema?',
    showCancelButton: true,
    confirmButtonText: `Si, Salir!`,
    cancelButtonText: `No`
  }).then((result) => {
    if (result.isConfirmed) {
      cerrarSesion();
    }
  });
});

function salir(){

  
}

function cerrarSesion(){
  $.ajax({
    type: "POST",
    url: "Login",
    data: {"accion": "salir"},
    dataType: "json",
    success: function (d) {
      if(d.code == "success"){
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Hasta luego!',
          showConfirmButton: false,
          timer: 1500
        }).then((result)=>{
          if (result.dismiss === Swal.DismissReason.timer) {
            location.reload();
          }
        });
      }else{
        console.log(d);
      }
    }
  });
}

function cambiarClave(){
  let id = $("tId").val();
  let cVieja = $("tClaveVieja").val().toString();
  let cNueva = $("tClaveNueva").val().toString();
  let cConfirma = $("tClaveConfirma").val().toString();

  if(cVieja.length==0 || cNueva.length==0 || cConfirma.length==0){
    msg("Todos los campos son requeridos");
  }else if(cNueva != cConfirma){
    msg("Las contraseña nueva y la confirmacion no coinciden");
  }
}

function msjSuccess(mensaje){
  Swal.fire({
    position: 'center',
    icon: 'success',
    title:"ParkingApp",
    text: mensaje,
    showConfirmButton: false,
    timer: 2000
  }).then((result)=>{
    if (result.dismiss === Swal.DismissReason.timer) {
      location.reload();
    }
  })
}

function msjError(mensaje){
  Swal.fire({
    position: 'center',
    icon: 'error',
    title:"ParkingApp",
    text: mensaje,
    showConfirmButton: false,
    timer: 2000
  })
}

function getParking(){
  $.ajax({
    type: "POST",
    url: "Parqueo",
    data:{"accion":"get"},
    dataType: "json",
    success: function (data) {
      if(data.code =="error"){
        $(".c-parking").html("0");
        $(".sl-parking").append(`
          <option value="">Todos los parqueos ocupados</option>
        `);
      }else{
        let cant = data.length;
        $(".c-parking").html(cant);
        $.each(data, function (i, v) { 
          $(".sl-parking").append(`
            <option value="${v.id}">${v.codigo}</option>
          `); 
        });
      }
    }
  });
}

function getAutos(){
  $.ajax({
    type: "POST",
    url: "Vehiculo",
    data: {"accion": "get"},
    dataType: "json",
    success: function (data) {
      let cantidad = data.length;
      $(".c-auto").html(cantidad);
      $.each(data, function (i, v) { 
        $(".sl-auto").append(`
          <option value="${v.id}">${v.tipo}</option>
        `); 
      });
    }
  });
}


function datosEmpresa(){
  let datos = {"id": 1, "accion": "datos"};
  $.ajax({
    type: "POST",
    url: "Empresa",
    data: datos,
    dataType: "json",
    success: function (r) {
      
    }
  });
}




