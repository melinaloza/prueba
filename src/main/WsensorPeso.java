package ar.edu.utn.frba.dds;

class WsensorPeso implements SensorPeso {
  private Wsensor sensorPeso;

  public WsensorPeso(Wsensor sensorPeso) {
    this.sensorPeso = sensorPeso;
  }

  public Double getPeso(String serialNumber) {
    Reading reading = sensorPeso.getWeight(serialNumber);

    if (reading.getUnit().equals("lbs")) {
      return reading.getValue() * 0.453592;
    }
    return reading.getValue();
  }
}
