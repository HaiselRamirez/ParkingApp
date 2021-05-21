package DAO;

import Config.Conexion;
import Interfaces.Registro_i;
import Modelo.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Registro_dao implements Registro_i {

  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st;
  Registro reg = new Registro();

 
  @Override
  public ArrayList<Registro> listar() {
    ArrayList<Registro> lista = new ArrayList<Registro>();
    String sql = "SELECT * FROM registro";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Registro r = new Registro();
        r.setId(rs.getInt("id"));
        r.setPlaca(rs.getString("placa"));
        r.setTipo(rs.getInt("tipo"));
        r.setParqueo(rs.getInt("parqueo"));
        r.setFecha(rs.getDate("fecha_registro"));
        r.setUsuario(rs.getInt("usuario"));
        r.setEstado(rs.getBoolean("estado"));
        lista.add(r);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return lista;
  }

  @Override
  public Registro list(int id) {
    String sql = "SELECT * FROM registro WHERE id=" + id;
    Registro r = new Registro();
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        r.setId(rs.getInt("id"));
        r.setPlaca(rs.getString("placa"));
        r.setTipo(rs.getInt("tipo"));
        r.setParqueo(rs.getInt("parqueo"));
        r.setFecha(rs.getDate("fecha_registro"));
        r.setUsuario(rs.getInt("usuario"));
        r.setEstado(rs.getBoolean("estado"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return r;
  }

  @Override
  public int registrar(Registro re) {
    int resp =0;
    String sql = "INSERT INTO registro (placa, tipo, parqueo,usuario) VALUES('" + re.getPlaca() + "'," + re.getTipo() + "," + re.getParqueo() + "," + re.getUsuario() + ")";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      int row = ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      if(rs.next()){
        resp = rs.getInt(1);
      }
    } catch (ClassNotFoundException e) {
      resp = 0;
    } catch (SQLException ex) {
      Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      resp = 0;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return resp;
  }

  @Override
  public ArrayList<Registro> registroUsuario(int user) {
    ArrayList<Registro> lista = new ArrayList<Registro>();
    String sql = "SELECT * FROM registro WHERE usuario=" + user;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Registro r = new Registro();
        r.setId(rs.getInt("id"));
        r.setPlaca(rs.getString("placa"));
        r.setTipo(rs.getInt("tipo"));
        r.setParqueo(rs.getInt("parqueo"));
        r.setFecha(rs.getDate("fecha_registro"));
        r.setUsuario(rs.getInt("usuario"));
        r.setEstado(rs.getBoolean("estado"));
        lista.add(r);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return lista;
  }

  @Override
  public ArrayList<Registro> registroFecha(Date fecha) {
    ArrayList<Registro> list = new ArrayList<Registro>();
    String sql = "SELECT * FROM registro WHERE fecha_registro='" + fecha + "'";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Registro r = new Registro();
        r.setId(rs.getInt("id"));
        r.setPlaca(rs.getString("placa"));
        r.setTipo(rs.getInt("tipo"));
        r.setParqueo(rs.getInt("parqueo"));
        r.setFecha(rs.getDate("fecha_registro"));
        r.setUsuario(rs.getInt("usuario"));
        r.setEstado(rs.getBoolean("estado"));
        list.add(r);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return list;
  }

  @Override
  public boolean terminar(int i) {
    boolean resp;
    String sql = "UPDATE registro SET estado=0 WHERE id="+i;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      ps.executeUpdate();
      resp = true;
    } catch (ClassNotFoundException e) {
      resp = false;
    } catch (SQLException e) {
      resp = false;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Registro_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return resp;
  }

  @Override
  public Map<String,String> detallesRegistro(String placa) {
    Map<String,String> map = new HashMap<String,String>();
    String query = "SELECT r.id, r.fecha_registro, t.tarifa, p.id as id_parqueo FROM registro as r JOIN tipo_auto as t ON t.id=r.tipo JOIN parqueo as p ON p.id=r.parqueo WHERE r.placa='"+placa+"' AND r.estado="+1;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while(rs.next()){
        LocalDateTime fecha = rs.getTimestamp("fecha_registro").toLocalDateTime();
        map.put("id", Integer.toString(rs.getInt("id")));
        map.put("fecha", fecha.toString());
        map.put("tarifa", Double.toString(rs.getDouble("tarifa")));
        map.put("id_parqueo", Integer.toString(rs.getInt("id_parqueo")));
      }
    } catch (ClassNotFoundException e) {
      map.put("error",e.getMessage());
    } catch (SQLException e) {
      map.put("error",e.getMessage());
    }
    return map;
  }

  @Override
  public ArrayList<Registro> regActivos() {
    ArrayList<Registro> li = new ArrayList<Registro>();
    String sql ="SELECT * FROM registro WHERE estado=1";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        Registro r = new Registro();
        r.setId(rs.getInt("id"));
        r.setPlaca(rs.getString("placa"));
        r.setParqueo(rs.getInt("parqueo"));
        r.setFecha(rs.getTimestamp("fecha_registro"));
        r.setEstado(rs.getBoolean("estado"));
        r.setUsuario(rs.getInt("usuario"));
        r.setTipo(rs.getInt("tipo"));
        li.add(r);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return li;
  }

  @Override
  public ArrayList<Object> detallesRegistro() {
    ArrayList<Object> lista = new ArrayList();
    String sql ="SELECT r.id, r.placa, r.fecha_registro, t.tipo, t.tarifa, p.codigo, u.username FROM registro as r JOIN tipo_auto as t ON t.id=r.tipo JOIN parqueo as p ON p.id=r.parqueo JOIN usuario as u ON u.id=r.usuario WHERE r.estado=1";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        Object[] obj = new Object[7];
        obj[0] = rs.getInt("id");
        obj[1] = rs.getString("placa");
        obj[2] = rs.getTimestamp("fecha_registro");
        obj[3] = rs.getString("tipo");
        obj[4] = rs.getDouble("tarifa");
        obj[5] = rs.getString("codigo");
        obj[6] = rs.getString("username");
        lista.add(obj);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return lista;
  }

  @Override
  public Map<String, String> detalleUnRegistro(int id) {
    Map<String, String> map = new HashMap<String, String>();
   String sql ="SELECT r.id, r.placa, r.fecha_registro, t.tipo, t.tarifa, p.descripcion, u.nombre FROM registro as r JOIN tipo_auto as t ON t.id=r.tipo JOIN parqueo as p ON p.id=r.parqueo JOIN usuario as u ON u.id=r.usuario WHERE r.id="+id;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        map.put("id", Integer.toString(rs.getInt("id")));
        map.put("placa", rs.getString("placa"));
        map.put("vehiculo", rs.getString("tipo"));
        map.put("tarifa",Double.toString(rs.getDouble("tarifa")));
        map.put("parqueo",rs.getString("descripcion"));
        map.put("fecha",rs.getTimestamp("fecha_registro").toString());
        map.put("usuario",rs.getString("nombre"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return map;
  }
}
