package ar.edu.utn.frba.dds;

import java.util.function.Consumer;

class TsensorTemperatura implements SensorTemperatura, Action {
  private Tsensor sensorTemperatura;
  private Consumer<Double> callback;


  public TsensorTemperatura(Tsensor sensorTemperatura, String serialNumber) {
    this.sensorTemperatura = sensorTemperatura;
    this.sensorTemperatura.connect(serialNumber);
    this.sensorTemperatura.onTemperatureChange(this);
  }

  public void setCallback(Consumer<Double> callback) {
    this.callback = callback;
  }

  public void executeForTemperature(Double temperatura) {
    this.callback.accept(temperatura);
  }


}



