package ar.edu.utn.frba.dds;

class Wsensor {
  Double valor;
  String unit;

  public Wsensor(Double valor, String unit) {
    this.valor = valor;
    this.unit = unit;
  }

  public Reading getWeight(String serialNumber) {
    return new Reading(valor, unit);
  }
}