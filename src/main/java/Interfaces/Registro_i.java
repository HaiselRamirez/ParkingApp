package Interfaces;

import Modelo.Registro;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface Registro_i {
  public ArrayList<Registro> listar();
  public Registro list(int id);
  public int registrar(Registro re);
  public ArrayList<Registro> registroUsuario(int user);
  public ArrayList<Registro> registroFecha(Date fecha);
  public boolean terminar(int id);
  public Map<String,String> detallesRegistro(String placa);
  public ArrayList<Registro> regActivos();
  public ArrayList<Object> detallesRegistro();
  public Map<String,String>detalleUnRegistro(int id);
}
