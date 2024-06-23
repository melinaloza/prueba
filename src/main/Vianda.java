package ar.edu.utn.frba.dds;

import exceptions.ColaboracionNoRealizadaException;
import exceptions.ViandaEntregadaException;
import java.time.Duration;
import java.time.LocalDate;

public class Vianda {
  private Comida comida;
  private LocalDate fechaCaducidad;
  private LocalDate fechaDonacion;
  private Colaborador donadaPor;
  private Boolean entregada = false;
  private Double peso;
  private Double calorias;
  private Heladera heladera;

  public Vianda(
      Comida comida,
      LocalDate fechaCaducidad,
      LocalDate fechaDonacion,
      Colaborador donadoPor,
      Double peso,
      Double calorias) {
    if (comida == null) {
      throw new IllegalArgumentException("El valor de `comida` no puede ser null");
    }

    if (fechaCaducidad == null) {
      throw new IllegalArgumentException("El valor de `fechaCaducidad` no puede ser null");
    }

    if (fechaDonacion == null) {
      throw new IllegalArgumentException("El valor de `fechaDonacion` no puede ser null");
    }

    if (donadoPor == null) {
      throw new IllegalArgumentException("El valor de `donadoPor` no puede ser null");
    }

    this.comida = comida;
    this.fechaCaducidad = fechaCaducidad;
    this.fechaDonacion = fechaDonacion;
    this.donadaPor = donadoPor;
    this.peso = peso;
    this.calorias = calorias;
  }

  public Comida getComida() {
    return comida;
  }

  public LocalDate getFechaCaducidad() {
    return fechaCaducidad;
  }

  public LocalDate getFechaDonacion() {
    return fechaDonacion;
  }

  public Colaborador getDonadaPor() {
    return donadaPor;
  }

  public Double getPeso() {
    return peso;
  }

  public Double getCalorias() {
    return calorias;
  }


  public void guardarEnHeladeraAsignada() {
    if (heladera == null) {
      throw new ColaboracionNoRealizadaException("No se puede guardar en la heladera si no se le "
          + "asigna una previamente");
    }
    this.heladera.recibirVianda(this);
  }

  public Boolean fueEntregada() {
    return entregada;
  }

  public void consumirVianda(Tarjeta tarjeta) {
    if (entregada) {
      throw new ViandaEntregadaException("¡No se puede consumir la vianda! ya fue entregada");
    }
    UsoTarjeta usoTarjeta = new UsoTarjeta(LocalDate.now(), heladera);
    tarjeta.agregarUso(usoTarjeta);
    this.entregada = true;
  }

  public Heladera getHeladera() {
    return heladera;
  }

  public void asignarHeladera(Heladera heladera) {
    if (heladera == null) {
      throw new IllegalArgumentException("La heladera asignada no puede ser null");
    }
    this.heladera = heladera;
  }

  public Long calcularDias() {
    Duration dif = Duration.between(
        this.fechaDonacion.atStartOfDay(),
        this.fechaCaducidad.atStartOfDay()
    );
    return dif.toDays();
  }

  public Boolean estaFresca() {
    return this.calcularDias() >= 0;
  }

  public Long semanasFrescas() {
    return (this.estaFresca() ? this.calcularDias() / 7 : 0);
  }
}