package DAO;

import Interfaces.Vehiculo_i;
import Modelo.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vehiculo_dao implements Vehiculo_i {

  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st = null;
  Vehiculo ve = new Vehiculo();

  @Override
  public List<Vehiculo> listar() throws SQLException {
    List<Vehiculo> list = new ArrayList<Vehiculo>();
    String query = "SELECT * FROM tipo_auto";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        Vehiculo v = new Vehiculo();
        v.setId(rs.getInt("id"));
        v.setTipo(rs.getString("tipo"));
        v.setDescripcion(rs.getString("descripcion"));
        v.setTarifa(rs.getDouble("tarifa"));
        v.setEstado(rs.getBoolean("estado"));
        list.add(v);
      }
    } catch (ClassNotFoundException e) {
      list = null;
    } catch (SQLException e) {
      list = null;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return list;
  }

  @Override
  public Vehiculo list(int id) {
    String query = "SELECT * FROM tipo_auto WHERE id=" + id;
    Vehiculo v = new Vehiculo();
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        v.setId(rs.getInt("id"));
        v.setTipo(rs.getString("tipo"));
        v.setDescripcion(rs.getString("descripcion"));
        v.setTarifa(rs.getDouble("tarifa"));
        v.setEstado(rs.getBoolean("estado"));
      }
    } catch (ClassNotFoundException e) {
      v = null;
    } catch (SQLException e) {
      v = null;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return v;
  }

  @Override
  public boolean agregar(Vehiculo ve) {
    boolean resp;
    String query = "INSERT INTO tipo_auto (tipo, descripcion, tarifa) VALUES(?,?,?)";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      ps.setString(1, ve.getTipo());
      ps.setString(2, ve.getDescripcion());
      ps.setDouble(3, ve.getTarifa());
      int i = ps.executeUpdate();
      resp = i != 0;
    } catch (ClassNotFoundException e) {
      resp = false;
    } catch (SQLException e) {
      resp = false;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return resp;
  }

  @Override
  public boolean editar(Vehiculo ve) {
    boolean resp;
    String query = "UPDATE tipo_auto SET tipo=?, descripcion=?, tarifa=?, estado=? WHERE id=" + ve.getId();
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      ps.setString(1, ve.getTipo());
      ps.setString(2, ve.getDescripcion());
      ps.setDouble(3, ve.getTarifa());
      ps.setBoolean(4, ve.isEstado());
      int i = ps.executeUpdate();
      resp = i != 0;
    } catch (ClassNotFoundException e) {
      resp = false;
    } catch (SQLException e) {
      resp = false;
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return resp;
  }

  @Override
  public ArrayList<Vehiculo> getVehiculo() {
    ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
    String sql = "SELECT id, tipo FROM tipo_auto WHERE estado=" + 1;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Vehiculo ve = new Vehiculo();
        ve.setId(rs.getInt("id"));
        ve.setTipo(rs.getString("tipo"));
        lista.add(ve);
      }
    } catch (Exception e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Vehiculo_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return lista;
  }

}
