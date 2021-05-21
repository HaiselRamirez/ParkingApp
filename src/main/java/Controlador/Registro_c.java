package Controlador;

import DAO.Parqueo_dao;
import DAO.Registro_dao;
import Modelo.Registro;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registro_c extends HttpServlet {

  Registro reg = new Registro();
  Registro_dao dao = new Registro_dao();
  Map<String, String> map;
  Parqueo_dao pa = new Parqueo_dao();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String act = request.getParameter("accion");
    if ("registrar".equalsIgnoreCase(act)) {
      Registrar(request, response);
    } else if ("list".equalsIgnoreCase(act)) {
      unRegistro(request, response);
    } else if ("listar".equalsIgnoreCase(act)) {
      listarRegistros(request, response);
    } else if ("regActivos".equalsIgnoreCase(act)) {
      registrosActivos(request, response);
    } else if ("porFecha".equalsIgnoreCase(act)) {
      registrosHoy(request, response);
    } else if ("porUser".equalsIgnoreCase(act)) {
      registrosUser(request, response);
    } else if ("detalle".equalsIgnoreCase(act)) {
      detalleRegistros(request, response);
    }else {
      response.sendRedirect("404.jsp");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void Registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int parqueo = Integer.parseInt(request.getParameter("tParqueo"));
    int usuario = (int) request.getSession().getAttribute("id");
    map = new HashMap<String, String>();
    Map<String, String> idReg;
    reg.setPlaca(request.getParameter("tPlaca"));
    reg.setTipo(Integer.parseInt(request.getParameter("tAuto")));
    reg.setParqueo(parqueo);
    reg.setUsuario(usuario);
    int add = dao.registrar(reg);
    idReg = dao.detalleUnRegistro(add);
    if (add > 0) {
      if (pa.ocupar(parqueo)) {
        map = idReg;
        map.put("code", "success");
        map.put("mensaje", "Registro exitoso!");
      } else {
        map.put("code", "error");
        map.put("mensaje", "No se pudo realizar el registro");
      }
    }
    responderJson(map, response);
  }

  private void listarRegistros(HttpServletRequest request, HttpServletResponse response) throws IOException { 
    ArrayList<Registro> lista;
    lista = dao.listar();
    responseJson(lista, response);
  }

  private void registrosActivos(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<Registro> m;
    m = dao.regActivos();
    responseJson(m, response);
  }

  private void unRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    reg = dao.list(id);
    if (reg.getId() > 0) {
      map.put("id", Integer.toString(reg.getId()));
      map.put("placa", reg.getPlaca());
      map.put("tipo", Integer.toString(reg.getTipo()));
      map.put("parqueo", Integer.toString(reg.getParqueo()));
      map.put("fecha_registro", reg.getFecha().toString());
      map.put("usuario", Integer.toString(reg.getUsuario()));
      map.put("estado", Boolean.toString(reg.isEstado()));
    } else {
      map.put("code", "error");
      map.put("mensaje", "No existe usuario con ese ID");
    }
    responderJson(map, response);
  }

  private void registrosHoy(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Date hoy = new Date();
    ArrayList<Registro> lista;
    lista = dao.registroFecha(hoy);
    responseJson(lista, response);
  }


  private void registrosUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int user = (int) request.getSession().getAttribute("id");
    ArrayList<Registro> lista;
    lista = dao.registroUsuario(user);
    responseJson(lista, response);
  }

  private void responseJson(List<Registro> list, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(list);
    PrintWriter printer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    printer.write(json);
    printer.close();
  }
  
  private void json(ArrayList<Object> list, HttpServletResponse response) throws IOException {
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

  private void convertirJson(Map<Integer, ArrayList> map, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(map);
    PrintWriter printer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    printer.write(json);
    printer.close();
  }

  private void detalleRegistros(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<Object> list;
    list = dao.detallesRegistro();
    json(list, response);
  }
}
