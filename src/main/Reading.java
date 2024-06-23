package ar.edu.utn.frba.dds;

class Reading {
  private Double value;
  private String unit;

  public Reading(Double value, String unit) {
    this.value = value;
    this.unit = unit;
  }

  public Double getValue() {
    return value;
  }

  public String getUnit() {
    return unit;
  }
}