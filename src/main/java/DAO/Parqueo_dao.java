
package DAO;

import Config.Conexion;
import Interfaces.Parqueo_i;
import Modelo.Parqueo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parqueo_dao implements Parqueo_i {
  Parqueo pa = new Parqueo();
  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st = null;
  
  @Override
  public List<Parqueo> listar() {
    List<Parqueo> lista = new ArrayList<Parqueo>();
    String query = "SELECT * FROM parqueo";
    try {
      con =cn.getConexion();
      ps=con.prepareStatement(query);
      rs =ps.executeQuery();
      while(rs.next()){
        Parqueo p = new Parqueo();       
        p.setId(rs.getInt("id"));
        p.setEstado(rs.getString("estado"));
        p.setCodigo(rs.getString("codigo"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setNivel(rs.getInt("nivel"));
        lista.add(p);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      return lista;
  }

  @Override
  public Parqueo list(int id) {
    Parqueo p = new Parqueo();
    String query = "SELECT * FROM parqueo WHERE id="+id;
    try {
      con =cn.getConexion();
      ps=con.prepareStatement(query);
      rs =ps.executeQuery();
      while(rs.next()){
        p.setId(rs.getInt("id"));
        p.setCodigo(rs.getString("codigo"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setNivel(rs.getInt("nivel"));
        p.setEstado(rs.getString("estado"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    return p;
  }

  @Override
  public boolean agregar(Parqueo pa) {
    String query= "INSERT INTO parqueo(codigo,descripcion,nivel) VALUES('"+pa.getCodigo()+"','"+pa.getDescripcion()+"', "+pa.getNivel()+")";
    boolean r = false;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      ps.executeUpdate();
      r = true;
    } catch (SQLException e) {
   } catch (ClassNotFoundException ex) {
          Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
   }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        return r;
  }

  @Override
  public boolean editar(Parqueo pa) {
    boolean res;
    String query = "UPDATE parqueo SET codigo='"+pa.getCodigo()+"', descripcion='"+pa.getDescripcion()+"', nivel="+pa.getNivel()+", estado='"+pa.getEstado()+"' WHERE id="+pa.getId();
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      ps.executeUpdate();
      res = true;
    } catch (ClassNotFoundException e) {
      System.out.print(e.getMessage());
      res = false;
    } catch (SQLException e) {
      System.out.print(e.getMessage());
      res = false;
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    return res;
  }

  @Override
  public ArrayList<Parqueo> getParkin() {
    ArrayList<Parqueo> list = new ArrayList<Parqueo>();
    String sql = "SELECT id, codigo FROM parqueo WHERE estado='disponible'";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        Parqueo p = new Parqueo();
        p.setId(rs.getInt("id"));
        p.setCodigo(rs.getString("codigo"));
        list.add(p);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    return list;
  }

  @Override
  public boolean ocupar(int id) {
    boolean r;
    String sql ="UPDATE parqueo SET estado='ocupado'  WHERE id="+id;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
       ps.executeUpdate();
       r = true;
    } catch (Exception e) {
      r = false;
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    return r;
  }

  @Override
  public boolean desocupar(int id) {
    boolean r;
    String sql ="UPDATE parqueo SET estado='disponible'  WHERE id="+id;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
       ps.executeUpdate();
       r = true;
    } catch (Exception e) {
      r = false;
    }finally{
          try {
            con.close();
          } catch (SQLException ex) {
            Logger.getLogger(Parqueo_dao.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    return r;
  }
}
