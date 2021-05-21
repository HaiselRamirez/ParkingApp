<%
  if("on".equals(session.getAttribute("login")))
  {
    if("admin".equals(session.getAttribute("cargo"))){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<section class="content-header">
  <div class="container-fluid">
    <div class="row mb-2">
      <div class="col-sm-6">
        <h1> <i class="fas fa-store"></i> Datos de la empresa</h1>
      </div>
      <div class="col-sm-6">
      </div>
    </div>
  </div><!-- /.container-fluid -->
</section>
<section class="content">
  <div class="row" id="div1">
    <div class="col-12">
      <div class="card">
        <div class="card-header bg-dark">
          <h3 class="card-title">
            <i class="fas fa-info"></i>
             Información detallada de la empresa
          </h3>
        </div>
        <div class="card-body">
          <div class="row">
            <div class="col-12 col-md-6">
              <dl class="">
                <dt>Nombre:</dt>
                <dd><span id="de_nombre"></span></dd>
                <dt>Slogan</dt>
                <dd><span id="de_sloga"></span></dd>
                <dt>Descripción</dt>
                <dd><span id="de_desc"></span></dd>
                <dt>Dirección</dt>
                <dd><span id="de_direc"></span></dd>
                <dt>Logotipo</dt>
                <dd><span id="de_logo"></span></dd>
              </dl>
            </div>
            <div class="col-12 col-md-6">
              <dl class="dl-horizontal">
                <dt>Email</dt>
                <dd><span id="de_email"></span></dd>
                <dt>Telefono</dt>
                <dd><span id="de_tel"></span></dd>
                <dt>Propietario</dt>
                <dd><span id="de_dueno"></span></dd>
                <dt>RNC</dt>
                <dd><span id="de_rnc"></span></dd>
                <dt>Fundación</dt>
                <dd><span id="de_fecha"></span></dd>
                <dt>Pagina Web</dt>
                <dd><span id="de_web"></span></dd>
              </dl>
            </div>
          </div>
        </div>
        <div class="card-footer">
          <div class="row">
            <div class="col-3 ml-auto">
              <button class="btn btn-block btn-dark btn-flat" onclick="editEmpresa();">
                <i class="fas fa-edit"></i>
                Editar 
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="footer.jsp" %>
  </body>
</html>
<%
    }else{
      response.sendRedirect("404.jsp");
    }
}else{
  response.sendRedirect("login.jsp");
}
%>
