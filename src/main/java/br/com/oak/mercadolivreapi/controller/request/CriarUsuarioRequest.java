package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.SenhaPlainText;
import br.com.oak.mercadolivreapi.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CriarUsuarioRequest {

  @NotBlank
  @Email
  private String login;

  @NotBlank
  @Size(min = 6)
  private String senha;

  public CriarUsuarioRequest(@NotBlank @Email String login,
      @NotBlank @Size(min = 6) String senha) {
    this.login = login;
    this.senha = senha;
  }

  public Usuario toModel() {
    return new Usuario(this.login, new SenhaPlainText(this.senha));
  }
}
