package ar.edu.utn.frba.dds;

import exceptions.ColaboracionNoRealizadaException;
import java.time.LocalDate;

class ColaboracionConVianda implements Colaboracion {
  public static final Double coeficientePuntuacionAportada = 1.5;
  private final LocalDate fechaDonacion;
  private final Vianda vianda;
  private Boolean realizada = false;

  public ColaboracionConVianda(Vianda vianda, LocalDate fechaDonacion) {
    this.vianda = vianda;
    this.fechaDonacion = fechaDonacion;
  }

  public Vianda getVianda() {
    return vianda;
  }

  public void realizarContribucion(Colaborador colaborador) {
    // Implementación específica para ContribucionDeVianda
    if (realizada) {
      throw new ColaboracionNoRealizadaException("La contribución ya fue realizada");
    }
    this.vianda.guardarEnHeladeraAsignada();
    this.realizada = true;
  }

  //En nuestro modelo tenemos que por cada colaboracion solo se dona una vianda
  public Double calcularPuntaje() {
    return this.vianda.semanasFrescas() * coeficientePuntuacionAportada;
  }

  public LocalDate getFechaColaboracion() {
    return fechaDonacion;
  }


  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaDonacion.isBefore(fechaDesde) || fechaDonacion.isAfter(fechaHasta));
  }
}