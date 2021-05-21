<%
  if("on".equals(session.getAttribute("login"))){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
    <section class="content">
      <div class="row mt-4">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-primary">
            <div class="inner">
              <h3 class="c-reg">0</h3>
              <p>Vehiculos parqueados</p>
            </div>
            <div class="icon">
              <i class="fas fa-car"></i>
            </div>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-navy">
            <div class="inner">
              <h3 class="c-parking"></h3>
              <p>Parqueos Disponibles</p>
            </div>
            <div class="icon">
              <i class="fas fa-parking"></i>
            </div>
          </div>
        </div>
        <div class="col-lg-3 col-xs-6">
          <div class="small-box bg-orange">
            <div class="inner">
              <h3 class="c-users">0</h3>
              <p>Usuarios</p>
            </div>
            <div class="icon">
              <i class="fa fa-users"></i>
            </div>
          </div>
        </div>
        <div class="col-lg-3 col-xs-6">
          <div class="small-box bg-green">
            <div class="inner">
              <h3 class="c-cobrado">0.00</h3>
              <p>Pesos cobrado</p>
            </div>
            <div class="icon">
              <i class="fas fa-donate"></i>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title"> Detalles de los registros activos</h3>
            </div>
            <div class="card-body">
              <table class="table table-sm table-bordered" id="tblDetalles">
                <thead class="bg-dark">
                  <tr class="text-center">
                    <th>Placa</th>
                    <th>Vehiculo</th>
                    <th>Parqueo</th>
                    <th>Fecha</th>
                    <th>Tarifa</th>
                    <th>Usuario</th>
                  </tr>
                </thead>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>
<%@include file="footer.jsp"%>
<script src="dist/js/app.js"></script>
  </body>
</html>

<%}else{
response.sendRedirect("login.jsp");
}%>