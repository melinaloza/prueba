package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.Collection;


public class GestorPersonaVulnerable {
  Usuario usuario;
  private Collection<PersonaVulnerable> personasVulnerables = new ArrayList<>();
  // Por si un gestor solo puede tener una personaVulnerable a cargo. REVISAR
  // PersonaVulnerable personaVulnerable;


  public Usuario getUsuario() {
    return usuario;
  }


  public Collection<PersonaVulnerable> getPersonasVulnerables() {
    return personasVulnerables;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void registrarPersonaVulnerable(
      PersonaVulnerable personaVulnerable
  ) {
    personasVulnerables.add(personaVulnerable);
  }


}
