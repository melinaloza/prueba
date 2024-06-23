package ar.edu.utn.frba.dds;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Usuario {
  private String usuario;
  private byte[] salt;
  private String hash;

  // TODO: Mergear con el Administrador de usuarios
  public Usuario(String usuario, String hash, byte[] salt) {
    this.usuario = usuario;
    this.hash = hash;
    this.salt = salt;
  }

  public String getUsuario() {
    return usuario;
  }

  public String getHash() {
    return hash;
  }

  public byte[] getSalt() {
    return salt;
  }
}
