$(function(){
  getData();
});



$("#fAgregaParqueo").submit(function (e) { 
  e.preventDefault();
  $.ajax({
    type: $(this).attr('method'),
    url: $(this).attr('action'),
    data: $(this).serialize(),
    dataType: "json",
    success: function (data) {
      if(data.code == "error"){
        msjSuccess(re.mensaje);
      }else if(data.code == "success"){
        msjError(re.mensaje);
      }
    }
  });
});


$("#fEditaParqueo").submit(function(e){
  e.preventDefault();
  $.ajax({
    type: $(this).attr('method'),
    url: $(this).attr('action'),
    data: $(this).serialize(),
    dataType: "json",
    success: function (re) {
      if(re.code =="success"){
        msjSuccess(re.mensaje)
      }else{
        msjError(re.mensaje);
      }
    }
  });
});

function getData(){
  let datos = {"accion":"listar"};
  let da;
  $.ajax({
    type: "POST",
    url: "Parqueo",
    data: datos,
    dataType: "json",
    success: function (data) {
      da = data;
    $("#tblParqueos").dataTable({
      info: false,
      ordering: false,
      language:{
        url:"dist/Spanish.json"
      },
      data:da,
      columns:[
        {data: "codigo"},
        {data: "descripcion"},
        {data: "nivel"},
        {data: "estado"},
        {orderable:true,
          render:function(data,type,row){
            return `
              <button class="btn rounded-circle btn-info btn-sm" onclick="oneParking(${row.id})" data-toggle="modal" data-target="#mEditaParqueo">
                <i class="fas fa-pen"></i>
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
          targets:1,
          render:function(data,type, row) {
            return `<span class="text-dark">${data}</span>`;
          }
        },
        {
          targets:2,
          render:function(data,type, row) {
            switch(data){
              case 1:
                return `<span class="text-dark">Primer</span>`;
                break;
              case 2:
                return `<span class="text-dark">Segundo</span>`;
                break;
              case 3:
                return `<span class="text-dark">Tercer</span>`;
                break;
              case 4:
                return `<span class="text-dark">Cuarto</span>`;
                break;
              default:
                return `<span class="text-dark">Quinto</span>`;
                break;
            }
          }
        },
        {
          targets:3,
          render:function(data,type, row){
            if(data == "disponible"){
              return `<span class="badge badge-success">Disponible</span>`;
            }else{
              return `<span class="badge badge-warning">Ocupado</span>`;
            }
          }

        }

      ]
    });
  }
});
}

function oneParking(id){
  let dato = {"id":id, "accion":"list"};
  $.ajax({
    type: "POST",
    url: "Parqueo",
    data: dato,
    dataType: "json",
    success:function (r){
      $("#t_Id").val(r.id);
      $("#t_Codigo").val(r.codigo);
      $("#t_Descripcion").val(r.descripcion);
      $("#t_Nivel").val(r.nivel);
      $("#t_Estado").val(r.estado);
    }
  });
}


