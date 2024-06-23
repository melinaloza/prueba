package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.util.List;

public interface Colaborador {

  void colaborarCon(ColaboracionConDinero colaboracionConDinero);

  boolean sos(TipoDocumento tipoDocumento, Integer documento);

  String getDenominacion();

  void agregarColaboracionHistorica(ColaboracionHistorica colaboracionHistorica);

  Double calcularPuntaje(LocalDate fechaDesde, LocalDate fechaHasta);

  List<Colaboracion> obtenerColaboraciones();

}