package ar.edu.utn.frba.dds;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.CargaMasivaException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MigradorColaboraciones {
  private AdministradorColaboradores administradorColaboradores;

  public MigradorColaboraciones(AdministradorColaboradores administradorColaboradores) {
    this.administradorColaboradores = administradorColaboradores;
  }

  public void insertarColaboracionesHistoricas(FileReader reader, char caracterCentinela) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      CSVParser parser = new CSVParserBuilder().withSeparator(caracterCentinela).build();
      CSVReader csvReader = new CSVReaderBuilder(reader)
                                  .withCSVParser(parser)
                                  .withSkipLines(1)
                                  .build();

      String[] registro;
      int fila = 1;
      while ((registro = csvReader.readNext()) != null) {
        if (registro.length != 8) {
          throw new CargaMasivaException("La cantidad de registros de la fila [" + fila + "] es "
              + "inválida.");
        }
        TipoDocumento tipoDoc;
        int documento;

        // Agrego los try-catch para encapsular la excepción en una de más alto nivel
        // que sea más expresiva para el usuario.
        try {
          tipoDoc = TipoDocumento.valueOf(registro[0]);
          documento = Integer.parseInt(registro[1]);
        } catch (NumberFormatException exception) {
          throw new CargaMasivaException("El número de documento [" + registro[1] + "] es "
              + "inválido. Fila: " + fila);
        } catch (IllegalArgumentException exception) {
          throw new CargaMasivaException("El tipo de documento [" + registro[0] + "] es inválido");
        }
        String nombre = registro[2];
        String apellido = registro[3];
        String mail = registro[4];
        LocalDate fechaColaboracion;
        TipoColaboracionHistorica tipoColaboracion;
        int cantidad;
        // Agrego los try-catch para encapsular la excepción en una de más alto nivel
        // que sea más expresiva para el usuario.
        try {
          fechaColaboracion = LocalDate.parse(registro[5], formatter);
          tipoColaboracion = TipoColaboracionHistorica.valueOf(registro[6]);
          cantidad = Integer.parseInt(registro[7]);
        } catch (DateTimeParseException exception) {
          throw new CargaMasivaException("La fecha de colaboración [" + registro[5] + "] es "
              + "inválida. El formato debe ser DD/MM/AAAA. Fila: " + fila);
        } catch (NumberFormatException exception) {
          throw new CargaMasivaException("La cantidad [" + registro[7] + "] es inválida. Fila: "
              + fila);
        } catch (IllegalArgumentException exception) {
          throw new CargaMasivaException("El tipo de colaboración [" + registro[6] + "] es "
              + "inválido. Fila: " + fila);
        }
        if (!mail.matches("^.+@.+.[a-z]+$")) {
          throw new CargaMasivaException("El email [" + mail + "] es inválido. Fila: " + fila);
        }

        // Buscamos al colaborador que mencionado en el CSV y le agregamos la colaboracion histórica
        // Si el colaborador no existe se crea.
        new ColaboradorHistorico(tipoDoc, documento, nombre, apellido, mail)
            .obtenerColaboradorHumano(administradorColaboradores)
            .agregarColaboracionHistorica(new ColaboracionHistorica(
                cantidad,
                fechaColaboracion,
                tipoColaboracion
            ));

        fila++;
      }
      csvReader.close();
    } catch (IOException | CsvValidationException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}