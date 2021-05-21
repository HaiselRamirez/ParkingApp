/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Conexion;
import Interfaces.Salida_i;
import Modelo.Salida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author haise
 */
public class Salida_dao implements Salida_i {
  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st = null;

  @Override
  public ArrayList<Salida> listar() {
    String sql = "SELECT * FROM salida";
    ArrayList<Salida> list = new ArrayList<Salida>();
    try {
     con = cn.getConexion();
     ps = con.prepareStatement(sql);
     rs = ps.executeQuery();
     while(rs.next()){
       Salida sal = new Salida();
       sal.setId(rs.getInt("id"));
       sal.setRegistro(rs.getInt("registro"));
       sal.setCosto(rs.getDouble("costo"));
       sal.setPagado(rs.getDouble("pagado"));
       sal.setDevuelta(rs.getDouble("devuelta"));
       sal.setFecha(rs.getTimestamp("fecha_registro"));
       sal.setObs(rs.getString("observaciones"));
       sal.setUsuario(rs.getInt("usuario"));
       list.add(sal);
     }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return list;
  }

  @Override
  public Salida list(int id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean agregar(Salida sa) {
    String sql = "INSERT INTO salida(registro, costo, pagado, devuelta, observaciones, usuario)VALUES("+sa.getRegistro()+","+sa.getCosto()+","+sa.getPagado()+","+sa.getDevuelta()+",'"+sa.getObs()+"',"+sa.getUsuario()+")";
    boolean resp;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      int i = ps.executeUpdate();
      resp = i != 0;
    } catch (ClassNotFoundException e) {
      resp = false;
    } catch (SQLException e) {
      resp = false;
    }
    return resp;
  }

  @Override
  public boolean editar(Salida sa) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public ArrayList<Salida> salidaUsuario(int user) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public ArrayList<Salida> salidaFecha(Date fecha) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Map<String, String> detalleSalida(int id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Double cobradoPorUsuario(int id) {
    Double cobrado = 0.0;
    String sql= "SELECT sum(costo) as total FROM salida WHERE usuario=" + id;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs=ps.executeQuery();
      while(rs.next()){
        cobrado = rs.getDouble("total");
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    }
    return cobrado;
  }
  
}
