
package Modelo;


public class Parqueo {
  int id;
  String codigo;
  String descripcion;
  int nivel;
  String estado;

  public Parqueo() {
  }

  public Parqueo(int id, String codigo, String descripcion, int nivel, String estado) {
    this.id = id;
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.nivel = nivel;
    this.estado = estado;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getNivel() {
    return nivel;
  }

  public void setNivel(int nivel) {
    this.nivel = nivel;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  

}
