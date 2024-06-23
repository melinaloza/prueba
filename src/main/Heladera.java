package ar.edu.utn.frba.dds;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Heladera {
  private Ubicacion ubicacion;
  private String nombre;
  private LocalDate fechaPuestaEnFuncionamiento;
  private Collection<Vianda> viandas;
  private ModeloHeladera modeloHeladera;
  private Boolean estaActiva = true;
  private SensorTemperatura sensorTemperatura;
  private SensorPeso sensorPeso;
  private List<Double> temperaturas = new ArrayList<>();
  private Colaborador administradaPor;

  public Heladera(
      Double latitud, Double longitud, String direccion,
      String nombre, LocalDate fechaPuestaEnFuncionamiento,
      Collection<Vianda> viandas, ModeloHeladera modeloHeladera) {
    this.ubicacion = new Ubicacion(latitud, longitud, direccion);
    this.nombre = nombre;
    this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
    this.viandas = (viandas != null) ? viandas : new ArrayList<>();
    this.modeloHeladera = modeloHeladera;
  }

  public void asignarAdministrador(Colaborador colaborador) {
    if (this.administradaPor != null) {
      throw new RuntimeException("La heladera ya se encuentra administrada por un colaborador");
    }
    this.administradaPor = colaborador;
  }

  public Colaborador getAdministradaPor() {
    return administradaPor;
  }

  public void recibirVianda(Vianda vianda) {
    if (vianda == null) {
      throw new RuntimeException("Debe ingresar una vianda válida");
    }
    if (this.viandas.contains(vianda)) {
      throw new RuntimeException("La vianda ya se encuentra en la heladera");
    }
    this.viandas.add(vianda);
  }


  public Collection<Vianda> getViandas() {
    return viandas;
  }

  public void recibirTemperatura(Double temperatura) {
    if (temperaturas.size() == 3) {
      temperaturas.remove(0);
    }
    temperaturas.add(temperatura);
  }

  public AtencionDeHeladera getEstado() {
    Double tempMin = modeloHeladera.getTemperaturaMinima();
    Double tempMax = modeloHeladera.getTemperaturaMaxima();
    List<Double> respuesta = temperaturas.stream().filter(
        temperatura -> temperatura < tempMin || temperatura > tempMax).toList();
    return (respuesta.size() < 3) ? AtencionDeHeladera.NO_NECESITA_ATENCION :
        AtencionDeHeladera.NECESITA_ATENCION;
  }

  public LlenadoHeladera getLlenado() {
    double porcentaje = (sensorPeso.getPeso(this.nombre) * 100)
        / (modeloHeladera.getCapacidad() * 0.4);
    if (porcentaje <= 30) {
      return LlenadoHeladera.BAJO;
    } else if (porcentaje <= 70) {
      return LlenadoHeladera.MEDIO;
    }
    return LlenadoHeladera.ALTO;
  }

  public LocalDate getFechaPuestaEnFuncionamiento() {
    return fechaPuestaEnFuncionamiento;
  }

  public void setUbicacion(Double latitud, Double longitud, String direccion) {
    this.ubicacion = new Ubicacion(latitud, longitud, direccion);
  }

  public void setEstaActiva(Boolean estaActiva) {
    this.estaActiva = estaActiva;
  }

  public void setSensorPeso(SensorPeso sensorPeso) {
    this.sensorPeso = sensorPeso;
  }

  public void setSensorTemperatura(SensorTemperatura sensorTemperatura) {
    this.sensorTemperatura = sensorTemperatura;
    this.sensorTemperatura.setCallback(this::recibirTemperatura);
  }

  public List<Double> getTemperaturas() {
    return temperaturas;
  }

  public Long diasActiva() {
    LocalDate fechaActual = LocalDate.now();
    Duration dif = Duration.between(
        this.fechaPuestaEnFuncionamiento.atStartOfDay(), fechaActual.atStartOfDay());
    return (this.estaActiva ? dif.toDays() : 0);
  }

  public void retirarViandasParaRedistribucion(List<Vianda> viandasAretirar) {
    if (!this.viandas.containsAll(viandasAretirar)) {
      throw new RuntimeException("La vianda que desea redistribuir"
          + " no se encuentra en esta heladera");
    }
    this.viandas.removeAll(viandasAretirar);
  }

  public void agregarViandasParaRedistribucion(List<Vianda> viandasRecibidas) {
    this.viandas.addAll(viandasRecibidas);
  }

  public Long mesesActiva() {
    return this.diasActiva() / 30;
  }

  public Integer obtenerUsos() {
    return this.viandas.stream().filter(Vianda::fueEntregada).toList().size();
  }
}

