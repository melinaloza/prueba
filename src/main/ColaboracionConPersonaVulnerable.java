package ar.edu.utn.frba.dds;

import exceptions.ColaboracionNoRealizadaException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ColaboracionConPersonaVulnerable implements Colaboracion {
  public static final Double coeficientePuntuacionAportada = 2.0;
  private final LocalDate fechaColaboracion;
  private Tarjeta tarjetaAentregar;
  private final PersonaVulnerable personaVulnerable;
  private final GestorPersonaVulnerable gestorPersonaVulnerable;
  private Boolean realizada = false;


  public ColaboracionConPersonaVulnerable(
      PersonaVulnerable personaVulnerable,
      GestorPersonaVulnerable gestorPersonaVulnerable,
      LocalDate fechaColaboracion) {
    this.personaVulnerable = personaVulnerable;
    this.gestorPersonaVulnerable = gestorPersonaVulnerable;
    this.fechaColaboracion = fechaColaboracion;
  }


  public void realizarContribucion(Colaborador colaborador) {
    if (realizada) {
      throw new ColaboracionNoRealizadaException("La contribución ya fue realizada");
    }
    ColaboradorHumano colaboradorHumano = (ColaboradorHumano) colaborador;
    this.tarjetaAentregar = colaboradorHumano.getTarjeta();
    tarjetaAentregar.asociarCon(personaVulnerable);
    gestorPersonaVulnerable.registrarPersonaVulnerable(personaVulnerable);
    this.realizada = true;
  }

  private Integer mesesDesdeColaboracion() {
    return (int) ChronoUnit.MONTHS.between(fechaColaboracion, LocalDate.now());
  }

  public Double calcularPuntaje() {
    return (double) tarjetaAentregar.cantidadUsos()
        * mesesDesdeColaboracion()
        * coeficientePuntuacionAportada;
  }

  public LocalDate getFechaColaboracion() {
    return fechaColaboracion;
  }


  public boolean seRealizoDurante(LocalDate fechaDesde, LocalDate fechaHasta) {
    return !(fechaColaboracion.isBefore(fechaDesde) || fechaColaboracion.isAfter(fechaHasta));
  }
}