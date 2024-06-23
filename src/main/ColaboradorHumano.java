package ar.edu.utn.frba.dds;

import exceptions.SinTarjetasParaColaborarException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;


public class ColaboradorHumano implements Colaborador {
  private String nombre;
  private String apellido;
  private Integer telefono;
  private String direccion;
  private Integer whatsapp;
  private LocalDate fechaNacimiento;
  private String email;
  private TipoDocumento tipoDocumento;
  private Integer documento;
  private Collection<Colaboracion> colaboraciones = new ArrayList<>();
  private Stack<Tarjeta> tarjetas = new Stack<>();

  public ColaboradorHumano(
      String email,
      Integer telefono,
      Integer whatsapp,
      String direccion,
      String nombre,
      String apellido,
      LocalDate fechaNacimiento,
      TipoDocumento tipoDocumento,
      Integer documento
  ) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.email = email;
    this.telefono = telefono;
    this.direccion = direccion;
    this.whatsapp = whatsapp;
    this.tipoDocumento = tipoDocumento;
    this.documento = documento;
  }

  public boolean sos(TipoDocumento tipoDocumento, Integer documento) {
    return this.tipoDocumento == tipoDocumento && this.documento.equals(documento);
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public Integer getTelefono() {
    return this.telefono;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public Integer getWhatsapp() {
    return this.whatsapp;
  }

  public LocalDate getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public String getEmail() {
    return this.email;
  }

  public void colaborarCon(ColaboracionConVianda colaboracionConVianda) {
    colaboracionConVianda.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConVianda);
  }

  public void colaborarCon(ColaboracionConDinero colaboracionConDinero) {
    colaboracionConDinero.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConDinero);
  }

  public void colaborarCon(ColaboracionConPersonaVulnerable colaboracionConPersonaVulnerable) {
    colaboracionConPersonaVulnerable.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConPersonaVulnerable);
  }

  public void colaborarCon(ColaboracionConDistribucionVianda colaboracionConDistribucionVianda) {
    colaboracionConDistribucionVianda.realizarContribucion(this);
    this.colaboraciones.add(colaboracionConDistribucionVianda);
  }

  public void agregarColaboracionHistorica(ColaboracionHistorica colaboracionHistorica) {
    this.colaboraciones.add(colaboracionHistorica);
  }

  public Double calcularPuntaje(LocalDate fechaDesde, LocalDate fechaHasta) {
    return colaboraciones.stream().filter(colaboracion -> colaboracion.seRealizoDurante(fechaDesde,
        fechaHasta)).mapToDouble(Colaboracion::calcularPuntaje).sum();
  }

  public String getDenominacion() {
    return nombre + " " + apellido;
  }

  public List<Colaboracion> obtenerColaboraciones() {
    return colaboraciones.stream().toList();
  }

  public void recibirTarjetas(List<Tarjeta> tarjetasEntregadas) {
    if (this.direccion == null) {
      throw new RuntimeException("El colaborador debe tener una dirección asignada para recibir "
          + "tarjetas");
    }
    this.tarjetas.addAll(tarjetasEntregadas);
  }

  public Tarjeta getTarjeta() {
    if (tarjetas.isEmpty()) {
      throw new SinTarjetasParaColaborarException("Ya no hay tarjetas para colaborar");
    }
    return tarjetas.pop();
  }

  public int obtenerCantidadTarjetas() {
    return this.tarjetas.size();
  }

}
