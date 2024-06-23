package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ColaboradorJuridico implements Colaborador {
  private String razonSocial;
  private TipoPersonaJuridica tipo;
  private String rubro;
  private String direccion;
  private Collection<Colaboracion> colaboraciones = new ArrayList<>();

  public ColaboradorJuridico(
      String razonSocial,
      TipoPersonaJuridica tipo,
      String rubro,
      String direccion
  ) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.rubro = rubro;
    this.direccion = direccion;
  }

  public boolean sos(TipoDocumento tipoDocumento, Integer documento) {
    return false;
  }

  public String getRazonSocial() {
    return this.razonSocial;
  }

  public TipoPersonaJuridica getTipo() {
    return this.tipo;
  }

  public String getRubro() {
    return this.rubro;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public void colaborarCon(ColaboracionConDinero colaboracionConDinero) {
    colaboracionConDinero.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConDinero);
  }

  public void colaborarCon(
      ColaboracionConAdministracionDeHeladera colaboracionConAdministracionDeHeladera) {
    colaboracionConAdministracionDeHeladera.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConAdministracionDeHeladera);
  }

  public Double calcularPuntaje(LocalDate fechaDesde, LocalDate fechaHasta) {
    return colaboraciones.stream().filter(colaboracion -> colaboracion.seRealizoDurante(fechaDesde,
        fechaHasta)).mapToDouble(Colaboracion::calcularPuntaje).sum();
  }

  public String getDenominacion() {
    return razonSocial;
  }

  public void agregarColaboracionHistorica(ColaboracionHistorica colaboracionHistorica) {
    throw new RuntimeException("No se pueden cargar colaboraciones históricas de los "
        + "colaboradores jurídicos");
  }

  public List<Colaboracion> obtenerColaboraciones() {
    return colaboraciones.stream().toList();
  }
}
