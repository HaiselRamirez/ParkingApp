
package Modelo;

import java.util.Date;

public class Registro {
  int id;
  String placa;
  int tipo;
  int parqueo;
  Date fecha;
  int usuario;
  boolean estado;

  public Registro() {
  }

  public Registro(int id, String placa, int tipo, int parqueo, Date fecha, int usuario, boolean estado) {
    this.id = id;
    this.placa = placa;
    this.tipo = tipo;
    this.parqueo = parqueo;
    this.fecha = fecha;
    this.usuario = usuario;
    this.estado = estado;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public int getParqueo() {
    return parqueo;
  }

  public void setParqueo(int parqueo) {
    this.parqueo = parqueo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getUsuario() {
    return usuario;
  }

  public void setUsuario(int usuario) {
    this.usuario = usuario;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }
}
