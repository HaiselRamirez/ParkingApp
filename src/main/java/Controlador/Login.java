package Controlador;

import DAO.Usuario_dao;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

  Usuario us = new Usuario();
  Usuario_dao dao = new Usuario_dao();
  HttpSession session;
  Map<String, String> map = new HashMap<String, String>();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.sendRedirect("login.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String act;
    act = request.getParameter("accion");
    if (act != null) {
      if ("entrar".equals(act)) {
        entrar(request, response);
      } else if ("salir".equals(act)) {
        salir(request, response);
      }
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void entrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");
    us = dao.validar(user, pass);
    if (us.getUsername() != null) {
      if (us.isEstado()) {
        session = request.getSession();
        session.setAttribute("login", "on");
        session.setAttribute("id", us.getId());
        session.setAttribute("nombre", us.getNombre());
        session.setAttribute("user", us.getUsername());
        session.setAttribute("clave", us.getPassword());
        session.setAttribute("cargo", us.getCargo());
        session.setAttribute("correo", us.getCorreo());
        map.put("code", "success");
        map.put("mensaje", "Bienvenid@ a ParkingApp");
        map.put("nombre", us.getNombre());
      } else {
        map.put("code", "error");
        map.put("mensaje", "Usuario deshabilitado, favor contactar el administrador");
      }
    } else {
      map.put("code", "error");
      map.put("mensaje", "Usuario y/o contraseña incorrecta");
    }
    responderJson(map, response);
  }

  private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException {
    session = request.getSession();
    session.setAttribute("login", "off");
    session.setAttribute("id", null);
    session.setAttribute("nombre", null);
    session.setAttribute("user", null);
    session.setAttribute("clave", null);
    session.setAttribute("cargo", null);
    session.setAttribute("correo", null);
    map.put("code", "success");
    map.put("mensaje", "hasta luego");
    responderJson(map, response);
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
