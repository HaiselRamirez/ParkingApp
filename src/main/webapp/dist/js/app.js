$(function(){
  cobradoPorUsuario();
  parqueados();
  cantUsuarios();
});

$("#tblDetalles").dataTable({
  info:false,
  ordering:false,
  language:{
    url: 'dist/Spanish.json'
  },
  ajax:{
    type: "POST",
    url: "Registro",
    data: {"accion": "detalle"},
    dataSrc:""
  },
  columns:[
    {data: 1},
    {data: 3},
    {data: 5},
    {data: 2},
    {data: 4},
    {data: 6},
  ],
  columnDefs:[
    {
      targets: 0,
      render:function(data,type,row){
        return `<strong class="text-dark">${data}</strong>`;
      }
    },
    {
      targets: 1,
      render:function(data,type,row){
        return `<strong class="text-dark">${data}</strong>`;
      }
    },
    {
      targets: 2,
      render:function(data,type,row){
        return `<strong class="text-dark">${data}</strong>`;
      }
    },
    {
      targets: 4,
      render:function(data,type,row){
        return `<strong class="text-success">RD$: ${data}.00</strong>`;
      }
    },
  ]
});

function cobradoPorUsuario(){
  let idActivo = $("#tSessionID").val();
  let datos = {"accion": "cobrado", "id": idActivo};
  $.ajax({
    type:"POST",
    url: "Salida",
    data:datos,
    dataType:"json",
    success:function(r){
      $(".c-cobrado").html("$ "+r.total);
    }
  });
}

function parqueados(){
  let datos = {"accion": "regActivos"};
  $.ajax({
    type: "POST",
    url: "Registro",
    data: datos,
    dataType: "json",
    success: function (r) {
      let cantidad;
      if(r.length > 0){
        cantidad = r.length;
      }else{
        cantidad = 0;
      }
      $(".c-reg").html(cantidad);
    }
  });
}

function cantUsuarios(){
  $.ajax({
    type: "POST",
    url: "Usuario",
    dataType: "json",
    success: function (r) {
      let cant = r.length;
      $(".c-users").html(cant);
    }
  });
}