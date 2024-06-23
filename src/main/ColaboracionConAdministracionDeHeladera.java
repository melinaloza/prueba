package ar.edu.utn.frba.dds;

import exceptions.ColaboracionNoRealizadaException;
import java.time.LocalDate;

public class ColaboracionConAdministracionDeHeladera implements Colaboracion {
  public static final Double coeficientePuntuacionAportada = 5.0;
  private final Heladera heladera;
  private final LocalDate fechaColaboracion;
  private Boolean realizada = false;

  public ColaboracionConAdministracionDeHeladera(Heladera heladera, LocalDate fechaColaboracion) {

    if (heladera == null) {
      throw new IllegalArgumentException("La heladera es obligatoria");
    }
    this.heladera = heladera;
    this.fechaColaboracion = fechaColaboracion;
  }

  public Heladera getHeladera() {
    return this.heladera;
  }

  public void realizarContribucion(Colaborador colaborador) {
    if (realizada) {
      throw new ColaboracionNoRealizadaException("La contribución ya fue realizada");
    }
    this.heladera.asignarAdministrador(colaborador);
    this.realizada = true;
  }

  public Double calcularPuntaje() {
    return heladera.mesesActiva()
        * heladera.obtenerUsos()
        * coeficientePuntuacionAportada;
  }

  public LocalDate getFechaColaboracion() {
    return fechaColaboracion;
  }

  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaColaboracion.isBefore(fechaDesde) || fechaColaboracion.isAfter(fechaHasta));
  }
}
