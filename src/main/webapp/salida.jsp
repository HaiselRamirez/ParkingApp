<%
  if("on".equals(session.getAttribute("login"))){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1> <i class="fas fa-hand-holding-usd"></i> COBRAR SERVICIO</h1>
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
              <form action="Salida" method="POST" id="frmSalida">
                <div class="row">
                  <div class="col-12">
                    <input type="hidden" name="accion" value="cobrar">
                    <input type="hidden" name="tIdRegistro" id="tIdRegistro">
                    <input type="hidden" name="tIdParqueo" id="tIdParqueo">
                    <div class="form-group">
                      <label for="tReg">PLACA:</label>
                      <input type="text" class="form-control" id="tReg" name="tReg" autofocus required maxlength="8" style="height:80px;font-weight:bold;font-size:60px;text-align:center;color:blue;text-transform:uppercase;">
                    </div>
                  </div>
                  <div class="col-2">
                    <div class="form-group">
                      <label for="tDeuda">Deuda:</label>
                      <input type="number" class="form-control" id="tDeuda" name="tDeuda" placeholder="Deuda" readonly>
                    </div>
                  </div>
                  <div class="col-2">
                    <div class="form-group">
                      <label for="tPago">Pago</label>
                      <input type="number" class="form-control" name="tPago" id="tPago" placeholder="Pago">
                    </div>
                  </div>
                  
                  <div class="col-2">
                    <div class="form-group">
                      <label for="tDevuelta">Devuelta</label>
                      <input type="number" id="tDevuelta" name="tDevuelta" class="form-control" readonly placeholder="Devuelta">
                    </div>
                  </div>
                  <div class="col-6">
                    <div class="form-group">
                      <label for="tObserva">Observaciones</label>
                      <textarea name="tObserva" id="tObserva" cols="5" rows="3" class="form-control"></textarea>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-6">
                    <i class="fa fa-calendar-alt" aria-hidden="true"></i>
                    <span id="date1"></span>
                  </div>
                  <div class="col-3">
                    <button class="btn btn-block btn-dark btn-lg" type="submit">
                      <i class="fas fa-hand-holding-usd"></i>
                      <span> COBRAR</span>
                    </button>
                  </div>
                  <div class="col-3">
                    <button class="btn btn-block btn-warning btn-lg" type="reset">
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
  <%@include file="footer.jsp"%>
  <script src="dist/js/salida.js"></script>
  </body>
</html>

<%}else{
response.sendRedirect("login.jsp");
}%>