
package Interfaces;

import Modelo.Usuario;

import java.util.List;


public interface Usuario_i {
  public List<Usuario>  listar();
  public Usuario list(int id);
  public boolean agregar(Usuario u);
  public boolean editar(Usuario u);
  public Usuario validar(String user, String pass);
  public boolean cambiarClave(int id, String clave);
}
