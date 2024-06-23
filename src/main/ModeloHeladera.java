package ar.edu.utn.frba.dds;

public class ModeloHeladera {
  private Integer capacidad;
  private Double temperaturaMinima;
  private Double temperaturaMaxima;


  public ModeloHeladera(Double temperaturaMinima, Double temperaturaMaxima, Integer capacidad) {
    this.temperaturaMinima = temperaturaMinima;
    this.temperaturaMaxima = temperaturaMaxima;
    this.capacidad = capacidad;
  }

  public Double getTemperaturaMinima() {
    return temperaturaMinima;
  }

  public Double getTemperaturaMaxima() {
    return temperaturaMaxima;
  }

  public Integer getCapacidad() {
    return capacidad;
  }
}
