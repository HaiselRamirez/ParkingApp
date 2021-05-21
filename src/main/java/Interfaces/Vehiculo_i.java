
package Interfaces;

import Modelo.Vehiculo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface Vehiculo_i {
  public List<Vehiculo> listar() throws SQLException;
  public Vehiculo list(int id);
  public boolean agregar(Vehiculo ve);
  public boolean editar(Vehiculo ve);
  public ArrayList<Vehiculo> getVehiculo();
}
