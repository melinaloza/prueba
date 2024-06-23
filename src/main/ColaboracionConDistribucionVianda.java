package ar.edu.utn.frba.dds;

import exceptions.ColaboracionNoRealizadaException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class ColaboracionConDistribucionVianda implements Colaboracion {
  public static final Double coeficientePuntuacionAportada = 1.0;
  private final Heladera heladeraOrigen;
  private final Heladera heladeraDestino;
  private final MotivoDistribucionVianda motivo;
  List<Vianda> viandas;
  private LocalDate fechaDistribucion;
  private Boolean realizada = false;

  public ColaboracionConDistribucionVianda(
      Heladera heladeraOrigen,
      Heladera heladeraDestino,
      List<Vianda> viandas,
      MotivoDistribucionVianda motivo,
      LocalDate fechaDistribucion) {
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.viandas = viandas;
    this.motivo = motivo;
    this.fechaDistribucion = fechaDistribucion;
  }

  public LocalDate getFechaDistribucion() {
    return fechaDistribucion;
  }

  public MotivoDistribucionVianda getMotivo() {
    return motivo;
  }

  public Heladera getHeladeraOrigen() {
    return heladeraOrigen;
  }

  public Heladera getHeladeraDestino() {
    return heladeraDestino;
  }

  public Collection<Vianda> getViandas() {
    return viandas;
  }

  public void realizarContribucion(Colaborador colaborador) {
    if (realizada) {
      throw new ColaboracionNoRealizadaException("La contribución ya fue realizada");
    }
    heladeraOrigen.retirarViandasParaRedistribucion(viandas);
    heladeraDestino.agregarViandasParaRedistribucion(viandas);
    this.realizada = true;
  }

  public Double calcularPuntaje() {
    return this.viandas.size() * coeficientePuntuacionAportada;
  }

  public LocalDate getFechaColaboracion() {
    return fechaDistribucion;
  }

  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaDistribucion.isBefore(fechaDesde) || fechaDistribucion.isAfter(fechaHasta));
  }
}
