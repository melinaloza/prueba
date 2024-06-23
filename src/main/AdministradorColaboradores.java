package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdministradorColaboradores {

  private List<Colaborador> colaboradores = new ArrayList<>();

  public void altaColaborador(Colaborador colaborador) {
    colaboradores.add(colaborador);
  }

  public void bajaColaborador(Colaborador colaborador) {
    if (!colaboradores.contains(colaborador)) {
      throw new IllegalArgumentException("Colaborador desconocido");
    }
    colaboradores.remove(colaborador);
  }

  public Map<Colaborador, Double> reportePuntajeColaboradores(
      LocalDate fechaDesde, LocalDate fechaHasta) {
    if (colaboradores.isEmpty()) {
      return new HashMap<>();
    }
    Map<Colaborador, Double> colaboradoresYpuntajes = new HashMap<Colaborador, Double>();
    for (Colaborador unColaborador : this.colaboradores) {
      colaboradoresYpuntajes.put(
          unColaborador,
          unColaborador.calcularPuntaje(fechaDesde, fechaHasta)
      );
    }
    return colaboradoresYpuntajes;

  }

  public List<Colaborador> obtenerColaboradores() {
    return colaboradores.stream().toList();
  }
}