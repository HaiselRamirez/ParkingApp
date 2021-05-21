
package Modelo;


public class Usuario {
    int id;
    String username;
    String password;
    String nombre;
    String correo;
    String cargo;
    boolean estado;

    public Usuario() {
    }

    public Usuario(int id, String username, String password, String nombre, String correo, String cargo, boolean estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.cargo = cargo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
