
package Controlador;

import Modelo.Vehiculo;
import com.google.gson.Gson;

import DAO.Vehiculo_dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vehiculo_c extends HttpServlet {
  Vehiculo ve = new Vehiculo();
  Vehiculo_dao dao = new Vehiculo_dao();
  Map<String, String> map = new HashMap<String, String>();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, SQLException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String act = request.getParameter("accion");
    if ("agregar".equals(act)) {
      agregarVehiculo(request, response);
    } else if ("editar".equalsIgnoreCase(act)) {
      editarVehiculo(request, response);
    } else if ("list".equalsIgnoreCase(act)) {
      unVehiculo(request, response);
    } else if("get".equalsIgnoreCase(act)) {
      getVehiculos(request, response);
    }else{
      try {
        listarVehiculos(request, response);
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_c.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private void listarVehiculos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    List<Vehiculo> lista = new ArrayList();
    lista = dao.listar();
    responseJson(lista, response);
  }

  private void unVehiculo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String ide = request.getParameter("id");
    Map<String, String> m = new HashMap<String, String>();
    if(ide != null){
      int id = Integer.parseInt(ide);
      Vehiculo v = new Vehiculo();
      v = dao.list(id);
      m.put("id", Integer.toString(v.getId()));
      m.put("tipo", v.getTipo());
      m.put("descripcion", v.getDescripcion());
      m.put("tarifa" , Double.toString(v.getTarifa()));
      m.put("estado", Boolean.toString(v.isEstado()));
    }else{
      m.put("code", "error");
      m.put("mensaje", "Error al buscar el vehiculo");
    }
    responderJson(m, response);
  }

  private void editarVehiculo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String tipo = request.getParameter("t_Tipo");
    Vehiculo v = new Vehiculo();
    v.setId(Integer.parseInt(request.getParameter("t_Id")));
    v.setTipo(request.getParameter("t_Tipo"));
    v.setDescripcion(request.getParameter("t_Descripcion"));
    v.setTarifa(Double.parseDouble(request.getParameter("t_Tarifa")));
    v.setEstado(Boolean.parseBoolean(request.getParameter("t_Estado")));
    boolean edit = dao.editar(v);
    if(edit == true){
        map.put("code", "success");
        map.put("mensaje", "Datos del tipo de vehiculo editado correctamente");
      }else{
        map.put("code", "error");
        map.put("mensaje", "Ocurrió un error, no se editó el tipo de vehiculo");
      }
      responderJson(map, response);
  }

  private void agregarVehiculo(HttpServletRequest request, HttpServletResponse response) throws IOException {
      ve.setTipo(request.getParameter("tTipo"));
      ve.setDescripcion(request.getParameter("tDescripcion"));
      ve.setTarifa(Double.parseDouble(request.getParameter("tTarifa")));
      boolean r = dao.agregar(ve);
      if(r == true){
        map.put("code", "success");
        map.put("mensaje", "Vehiculo agregado correctamente");
      }else{
        map.put("code", "error");
        map.put("mensaje", "Ocurrió un error, no se agrego el vehiculo");
      }
      responderJson(map, response);
  }
  
  private void getVehiculos(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<Vehiculo> lista = new ArrayList();
    lista = dao.getVehiculo();
    responseJson(lista, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void responseJson(List<Vehiculo> list, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(list);
    PrintWriter printer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    printer.write(json);
    printer.close();
  }

  private void responderJson(Map<String, String> map, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(map);
    PrintWriter printer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    printer.write(json);
    printer.close();
  }

  
}
