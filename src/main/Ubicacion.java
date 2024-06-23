package ar.edu.utn.frba.dds;

public class Ubicacion {
  public final Double latitud;
  public final Double longitud;
  public final String direccion;

  public Ubicacion(Double latitud, Double longitud, String direccion) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.direccion = direccion;
  }
}