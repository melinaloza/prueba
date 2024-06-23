package ar.edu.utn.frba.dds;

import java.time.LocalDate;

public class UsoTarjeta {

  private Heladera heladera;
  private LocalDate fechaUso;

  public UsoTarjeta(LocalDate fechaUso, Heladera heladera) {
    this.fechaUso = fechaUso;
    this.heladera = heladera;
  }

  public LocalDate getFechaUso() {
    return fechaUso;
  }

  public Heladera getHeladera() {
    return heladera;
  }

}
