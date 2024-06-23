package ar.edu.utn.frba.dds;


import java.time.LocalDate;

public class PersonaVulnerable {
  private String nombre;
  private LocalDate fechaNacimiento;
  private LocalDate fechaRegistro;
  private String domicilio;
  private TipoDocumento tipoDocumento;
  private Integer numeroDocumento;
  private Integer cantidadMenoresCargo;

  public PersonaVulnerable(
      String nombre,
      LocalDate fechaNacimiento,
      LocalDate fechaRegistro,
      String domicilio,
      TipoDocumento tipoDocumento,
      Integer numeroDocumento,
      Integer cantidadMenoresCargo) {
    if (nombre == null) {
      throw new IllegalArgumentException("El valor de `nombre` no puede ser `null`");
    }

    if (fechaNacimiento == null) {
      throw new IllegalArgumentException("El valor de `fecha de nacimiento` no puede ser `null`");
    }

    if (fechaRegistro == null) {
      throw new IllegalArgumentException("El valor de `fecha de registro` no puede ser `null`");
    }

    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.fechaRegistro = fechaRegistro;
    this.domicilio = domicilio;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.cantidadMenoresCargo =
        cantidadMenoresCargo == null ? Integer.valueOf(0) : cantidadMenoresCargo;
  }

  public String getNombre() {
    return nombre;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public LocalDate getFechaRegistro() {
    return fechaRegistro;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public Integer getNumeroDocumento() {
    return numeroDocumento;
  }

  public Integer getCantidadMenoresCargo() {
    return cantidadMenoresCargo;
  }

  public Boolean estaEnSituacionDeCalle() {
    return this.domicilio == null;
  }

  public Boolean tieneMenoresCargo() {
    return this.cantidadMenoresCargo > 0;
  }


}
