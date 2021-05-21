package DAO;

import Config.Conexion;
import Interfaces.Usuario_i;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario_dao implements Usuario_i {

  Usuario u = new Usuario();
  Conexion cn = new Conexion();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  Statement st = null;

  /**
   *
   * @return
   */
  @Override
  public List<Usuario> listar() {
    List<Usuario> list = new ArrayList<Usuario>();
    String query = "SELECT * FROM usuario";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        Usuario us = new Usuario();
        us.setId(rs.getInt("id"));
        us.setUsername(rs.getString("username"));
        us.setPassword(rs.getString("password"));
        us.setNombre(rs.getString("nombre"));
        us.setCorreo(rs.getString("correo"));
        us.setCargo(rs.getString("cargo"));
        us.setEstado(rs.getBoolean("estado"));
        list.add(us);
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return list;
  }

  @Override
  public Usuario list(int id) {
    Usuario user = new Usuario();
    String query = "SELECT * FROM usuario WHERE id=" + id;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setNombre(rs.getString("nombre"));
        user.setCorreo(rs.getString("correo"));
        user.setCargo(rs.getString("cargo"));
        user.setEstado(rs.getBoolean("estado"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return user;
  }

  @Override
  public boolean agregar(Usuario u) {
    String query = "INSERT INTO usuario(username,password,nombre,correo,cargo) VALUES('" + u.getUsername() + "','" + u.getPassword() + "', '" + u.getNombre() + "','" + u.getCorreo() + "', '" + u.getCargo() + "')";
    boolean r = false;
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      ps.executeUpdate();
      r = true;
    } catch (SQLException e) {

    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return r;
  }

  @Override
  public boolean editar(Usuario u) {
    boolean res = false;
    String query = "UPDATE usuario SET password='" + u.getPassword() + "', nombre='" + u.getNombre() + "', correo='" + u.getCorreo() + "', cargo='" + u.getCargo() + "', estado=" + u.isEstado() + " WHERE id=" + u.getId();
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
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return res;
  }

  @Override
  public Usuario validar(String user, String pass) {
    Usuario us = new Usuario();
    String query = "SELECT * FROM usuario WHERE username='" + user + "' AND password='" + pass + "'";
    try {
      con = cn.getConexion();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        us.setId(rs.getInt("id"));
        us.setUsername(rs.getString("username"));
        us.setPassword(rs.getString("password"));
        us.setNombre(rs.getString("nombre"));
        us.setCorreo(rs.getString("correo"));
        us.setCargo(rs.getString("cargo"));
        us.setEstado(rs.getBoolean("estado"));
      }
    } catch (ClassNotFoundException e) {
    } catch (SQLException e) {
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return us;
  }

  @Override
  public boolean cambiarClave(int id, String clave) {
    boolean res;
    String query = "UPDATE usuario SET password='" + clave + "' WHERE id=" + id;
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
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Usuario_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return res;
  }

}
