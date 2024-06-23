package ar.edu.utn.frba.dds;

import exceptions.ClaveException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;

public class ValidadorClaves {
  private List<String> clavesVulnerablesConocidas;
  private Set<Character> simbolosValidos;
  private Random randomGenerator = new Random();

  public ValidadorClaves(String nombreArchivoClavesVulnerables) {
    String caracteresEspecialesValidos = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    this.simbolosValidos = stringComoSet(caracteresEspecialesValidos);

    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/"
        + nombreArchivoClavesVulnerables))) {
      this.clavesVulnerablesConocidas = lines.toList();
    } catch (IOException e) {
      throw new RuntimeException("Error leyendo el archivo de claves vulnerables");
    }
  }

  public ValidadorClaves(String nombreArchivoClavesVulnerables, Set<Character> simbolosValidos) {
    this(nombreArchivoClavesVulnerables);

    if (simbolosValidos == null) {
      throw new IllegalArgumentException("La lista de simbolos válidos no puede ser null");
    }
    this.simbolosValidos = simbolosValidos;
  }

  public void validarClave(String clave) {
    this.validarLongitud(clave);
    this.validarQueContengaAlMenosUnaMayuscula(clave);
    this.validarQueContengaAlMenosUnaMinuscula(clave);
    this.validarQueContengaAlMenosUnNumero(clave);
    this.validarQueContengaAlMenosUnSimboloValido(clave);
    this.validarQueNoEsteEntreLasPeoresConocidas(clave);
  }

  protected void validarQueNoEsteEntreLasPeoresConocidas(String clave) {
    if (this.clavesVulnerablesConocidas.contains(clave)) {
      throw new ClaveException("La clave elegida se encuentra entre las 10k más vulnerables");
    }
  }

  protected void validarLongitud(String clave) {
    if (clave.length() < 8) {
      throw new ClaveException("La clave debe ser mayor a 8 caracteres");
    }
  }

  protected void validarQueContengaAlMenosUnaMayuscula(String clave) {
    if (!clave.matches(".*[A-Z].*")) {
      throw new ClaveException("La clave debe contener al menos una mayúscula");
    }
  }

  protected void validarQueContengaAlMenosUnaMinuscula(String clave) {
    if (!clave.matches(".*[a-z].*")) {
      throw new ClaveException("La clave debe contener al menos una minúscula");
    }
  }

  protected void validarQueContengaAlMenosUnNumero(String clave) {
    if (!clave.matches(".*\\d.*")) {
      throw new ClaveException("La clave debe contener al menos un número");
    }
  }

  private Set<Character> stringComoSet(String string) {
    return string.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toSet());
  }

  protected void validarQueContengaAlMenosUnSimboloValido(String clave) {
    Set<Character> claveComoConjunto = this.stringComoSet(clave);
    claveComoConjunto.retainAll(this.simbolosValidos);

    if (claveComoConjunto.isEmpty()) {
      throw new ClaveException(
          String.format("La clave debe contener al menos uno de los siguientes símbolos %s",
              this.simbolosValidos.toString()));
    }
  }

  public static String hashPassword(final String password, final byte[] salt, final int iterations,
                                    final int keyLength) {
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
      SecretKey key = skf.generateSecret(spec);
      byte[] hashedBytes = key.getEncoded();
      return Hex.encodeHexString(hashedBytes);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] generateSalt() {
    byte[] salt = new byte[7];
    this.randomGenerator.nextBytes(salt);
    return salt;
  }
}