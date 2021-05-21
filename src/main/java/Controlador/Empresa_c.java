
package Controlador;

import DAO.Empresa_dao;
import Modelo.Empresa;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Empresa_c extends HttpServlet {
    Empresa_dao dao = new Empresa_dao();
    Empresa emp = new Empresa();
  
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
    String act = request.getParameter("accion");
    if ("datos".equalsIgnoreCase(act)) {
      datosEmpresa(request, response);
    }else if("agregar". equalsIgnoreCase(act)){
      agregarEmpresa(request, response);
    }else if("editar".equalsIgnoreCase(act)){
      editarEmpresa(request, response);
    }else{
      response.sendRedirect("404.jsp");
    }
  }


  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void datosEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Map<String, String> map = new HashMap<String,String>();
    emp = dao.list(id);
    map.put("id", Integer.toString(emp.getId()));
    map.put("nombre", emp.getNombre());
    map.put("slogan", emp.getSlogan());
    map.put("descripcion", emp.getDescripcion());
    map.put("logo", emp.getLogo());
    map.put("email", emp.getEmail());
    map.put("telefono", emp.getTelefono());
    map.put("rnc", emp.getRnc());
    map.put("direccion", emp.getDireccion());
    map.put("web", emp.getWeb());
    
    responderJson(map, response);
  }

  private void editarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> map = new HashMap<String,String>();
    emp.setId(Integer.parseInt(request.getParameter("t_Id")));
    emp.setNombre(request.getParameter("t_Nombre"));
    emp.setSlogan(request.getParameter("t_Slogan"));
    emp.setDescripcion(request.getParameter("t_Descripcion"));
    emp.setLogo(request.getParameter("t_Logo"));
    emp.setEmail(request.getParameter("t_Email"));
    emp.setTelefono(request.getParameter("t_Telefono"));
    emp.setRnc(request.getParameter("t_Rnc"));
    emp.setDireccion(request.getParameter("t_Direccion"));
    emp.setWeb(request.getParameter("t_Web"));
    boolean add = dao.editar(emp);
    if(add){
      map.put("code","error");
      map.put("mensaje", "No se pudo agregar los datos de la empresa");
    }else{
      map.put("code","success");
      map.put("mensaje", "Los datos de la empresa fueron ingresados correctamente");
    }
    responderJson(map, response);
  }

  private void agregarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> map = new HashMap<String,String>();
    emp.setNombre(request.getParameter("tNombre"));
    emp.setSlogan(request.getParameter("tSlogan"));
    emp.setDescripcion(request.getParameter("tDescripcion"));
    emp.setLogo(request.getParameter("tLogo"));
    emp.setEmail(request.getParameter("tEmail"));
    emp.setTelefono(request.getParameter("tTelefono"));
    emp.setRnc(request.getParameter("tRnc"));
    emp.setDireccion(request.getParameter("tDireccion"));
    emp.setWeb(request.getParameter("tWeb"));
    boolean add = dao.agregar(emp);
    if(add){
      map.put("code","error");
      map.put("mensaje", "No se pudo agregar los datos de la empresa");
    }else{
      map.put("code","success");
      map.put("mensaje", "Los datos de la empresa fueron ingresados correctamente");
    }
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
  
  private void responseJson(List<Empresa> list, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(list);
    PrintWriter printer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    printer.write(json);
    printer.close();
  }

}
