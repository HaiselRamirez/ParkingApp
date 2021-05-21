<%
  if(session.getAttribute("login")!= "on"){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ParkingApp | Login</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <img src="dist/img/logo.PNG" alt="" class="img-responsive" width="120">
    <h3> <strong class="text-orange">Parking</strong>App</h3>
  </div>
  <!-- /.login-logo -->
  <div class="card" style="background: rgba(0,0,0,.6);">
    <div class="card-body login-card-body" style="background: rgba(0,0,0,.6);">
      <p class="login-box-msg text-white">Ingresa los datos para iniciar sesión</p>
      <form action="Login" method="post" id="frmLogin">
        <div class="input-group mb-2">
          <input type="text" class="form-control" name="tUser" id="tUser" placeholder="Usuario">
          <div class="input-group-append bg-warning">
            <div class="input-group-text">
              <span class="fas fa-user text-dark"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" class="form-control" name="tPass" id="tPass" placeholder="Contraseña">
          <div class="input-group-append bg-warning">
            <div class="input-group-text">
              <span class="fas fa-lock-open text-dark"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <button type="submit" class="btn btn-warning btn-block mb-3">
              <i class="fas fa-sig-in-alt"></i>
              <strong>ENTRAR</strong>
            </button>
            <a href="#" class="text-warning font-width-bold">Olvidaste la clave?</a>
            <span id="date" class="d-none"></span>
          </div>
        </div>
      </form>
      
    </div>
    <!-- /.login-card-body -->
  </div>
</div>
<!-- /.login-box -->

<!-- js -->
<script src="plugins/jquery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="plugins/sweetalert2/sweetalert2.all.min.js"></script>
<script src="dist/js/adminlte.min.js"></script>
<script src="dist/js/funciones.js"></script>
<script>
$(function(){
   $("#frmLogin").submit(function(e){
    e.preventDefault();
    let datos = {
      "accion": "entrar",
      "user": $("#tUser").val().toString(),
      "pass": $("#tPass").val().toString()
    }
    if(datos.clave =="" || datos.clave==""){
       msjError("todos los campos son necesarios");
    }else{
      $.ajax({
        type: "POST",
        url: "Login",
        data: datos,
        dataType: "json",
        success: function (re) {
          if(re.code == "error"){
            msjError(re.mensaje);
            $("#tUser").focus();
          }else{
            msjSuccess(re.mensaje + " " +re.nombre);
          }
        }
      });
    }
  });
});
</script>
</body>
</html>
<%}else{
response.sendRedirect("index.jsp");
}%>