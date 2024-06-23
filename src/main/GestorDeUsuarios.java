package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class GestorDeUsuarios {
  List<Usuario> usuarios = new ArrayList<>();
  ValidadorClaves validadorClaves;

  public GestorDeUsuarios(ValidadorClaves validadorClaves) {
    if (validadorClaves == null) {
      throw new IllegalArgumentException("El validador de claves no puede ser null");
    }
    this.validadorClaves = validadorClaves;
  }

  public Usuario agregarUsuario(String usuario, String clave) {
    this.validadorClaves.validarClave(clave);
    byte[] salt = this.validadorClaves.generateSalt();
    Usuario nuevoUsuario = new Usuario(usuario, ValidadorClaves.hashPassword(
        clave,
        salt,
        10000,
        512),
        salt);
    this.usuarios.add(nuevoUsuario);
    return nuevoUsuario;
  }

}
