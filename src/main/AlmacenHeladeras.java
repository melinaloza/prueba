package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;


public class AlmacenHeladeras {
  private List<Heladera> heladeras = new ArrayList<>();

  public List<Heladera> visualizarMapa() {
    return heladeras;
  }

  public void agregarHeadera(Heladera heladera) {
    heladeras.add(heladera);
  }

  public void bajaHeladera(Heladera heladera) {
    if (!heladeras.contains(heladera)) {
      throw new IllegalArgumentException("Heladera desconocida");
    }
    // TODO: Averiguar si debemos verificar que no esté vacía antes de eliminarla
    heladeras.remove(heladera);
  }
}
