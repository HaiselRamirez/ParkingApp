
package Interfaces;

import Modelo.Parqueo;
import java.util.ArrayList;
import java.util.List;


public interface Parqueo_i {
  
  public List<Parqueo> listar();
  public Parqueo list(int id);
  public boolean agregar( Parqueo pa);
  public boolean editar(Parqueo pa);
  public ArrayList<Parqueo> getParkin();
  public boolean ocupar(int id);
  public boolean desocupar(int id);
}
