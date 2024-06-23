package ar.edu.utn.frba.dds;

import java.time.LocalDate;

public class ColaboracionHistorica implements Colaboracion {
  private final int cantidad;
  private final TipoColaboracionHistorica tipoColaboracion;
  private final LocalDate fechaColaboracion;

  public ColaboracionHistorica(int cantidad, LocalDate fechaColaboracion,
                               TipoColaboracionHistorica tipoColaboracion) {
    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
    }
    if (tipoColaboracion == null) {
      throw new IllegalArgumentException("El tipo de colaboración no puede estar vacio");
    }
    if (fechaColaboracion == null) {
      throw new IllegalArgumentException("La fecha de colaboración no puede ser nula");
    }

    this.cantidad = cantidad;
    this.fechaColaboracion = fechaColaboracion;
    this.tipoColaboracion = tipoColaboracion;
  }

  public Double calcularPuntaje() {
    //Por lo hablado con Franco el 31-05-2024,
    // las colaboraciones históricas de momento computan cero puntos
    return 0.0;
  }

  public LocalDate getFechaColaboracion() {
    return this.fechaColaboracion;
  }

  public void realizarContribucion(Colaborador colaborador) {
  }

  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaColaboracion.isBefore(fechaDesde) || fechaColaboracion.isAfter(fechaHasta));
  }

  public TipoColaboracionHistorica obtenerTipoColaboracion() {
    return this.tipoColaboracion;
  }
}
