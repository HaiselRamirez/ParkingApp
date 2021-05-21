<%
  if("on".equals(session.getAttribute("login"))){
%>
<style>
  @media screen {
  #printSection {
      display: none;
  }
}

@media print {
  body * {
    visibility:hidden;
  }
  #printSection, #printSection * {
    visibility:visible;
  }
  #printSection {
    position:absolute;
    left:0;
    top:0;
  }
}
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1> <i class="fas fa-edit"></i> NUEVO REGISTRO</h1>
          </div>
          <div class="col-sm-6">
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
    <section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-body">
              <form action="Registro" method="POST" id="frmRegistro">
                <div class="row">
                  <div class="col-12">
                    <input type="hidden" name="accion" value="registrar">
                    <div class="form-group">
                      <label for="tPlaca">PLACA:</label>
                      <input type="text" class="form-control tPlaca" id="tPlaca" name="tPlaca" autofocus required maxlength="8" style="height:80px;font-weight:bold;font-size:60px;text-align:center;color:blue;text-transform:uppercase;">
                    </div>
                  </div>
                  <div class="col-3">
                    <div class="form-group">
                      <label for="tAuto">Tipo de Vehiculo</label>
                      <select name="tAuto" id="tAuto" class="form-control sl-auto">
                        <option value="">Seleccione...</option>
                      </select>
                    </div>
                  </div>
                  <div class="col-3">
                    <div class="form-group">
                      <label for="tParqueo">Parqueo:</label>
                      <select name="tParqueo" id="tParqueo" class="form-control sl-parking">
                      </select>
                    </div>
                  </div>
                  <div class="col-3">
                    <div class="form-group">
                      <label for="tTarifa">Tarifa por hora</label>
                      <input type="text" id="tTarifa" name="tTarifa" class="form-control" readonly>
                    </div>
                  </div>
                  <div class="col-3">
                    <div class="form-group">
                      <label for="tUser">Usuario</label>
                      <input type="text"  class="form-control" readonly value="${nombre}">
                      <input type="hidden" id="tUser" name="tUser" value="${id}">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-6">
                    <i class="fa fa-calendar-alt" aria-hidden="true"></i>
                    <span id="date1"></span>
                  </div>
                  <div class="col-3">
                    <button class="btn btn-block btn-success btn-lg" type="submit">
                      <i class="fas fa-edit"></i>
                      <span> REGISTRAR</span>
                    </button>
                  </div>
                  <div class="col-3">
                    <button class="btn btn-block btn-danger btn-lg" type="reset">
                      <i class="fas fa-times"></i>
                      <span> CANCELAR</span>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    <!-- Modal -->
    <div id="printThis">
      <div class="modal fade" id="modelId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-body">
            <div class="text-center">
              <h2>MyParking</h2>
              <small>Estamos para servirte, siempre!</small><br>
              <small>C/ Juan Pablo Duarte #10, Santo Domingo.</small>
              <small>Tel.:8096582989</small>
              <hr class="bg-dark" />
            </div>
            <table class="table table-sm">
              <h4 class="text-center">Ticket de servicio</h4>
              <tbody>
                <tr>
                  <td class="text-right">Fecha</td>
                  <td> <strong id="lFecha"></strong></td>
                </tr>
                <tr>
                  <td class="text-right">Placa</td>
                 <td> <strong id="lPlaca"></strong></td>
                </tr>
                <tr>
                  <td class="text-right">Vehiculo</td>
                 <td> <strong id="lTipo"></strong></td>
                </tr>
                <tr>
                  <td class="text-right">Parqueo</td>
                 <td> <strong id="lParqueo"></strong></td>
                </tr>
                <tr>
                  <td class="text-right">Tarifa</td>
                 <td> <strong id="lTarifa"></strong></td>
                </tr>
              </tbody>
            </table>
            <p class="text-center">
              <small >Presente este ticket cuando este realizando el pago, No hacetamos billetes de $500 en adelante <br> <b>Gracias por preferirnos</b></small>
            </p>
            <p>Le atendió: <b id="lUsuario"></b></p>
          </div>
        </div>
      </div>
    </div>
    </div>

  <%@include file="footer.jsp"%>
  <script src="dist/js/registro.js"></script>
  </body>
</html>

<%}else{
response.sendRedirect("login.jsp");
}%>
