package ar.edu.utn.frba.dds;


import exceptions.LimiteDeUsosAlcanzadoException;
import exceptions.TarjetaNoAsociadaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tarjeta {

  private final String codigo;
  private List<UsoTarjeta> usosDeLaTarjeta;
  private PersonaVulnerable personaVulnerable;

  private static String createRandomCode() {
    StringBuilder sb = new StringBuilder();
    while (sb.length() < 11) {
      sb.append(UUID.randomUUID());
    }
    return sb.substring(0, 11);
  }

  public Tarjeta(List<UsoTarjeta> usosDeLaTarjeta) {
    this.codigo = createRandomCode();
    this.usosDeLaTarjeta = (usosDeLaTarjeta != null) ? usosDeLaTarjeta : new ArrayList<>();
  }

  public void asociarCon(PersonaVulnerable persona) {
    this.personaVulnerable = persona;
  }

  public String getCodigo() {
    return codigo;
  }

  public int usosRestantes() {
    if (personaVulnerable == null) {
      throw new TarjetaNoAsociadaException("La tarjeta no esta asociada a ninguna persona");
    }
    int usosActual = cantidadUsosFechaActual();
    int usosAdmitidos = 2 * personaVulnerable.getCantidadMenoresCargo() + 4;

    return usosAdmitidos - usosActual;
  }

  public int cantidadUsosFechaActual() {
    int cantidad = 0;
    for (UsoTarjeta usoTarjeta : usosDeLaTarjeta) {
      if (usoTarjeta.getFechaUso().equals(LocalDate.now())) {
        cantidad++;
      }
    }
    return cantidad;
  }

  public void agregarUso(UsoTarjeta usoTarjeta) {
    if (this.usosRestantes() == 0) {
      throw new LimiteDeUsosAlcanzadoException("No tiene mas usos disponibles");
    }
    usosDeLaTarjeta.add(usoTarjeta);
  }

  public Integer cantidadUsos() {
    return this.usosDeLaTarjeta.size();
  }

  public PersonaVulnerable getTitular() {
    if (personaVulnerable == null) {
      throw new TarjetaNoAsociadaException("La tarjeta no esta asociada a ninguna Persona");
    }
    return personaVulnerable;
  }

}
