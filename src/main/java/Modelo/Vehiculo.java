
package Modelo;


public class Vehiculo {
  int id;
  String tipo;
  String descripcion;
  double tarifa;
  boolean estado;

  public Vehiculo() {
  }

  public Vehiculo(int id, String tipo, String descripcion, double tarifa, boolean estado) {
    this.id = id;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.tarifa = tarifa;
    this.estado = estado;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getTarifa() {
    return tarifa;
  }

  public void setTarifa(double tarifa) {
    this.tarifa = tarifa;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

}
