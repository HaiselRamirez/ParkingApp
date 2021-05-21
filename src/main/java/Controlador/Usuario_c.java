package Controlador;

import DAO.Usuario_dao;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Usuario_c", urlPatterns = {"/Usuario"})
public class Usuario_c extends HttpServlet {

  Usuario us = new Usuario();
  Usuario_dao dao = new Usuario_dao();
  Map<String, String> map = new HashMap<String, String>();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.sendRedirect("404.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String consul = request.getParameter("accion");
    if("agregar".equals(consul)){
      agregarUsuario(request, response);
    }else if("editar".equals(consul)){
      editarUsuario(request, response);
    }else if("list".equals(consul)){
      unUsuario(request, response);
    }else if("cambiar".equals(consul)){
      cambiarClave(request, response);
    }else{
      listarUsuarios(request, response);
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    us.setUsername(request.getParameter("tUsername"));
    us.setPassword(request.getParameter("tPassword"));
    us.setNombre(request.getParameter("tNombre"));
    us.setCorreo(request.getParameter("tCorreo"));
    us.setCargo(request.getParameter("tCargo"));
    boolean add = dao.agregar(us);
    if (add == true) {
      map.put("code", "success");
      map.put("mensaje", "Usuario agregado correctamente");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se pudo agregar el nuevo usuario");
    }
    responderJson(map, response);
  }

  private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String estado = request.getParameter("t_Estado");
    if (estado.equalsIgnoreCase("1")) {
      us.setEstado(true);
    } else {
      us.setEstado(false);
    }
    us.setUsername(request.getParameter("t_Username"));
    us.setPassword(request.getParameter("t_Password"));
    us.setNombre(request.getParameter("t_Nombre"));
    us.setCorreo(request.getParameter("t_Correo"));
    us.setCargo(request.getParameter("t_Cargo"));
    us.setId(Integer.parseInt(request.getParameter("t_Id")));
    boolean add = dao.editar(us);
    if (add == true) {
      map.put("code", "success");
      map.put("mensaje", "Los cambios se guardaron correctamente");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se pudo editar información del usuario");
    }
    responderJson(map, response);
  }

  private void unUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String ide = request.getParameter("id");
    Map<String, String> m = new HashMap<String, String>();
    if (ide != null) {
      int id = parseInt(ide);
      Usuario u;
      u = dao.list(id);
      m.put("id", Integer.toString(u.getId()));
      m.put("username", u.getUsername());
      m.put("password", u.getPassword());
      m.put("nombre", u.getNombre());
      m.put("correo", u.getCorreo());
      m.put("cargo", u.getCargo());
      m.put("estado", String.valueOf(u.isEstado()));
    } else {
      m.put("code", "error");
      m.put("mensaje", "El usuario no se encuentra en la base de datos");
    }
    responderJson(m, response);
  }

  private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<Usuario> list;
    list = dao.listar();
    responseJson(list, response);
  }

  private void cambiarClave(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String clave = request.getParameter("tClaveNueva");
    int id = Integer.parseInt(request.getParameter("id"));
    boolean add = dao.cambiarClave(id, clave);
    if (add == true) {
      map.put("code", "success");
      map.put("mensaje", "La clave fue cambiada correctamente");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se pudo cambiar la contraseña");
    }
    responderJson(map, response);
  }

  private void responseJson(List<Usuario> list, HttpServletResponse response) throws IOException {
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
