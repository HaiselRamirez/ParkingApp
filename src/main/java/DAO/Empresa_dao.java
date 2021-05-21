package DAO;

import Config.Conexion;
import Interfaces.Empresa_i;
import Modelo.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Empresa_dao implements Empresa_i {
  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st;
  
  
  @Override
  public ArrayList<Empresa> listar() {
    ArrayList<Empresa> list = new ArrayList();
    String sql ="SELECT * FROM empresa";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        Empresa em = new Empresa();
        em.setId(rs.getInt("id"));
        em.setNombre(rs.getString("nombre"));
        em.setSlogan(rs.getString("slogan"));
        em.setDescripcion(rs.getString("descripcion"));
        em.setLogo(rs.getString("logo"));
        em.setEmail(rs.getString("email"));
        em.setTelefono(rs.getString("telefono"));
        em.setRnc(rs.getString("rnc"));
        em.setDireccion(rs.getString("direccion"));
        em.setWeb(rs.getString("web"));
        list.add(em);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return list;
  }

  @Override
  public Empresa list(int id) {
    String sql ="SELECT * FROM empresa WHERE id="+id;
    Empresa em = new Empresa();
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()){
        em.setId(rs.getInt("id"));
        em.setNombre(rs.getString("nombre"));
        em.setSlogan(rs.getString("slogan"));
        em.setDescripcion(rs.getString("descripcion"));
        em.setLogo(rs.getString("logo"));
        em.setEmail(rs.getString("email"));
        em.setTelefono(rs.getString("telefono"));
        em.setRnc(rs.getString("rnc"));
        em.setDireccion(rs.getString("direccion"));
        em.setWeb(rs.getString("web"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return em;
  }

  @Override
  public boolean agregar(Empresa em) {
    boolean resp = false;
    String sql = "INSERT INTO empresa(nombre,slogan,descripcion,logo,email,telefono,rnc,direccion,web) VALUES(?,?,?,?,?,?,?,?,?)";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      ps.setString(1, em.getNombre());
      ps.setString(2, em.getSlogan());
      ps.setString(3, em.getDescripcion());
      ps.setString(4, em.getLogo());
      ps.setString(5, em.getEmail());
      ps.setString(6, em.getTelefono());
      ps.setString(7, em.getRnc());
      ps.setString(8, em.getDireccion());
      ps.setString(9, em.getWeb());
      if(ps.executeUpdate() > 0){
        resp = true;
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return resp;
  }

  @Override
  public boolean editar(Empresa em) {
    boolean resp = false;
    String sql = "UPDATE empresa SET nombre=?, slogan=?, descripcion=?, logo=?, email=?, telefono=?, rnc=?, direccion=?, web=? WHERE id=?";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      ps.setString(1, em.getNombre());
      ps.setString(2, em.getSlogan());
      ps.setString(3, em.getDescripcion());
      ps.setString(4, em.getLogo());
      ps.setString(5, em.getEmail());
      ps.setString(6, em.getTelefono());
      ps.setString(7, em.getRnc());
      ps.setString(8, em.getDireccion());
      ps.setString(9, em.getWeb());
      ps.setInt(10, em.getId());
      if(ps.executeUpdate() > 0){
        resp = true;
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return resp;
  }
  
}
