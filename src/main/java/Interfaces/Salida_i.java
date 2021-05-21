
package Interfaces;

import Modelo.Salida;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public interface Salida_i {
  public ArrayList<Salida> listar();
  public Salida list (int id);
  public boolean agregar(Salida sa);
  public boolean editar(Salida sa);
  public ArrayList<Salida> salidaUsuario(int user);
  public ArrayList<Salida> salidaFecha(Date fecha);
  public Map<String,String> detalleSalida(int id);
  public Double cobradoPorUsuario(int id);
}
