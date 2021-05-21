
package Controlador;
import DAO.Parqueo_dao;
import Modelo.Parqueo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Parqueo_c extends HttpServlet {
  Parqueo pa = new Parqueo();
  Parqueo_dao dao = new Parqueo_dao();
  Map<String,String> map = new HashMap<String, String>();
  List list = null;
  

  
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
    String ac = request.getParameter("accion");
    if("agregar".equals(ac)){
      agregarParqueo(request, response);
    }else if("editar".equals(ac)){
      editarParqueo(request, response);
    }else if("list".equals(ac)){
      unParqueo(request, response);
    }else if("get".equals(ac)){
      getParking(request, response);
    }else if("listar".equals(ac)){
      listarParqueos(request, response);
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void listarParqueos(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<Parqueo>lista;
    lista = dao.listar();
    responseJson(lista, response);
  }

  private void agregarParqueo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    pa.setCodigo(request.getParameter("tCodigo"));
    pa.setDescripcion(request.getParameter("tDescripcion"));
    pa.setNivel(Integer.parseInt(request.getParameter("tNivel")));
    pa.setEstado(request.getParameter("tEstado"));
    boolean add = dao.agregar(pa);
    if (add == true) {
      map.put("code", "success");
      map.put("mensaje", "Parqueo agregado correctamente");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se pudo agregar el parqueo");
    }
    responderJson(map, response);
  }

  private void editarParqueo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    pa.setCodigo(request.getParameter("t_Codigo"));
    pa.setId(Integer.parseInt(request.getParameter("t_Id")));
    pa.setDescripcion(request.getParameter("t_Descripcion"));
    pa.setNivel(Integer.parseInt(request.getParameter("t_Nivel")));
    pa.setEstado(request.getParameter("t_Estado"));
    boolean add = dao.editar(pa);
    if (add == true) {
      map.put("code", "success");
      map.put("mensaje", "Los cambios se guardaron correctamente");
    } else {
      map.put("code", "error");
      map.put("mensaje", "No se pudo editar información del parqueo");
    }
    responderJson(map, response);
  }

  private void unParqueo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String ide = request.getParameter("id");
    Map<String, String> m = new HashMap<String, String>();
    if (ide != null) {
      int id = parseInt(ide);
      Parqueo p;
      p = dao.list(id);
      m.put("id", Integer.toString(p.getId()));
      m.put("codigo", p.getCodigo());
      m.put("descripcion", p.getDescripcion());
      m.put("nivel", Integer.toString(p.getNivel()));
      m.put("estado", p.getEstado());
    } else {
      m.put("code", "error");
      m.put("mensaje", "El parqueo no se encuentra en la base de datos");
    }
    responderJson(m, response);
  }
  
  
  
  
  private void getParking(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> m = new HashMap<String, String>();
    ArrayList<Parqueo> lista;
    lista = dao.getParkin();
    if(lista.size()>0){
      responseJson(lista, response);
    }else{
      m.put("code","error");
      m.put("mensaje","No hay parqueos disponibles");
      responderJson(m, response);
    }
  }
  
  private void responseJson(List<Parqueo> list, HttpServletResponse response) throws IOException {
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
