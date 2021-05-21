
package Modelo;
public class Empresa {
  
  private int id;
  private String nombre;
  private String slogan;
  private String descripcion;
  private String logo;
  private String email;
  private String telefono;
  private String rnc;
  private String direccion;
  private String web;

  public Empresa() {
  }

  public Empresa(int id, String nombre, String slogan, String descripcion, String logo, String email, String telefono,  String rnc, String direccion, String web) {
    this.id = id;
    this.nombre = nombre;
    this.slogan = slogan;
    this.descripcion = descripcion;
    this.logo = logo;
    this.email = email;
    this.telefono = telefono;
    this.rnc = rnc;
    this.direccion = direccion;
    this.web = web;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getSlogan() {
    return slogan;
  }

  public void setSlogan(String slogan) {
    this.slogan = slogan;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getRnc() {
    return rnc;
  }

  public void setRnc(String rnc) {
    this.rnc = rnc;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getWeb() {
    return web;
  }

  public void setWeb(String web) {
    this.web = web;
  }

  
  
  
}
