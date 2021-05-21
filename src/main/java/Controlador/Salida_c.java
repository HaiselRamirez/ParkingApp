package Controlador;

import DAO.Parqueo_dao;
import DAO.Registro_dao;
import DAO.Salida_dao;
import Modelo.Salida;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Salida_c extends HttpServlet {

  Registro_dao regDao = new Registro_dao();
  Parqueo_dao parDao = new Parqueo_dao();
  Salida_dao dao = new Salida_dao();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String act = request.getParameter("accion");
    if ("cobrar".equals(act)) {
      cobrar(request, response);
    } else if ("detalles".equals(act)) {
      detalleRegistro(request, response);
    } else if ("listar".equals(act)) {
      listarSalida(request, response);
    } else if ("cobrado".equals(act)) {
      cobradoPorUsuario(request, response);
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

  private void cobrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Salida sal = new Salida();
    Map<String, String> map = new HashMap<String, String>();
    int usuario = (int) request.getSession().getAttribute("id");
    int id_parqueo = Integer.parseInt(request.getParameter("tIdParqueo"));
    int id_registro = Integer.parseInt(request.getParameter("tIdRegistro"));
    sal.setCosto(Double.parseDouble(request.getParameter("tDeuda")));
    sal.setDevuelta(Double.parseDouble(request.getParameter("tDevuelta")));
    sal.setPagado(Double.parseDouble(request.getParameter("tPago")));
    sal.setRegistro(id_registro);
    sal.setUsuario(usuario);
    sal.setObs(request.getParameter("tObserva"));
    boolean add = dao.agregar(sal);
    boolean fin = regDao.terminar(id_registro);
    boolean des = parDao.desocupar(id_parqueo);
    if (add == true && fin == true && des == true) {
      map.put("code", "success");
      map.put("mensaje", "Todos listo!");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se registró el pago, vuelva a intentarlo");
    }
    responderJson(map, response);
  }

  private void listarSalida(HttpServletRequest request, HttpServletResponse response) {
      
  }

  private void detalleRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> map;
    String placa = request.getParameter("placa");
    map = regDao.detallesRegistro(placa);
    responderJson(map, response);
  }

  private void responseJson(List<Salida> list, HttpServletResponse response) throws IOException {
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

  private double calculoHora(String fechaReg) {
    Date hoy = new Date();
    Date reg = new Date(fechaReg);
    long dif;
    dif = hoy.getTime() - reg.getTime();
    long hora = dif / (1000 * 60 * 60);
    return (double) hora;
  }

  private void cobradoPorUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Double cobrado = dao.cobradoPorUsuario(id);
    Map<String, String> map =new HashMap<String,String>();
    map.put("total", Double.toString(cobrado));
    responderJson(map, response);
  }
}
