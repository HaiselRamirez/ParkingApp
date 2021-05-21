
package Modelo;

import java.sql.Timestamp;


public class Salida {
  int id;
  int registro;
  Timestamp fecha;
  double costo;
  double pagado;
  double devuelta;
  String obs;
  int usuario;

  public Salida() {
  }

  public Salida(int id, int registro, Timestamp fecha, double costo, double pagado, double devuelta, String obs, int usuario) {
    this.id = id;
    this.registro = registro;
    this.fecha = fecha;
    this.costo = costo;
    this.pagado = pagado;
    this.devuelta = devuelta;
    this.obs = obs;
    this.usuario = usuario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRegistro() {
    return registro;
  }

  public void setRegistro(int registro) {
    this.registro = registro;
  }

  public Timestamp getFecha() {
    return fecha;
  }

  public void setFecha(Timestamp fecha) {
    this.fecha = fecha;
  }


  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }

  public double getPagado() {
    return pagado;
  }

  public void setPagado(double pagado) {
    this.pagado = pagado;
  }

  public double getDevuelta() {
    return devuelta;
  }

  public void setDevuelta(double devuelta) {
    this.devuelta = devuelta;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }

  public int getUsuario() {
    return usuario;
  }

  public void setUsuario(int usuario) {
    this.usuario = usuario;
  }
  
}
