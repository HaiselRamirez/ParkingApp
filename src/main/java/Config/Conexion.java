package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion{
  String database="parking_app";
  String user="root";
  String pass="";
  String url="jdbc:mysql://localhost:3306/"+database;
  Connection conn;


  public Conexion(){
   
  }

  public Connection getConexion() throws ClassNotFoundException{
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(url,user, pass);
    } catch (SQLException e) {
      System.err.print("Error de conexion " + e.getMessage());
    }
    return conn;
  }
}