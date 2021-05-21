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
        <h1> <i class="fas fa-parking"></i> Mantenimiento de los Parqueos</h1>
      </div>
      <div class="col-sm-6">
      </div>
    </div>
  </div><!-- /.container-fluid -->
</section>

<section class="content">
  <!-- Tabla que presenta los usuarios de la base de datos -->
  <div class="row mt-3" id="divTblPrqueos">
    <div class="col-12">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col-9">
              <h4 class="card-title"><i class="fas fa-list"></i> Lista de los parqueos</h4>
            </div>
            <div class="col-3">
              <button class="btn btn-block btn-dark" data-toggle="modal" data-target="#mAgregaParqueo">
              <i class="fas fa-plus"></i> NUEVO  PARQUEO
              </button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <div class="table-responsive w-100">
             <table class="table table-sm table-hover" id="tblParqueos">
               <thead class="bg-dark">
                <tr>
                  <th>Codigo</th>
                  <th>Descripcion</th>
                  <th>Nivel</th>
                  <th>Estado</th>
                  <th><i class="fas fa-edit"></i></th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- Fin del div-->
</section>

<!-- Modal -->
<div class="modal fade" id="mAgregaParqueo" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header py-2 text-center bg-dark">
        <h5 class="modal-title"><i class="fas fa-plus"></i> Agregar un nuevo parqueo</h5>
      </div>
      <form action="Parqueo" method="POST" id="fAgregaParqueo">
        <div class="modal-body">
          <div class="container-fluid">
            <div class="form-group row">
              <label for="tCodigo" class="col-3">Código:</label>
              <div class="col-6">
                <input type="text" class="form-control" id="tCodigo" name="tCodigo" placeholder="Codigo" required/>
              </div>
            </div>
            <div class="form-group row">
              <label for="tDescripcion" class="col-3">Descripción:</label>
              <div class="col-9">
                <input type="text" class="form-control" id="tDescripcion" name="tDescripcion" placeholder="Descripcion"/>
              </div>
            </div>
            <div class="form-group row">
              <label for="tNivel" class="col-3">Nivel:</label>
              <div class="col-9">
                <select name="tNivel" id="tNivel" class="form-control" required>
                  <option value="0">Seleccione...</option>
                  <option value="1">Primer</option>
                  <option value="2">Segundo</option>
                  <option value="3">Tercero</option>
                  <option value="4">Cuarto</option>
                  <option value="5">Quinto</option>
                </select>
              </div>
            </div>
            <div class="form-group row">
              <label for="tOcupado" class="col-3">Estado:</label>
              <div class="col-9">
                <select name="tOcupado" id="tOcupado" class="form-control" required>
                  <option value="disponible">Disponible</option>
                  <option value="ocupado">Ocupado</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="accion" value="agregar">
          <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Agregar</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal"> <i class="fas fa-times"></i>Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="mEditaParqueo" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header py-2 text-center bg-dark">
        <h5 class="modal-title"><i class="fas fa-edit"></i> Editar informacion del parqueo</h5>
      </div>
      <form action="Parqueo" method="POST" id="fEditaParqueo">
        <div class="modal-body">
          <div class="container-fluid">
            <div class="form-group row">
              <label for="t_Codigo" class="col-3">Código:</label>
              <div class="col-6">
                <input type="text" class="form-control" id="t_Codigo" name="t_Codigo"/>
              </div>
            </div>
            <div class="form-group row">
              <label for="t_Descripcion" class="col-3">Descripción:</label>
              <div class="col-9">
                <input type="text" class="form-control" id="t_Descripcion" name="t_Descripcion"/>
              </div>
            </div>
            <div class="form-group row">
              <label for="t_Nivel" class="col-3">Nivel:</label>
              <div class="col-9">
                <select name="t_Nivel" id="t_Nivel" class="form-control" required>
                  <option value="0">Seleccione...</option>
                  <option value="1">Primer</option>
                  <option value="2">Segundo</option>
                  <option value="3">Tercero</option>
                  <option value="4">Cuarto</option>
                  <option value="5">Quinto</option>
                </select>
              </div>
            </div>
            <div class="form-group row">
              <label for="t_Estado" class="col-3">Estado:</label>
              <div class="col-9">
                <select name="t_Estado" id="t_Estado" class="form-control" required>
                  <option value="disponible">Disponible</option>
                  <option value="ocupado">Ocupado</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="accion" value="editar">
          <input type="hidden" name="t_Id" id="t_Id">
          <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Guardar cambios</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal"> <i class="fas fa-times"></i>Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>


<%@include file="footer.jsp" %>
  <script src="dist/js/parqueo.js"></script>
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