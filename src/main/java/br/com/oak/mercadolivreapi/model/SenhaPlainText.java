package br.com.oak.mercadolivreapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaPlainText {

  private final String senha;

  public SenhaPlainText(@NotBlank @Size(min = 6) String senha) {
    Assert.hasLength(senha, "Senha não deve estar em branco");
    Assert.isTrue(senha.length() >= 6, "Senha deve ter pelo menos 6 caracteres");
    this.senha = new BCryptPasswordEncoder().encode(senha);
  }

  public String hash() {
    return this.senha;
  }
}
