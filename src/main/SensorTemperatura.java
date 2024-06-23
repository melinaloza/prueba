package ar.edu.utn.frba.dds;


import java.util.function.Consumer;

public interface SensorTemperatura {
  void setCallback(Consumer<Double> callback);
}



