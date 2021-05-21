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
        <h1> <i class="fas fa-car"></i> Vehiculos</h1>
      </div>
      <div class="col-sm-6">
      </div>
    </div>
  </div><!-- /.container-fluid -->
</section>
<section class="content">
  <!-- Tabla que presenta los usuarios de la base de datos -->
  <div class="row mt-3" id="divTblVehiculos">
    <div class="col-8">
      <div class="card">
        <div class="card-header">
          <h4 class="card-title"><i class="fas fa-list"></i> Lista de los tipos de vehiculos</h4>
        </div>
        <div class="card-body">
          <div class="table-responsive w-100">
             <table class="table table-sm table-hover" id="tblVehiculos">
               <thead class="bg-dark">
                <tr>
                  <th>Vehiculo</th>
                  <th>Descripcion</th>
                  <th>Tarifa</th>
                  <th>Estado</th>
                  <th><i class="fas fa-pen-alt"></i></th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="col-4">
      <div class="card">
        <div class="card-header bg-dark">
          <h4 class="card-title">NUEVO TIPO DE VEHICULO</h4>
        </div>
        <div class="card-body">
          <form action="Vehiculo" id="fAddVehiculo" method="POST">
            <input type="hidden" name="accion" value="agregar">
            <div class="form-group mb-3">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-car"></i>
                  </span>
                </div>
                <input type="text" class="form-control" name="tTipo" id="tTipo" placeholder="Vehiculo" required>
              </div>
            </div>
            <div class="form-group mb-3">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-info-circle"></i>
                  </span>
                </div>
                <input type="text" class="form-control" name="tDescripcion" id="tDescripcion" placeholder="Descripcion" required>
              </div>
            </div>
            <div class="form-group mb-3">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-dollar-sign"></i>
                  </span>
                </div>
                <input type="number" class="form-control" name="tTarifa" id="tTarifa" placeholder="Tarifa" required>
              </div>
            </div>
            <div class="form-group">
              <div class="row">
                <div class="col-6">
                  <button class="btn btn-block bg-black" type="submit">
                    <i class="fas fa-save"></i> GUARDAR
                  </button>
                </div>
                <div class="col-6">
                  <button class="btn btn-block bg-gray" type="reset">
                    <i class="fas fa-times"></i> CANCELAR
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
<!-- Fin del div-->
</section>

<!-- Modal -->
<div class="modal fade" id="mEditaVehiculo" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header bg-dark">
        <h5 class="modal-title">Editar el tipo de vehiculo</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fas fa-power-off text-white"></i></span>
          </button>
      </div>
      <form action="Vehiculo" method="POST" id="fEditaVehiculo">
        <div class="modal-body">
          <div class="form-group mb-3">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-car"></i>
                </span>
              </div>
              <input type="text" class="form-control" name="t_Tipo" id="t_Tipo" required>
            </div>
          </div>
          <div class="form-group mb-3">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-info-circle"></i>
                </span>
              </div>
              <input type="text" class="form-control" name="t_Descripcion" id="t_Descripcion" required>
            </div>
          </div>
          <div class="form-group mb-3">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-dollar-sign"></i>
                </span>
              </div>
              <input type="number" class="form-control" name="t_Tarifa" id="t_Tarifa" required>
            </div>
          </div>
          <div class="form-group mb-3">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-check-circle"></i>
                </span>
              </div>
              <select name="t_Estado" id="t_Estado" class="form-control" required>
                <option value="true">Permitido</option>
                <option value="false">No permitido</option>
              </select>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="t_Id" id="t_Id">
          <input type="hidden" name="accion" value="editar">
          <button type="submit" class="btn btn-dark"> Guardar Cambios</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal"> Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<%@include file="footer.jsp" %>
  <script src="dist/js/vehiculos.js"></script>
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

