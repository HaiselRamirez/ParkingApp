$(function(){
  getData(); 
});

function getData(){
  $("#tblVehiculos").dataTable({
    info:false,
    ordering:false,
    lengthMenu: [ 5, 10, 25 ],
    language:{
      url: "dist/Spanish.json"
    },
    ajax:{
      type: "POST",
      url: "Vehiculo",
      dataType: "json",
      dataSrc:""
    },
    columns:[
      {data:"tipo"},
      {data:"descripcion"},
      {data:"tarifa"},
      {data:"estado"},
      {orderable:true,
        render:function(data,type,row){
          return `
            <button class="btn btn-flat btn-sm btn-info rounded-circle" data-toggle="modal" data-target="#mEditaVehiculo" onclick="getVehiculo(${row.id});">
              <i class="fas fa-pencil-alt"></i>
            </button>
          `;
        }
      }
    ],
    columnDefs:[
      {
        targets: 0,
        render:function(data,type,row){
          return `<strong class="text-primary">${data}</strong>`;
        }
      },
      {
        targets: 2,
        render:function(data,type, row){
          return `<span class="text-success">RD$:${data}</span>`;
        }
      },
      {
        targets:3,
        render:function(data,type,row){
          if(data == true){
            return `<span class="badge badge-success">Permitido</span>`;
          }else{
            return `<span class="badge badge-danger">No permitido</span>`;
          }
        }
      }
    ]
  });
}

function getVehiculo(id){
  let datos = {"accion": "list", "id":id};
  $.ajax({
    type: "POST",
    url: "Vehiculo",
    data: datos,
    dataType: "json",
    success: function (d) {
      console.log(d);
      $("#t_Id").val(d.id);
      $("#t_Tipo").val(d.tipo);
      $("#t_Descripcion").val(d.descripcion);
      $("#t_Tarifa").val(d.tarifa);
      $("#t_Estado").val(d.estado);
    }
  });
}
$("#fAddVehiculo").submit(function (e) { 
  e.preventDefault();
  $.ajax({
    type: $(this).attr('method'),
    url: $(this).attr('action'),
    data: $(this).serialize(),
    dataType: "json",
    success: function (r) {
      if(r.code =="error"){
        msjError(r.mensaje);
      }else{
        msjSuccess(r.mensaje);
      }
    }
  });
});

$("#fEditaVehiculo").submit(function (e) { 
  e.preventDefault();
  $.ajax({
    type: $(this).attr('method'),
    url: $(this).attr('action'),
    data: $(this).serialize(),
    dataType: "json",
    success: function (res) {
      if(res.code =="error"){
        msjError(res.mensaje);
      }else{
        msjSuccess(res.mensaje);
      }
    }
  });
});



