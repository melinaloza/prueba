package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Clase que permite la creación de colaboradores de forma más sencilla.
 * Se pueden crear colaboradores humanos y jurídicos.
 */
public class ColaboradorBuilder {
  private String nombre;
  private String apellido;

  private String razonSocial;
  private TipoPersonaJuridica tipo;
  private String rubro;

  private String email; // Al menos uno
  private Integer telefono; // Al menos uno
  private Integer whatsapp; //Opcional
  private String direccion;
  private LocalDate fechaNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer documento;

  /**
   * Constructor de la clase.
   */
  public ColaboradorBuilder() {
  }

  /**
   * Método que permite la creación de un colaborador humano.
   */
  public ColaboradorHumano buildColaboradorHumano() {
    if (this.nombre == null) {
      throw new RuntimeException("El nombre del Colaborador es obligatorio");
    } else if (this.apellido == null) {
      throw new RuntimeException("El apellido del Colaborador es obligatorio");
    } else if (this.email == null && this.telefono == null && this.whatsapp == null) {
      throw new RuntimeException("Se requiere un medio de contacto al menos");
    }
    //TODO Validar que no agregue un tipo de colaboracion Juridico
    return new ColaboradorHumano(
        email,
        telefono,
        whatsapp,
        direccion,
        nombre,
        apellido,
        fechaNacimiento,
        tipoDocumento,
        documento);
  }

  /**
   * Método que permite la creación de un colaborador jurídico.
   */
  public ColaboradorJuridico buildColaboradorJuridico() {
    if (this.razonSocial == null) {
      throw new RuntimeException("La razon social del Colaborador es obligatorio");
    } else if (this.tipo == null) {
      throw new RuntimeException("El tipo del Colaborador es obligatorio");
    } else if (this.email == null && this.telefono == null && this.whatsapp == null) {
      throw new RuntimeException("Se requiere un medio de contacto al menos");
    }
    //TODO Validar que no agregue un tipo de colaboracion Humano
    return new ColaboradorJuridico(
        razonSocial,
        tipo,
        rubro,
        direccion
    );
  }

  /*
  * Comento esto, es lo que agrego Leo y no lo vamos a utilizar, saque lo demas en el codigo.
  * Todavia falta validar si el tipo de colaboracion es valido para el colaborador

  public void validarFormaContribuir(Categoria categoria) {
    Collection<Categoria> listaCategorias = metodosDeColaboracion.stream()
        .map(TipoColaboracion::getCategoria)
        .toList();
    if (listaCategorias.contains(categoria)) {
      throw new RuntimeException("El colaborador no puede contribuir de esa manera");
    }
  }
  * */
  public ColaboradorBuilder nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public ColaboradorBuilder apellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  public ColaboradorBuilder razonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
    return this;
  }

  public ColaboradorBuilder tipo(TipoPersonaJuridica tipo) {
    this.tipo = tipo;
    return this;
  }

  public ColaboradorBuilder rubro(String rubro) {
    this.rubro = rubro;
    return this;
  }

  public ColaboradorBuilder email(String email) {
    this.email = email;
    return this;
  }

  public ColaboradorBuilder telefono(Integer telefono) {
    this.telefono = telefono;
    return this;
  }

  public ColaboradorBuilder whatsapp(Integer whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public ColaboradorBuilder direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }


  public ColaboradorBuilder fechaNacimiento(String fechaNacimiento) {
    this.fechaNacimiento = java.time.LocalDate.parse(fechaNacimiento);
    return this;

  }

  public ColaboradorBuilder tipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
    return this;
  }

  public ColaboradorBuilder documento(Integer documento) {
    this.documento = documento;
    return this;
  }
}