
package Interfaces;

import Modelo.Empresa;
import java.util.ArrayList;

public interface Empresa_i {
  public ArrayList<Empresa> listar();
  public Empresa list(int id);
  public boolean agregar(Empresa em);
  public boolean editar(Empresa em);
}
