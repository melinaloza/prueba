package ar.edu.utn.frba.dds;

import java.time.LocalDate;

public interface Colaboracion {

  void realizarContribucion(Colaborador colaborador);

  Double calcularPuntaje();

  LocalDate getFechaColaboracion();

  boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta);
}