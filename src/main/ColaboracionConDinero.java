package ar.edu.utn.frba.dds;


import exceptions.ColaboracionNoRealizadaException;
import java.time.LocalDate;

class ColaboracionConDinero implements Colaboracion {
  public static Double coeficientePuntuacionAportada = 0.5;
  private final Double monto;
  private final Frecuencia frecuencia;
  private LocalDate fechaDonacion;
  private Boolean realizada = false;

  public ColaboracionConDinero(Double monto, LocalDate fechaDonacion, Frecuencia frecuencia) {
    this.monto = monto;
    this.frecuencia = frecuencia;
    this.fechaDonacion = fechaDonacion;
  }

  public Double getMonto() {
    return monto;
  }

  public LocalDate getFechaDonacion() {
    return fechaDonacion;
  }

  public Frecuencia getFrecuencia() {
    return frecuencia;
  }
  
  public void realizarContribucion(Colaborador colaborador) {
    if (realizada) {
      throw new ColaboracionNoRealizadaException("La contribución ya fue realizada");
    }
    this.realizada = true;
  }

  public Double calcularPuntaje() {
    return monto * coeficientePuntuacionAportada;
  }

  public LocalDate getFechaColaboracion() {
    return fechaDonacion;
  }


  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaDonacion.isBefore(fechaDesde) || fechaDonacion.isAfter(fechaHasta));
  }
}