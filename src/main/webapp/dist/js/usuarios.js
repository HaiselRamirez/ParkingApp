$(function(){
  getData();
})

function cambioDiv(){
  $("#divTblUsuarios").toggleClass('d-none');
  $("#divFormUsuarios").toggleClass('d-none');
}

function cambio2Div(){
  $("#divTblUsuarios").toggleClass('d-none');
  $("#divEditUsuarios").toggleClass('d-none');
}

$("#frmEditUsuario").submit(function(e){
  e.preventDefault();
  $.ajax({
    type: "POST",
    url: "Usuario",
    data: $(this).serialize(),
    dataType: "json",
    success: function (data) {
      msg(data);     
    }
  });
});
function getData(){
  $("#tblUsuarios").dataTable({
    ordering: false,
    language:{
      url: 'dist/Spanish.json'
    },
    ajax:{
      type: "POST",
      url: "Usuario",
      dataType: "json",
      dataSrc:""
    },
    columns:[
      {data:"username"},
      {data:"nombre"},
      {data:"correo"},
      {data:"cargo"},
      {data:"estado"},
      {orderable:true,
        render:function(data,type,row){
          return `
            <button class="btn btn-flat btn-sm btn-info rounded-circle" onclick="editarUsuarios(${row.id});">
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
        targets: 3,
        render:function(data,type,row){
          if(data =="admin"){
            return `<span class="text-primary"><i class="fas fa-user-cog"></i> Administrador</span>`;
          }else{
            return `<span class="text-info"><i class="fas fa-user-tag"></i> Empleado</span>`;
          }
        }
      },
      {
        targets:4,
        render:function(data,type,row){
          if(data ==true){
            return `<span class="badge badge-success"><i class="fas fa-user-check"></i> Activo</span>`;
          }else{
            return `<span class="badge badge-danger"><i class="fas fa-user-lock"></i> Inactivo</span>`;
          }
          
        }
      },
    ]

  });
}

$("#frmAddUsuario").submit(function(e){
  e.preventDefault();
  $.ajax({
    type: "POST",
    url: "Usuario",
    data: $(this).serialize(),
    dataType: "json",
    success: function (data) {
      msg(data);     
    }
  });
});

function editarUsuarios(id){
  let datos = {"id":id,"accion": "list"};
  $.ajax({
    type: "POST",
    url: "Usuario",
    data: datos,
    dataType: "json",
    success: function (da) {
      cargarFrmEditar(da);
    }
  });
}

function cargarFrmEditar(da){
  $("#t_Id").val(da.id);
  $("#t_Username").val(da.username);
  $("#t_Password").val(da.password);
  $("#t_Nombre").val(da.nombre);
  $("#t_Correo").val(da.correo);
  $("#t_Cargo").val(da.cargo);
  if(da.estado == "true"){
    $("#t_Estado").val(1);
  }else{
    $("#t_Estado").val(0);
  }
  cambio2Div();
}

function msg(data){
  const Toast = Swal.mixin({
    toast: true,
    position: 'center',
    showConfirmButton: false,
    timer: 4000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  });
  Toast.fire({
    icon: data.code,
    title: data.mensaje
  }).then((result) => {
    if (result.dismiss === Swal.DismissReason.timer) {
      location.reload();
    }
  })
}

