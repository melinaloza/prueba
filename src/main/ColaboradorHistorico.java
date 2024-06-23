package ar.edu.utn.frba.dds;

import java.util.List;

public class ColaboradorHistorico {
  private TipoDocumento tipoDoc;
  private Integer documento;
  private String nombre;
  private String apellido;
  private String mail;

  public ColaboradorHistorico(
      TipoDocumento tipoDoc,
      Integer documento,
      String nombre,
      String apellido,
      String mail
  ) {
    this.tipoDoc = tipoDoc;
    this.documento = documento;
    this.nombre = nombre;
    this.apellido = apellido;
    this.mail = mail;
  }

  public Colaborador obtenerColaboradorHumano(
      AdministradorColaboradores administradorColaboradores
  ) {
    List<Colaborador> colaboradores =
        administradorColaboradores.obtenerColaboradores().stream().filter(
            colaboradorExistente -> colaboradorExistente.sos(tipoDoc, documento)).toList();

    if (colaboradores.isEmpty()) {
      ColaboradorBuilder builder = new ColaboradorBuilder();
      builder.nombre(nombre)
          .apellido(apellido)
          .email(mail)
          .tipoDocumento(tipoDoc)
          .documento(documento);
      Colaborador colaborador = builder.buildColaboradorHumano();
      administradorColaboradores.altaColaborador(colaborador);
      return colaborador;
    }

    return colaboradores.get(0);
  }
}
