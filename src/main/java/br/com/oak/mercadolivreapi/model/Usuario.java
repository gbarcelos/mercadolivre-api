package br.com.oak.mercadolivreapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.util.Assert;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Email
  private String login;

  @NotBlank
  private String senha;

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @Deprecated
  public Usuario() {
  }

  public Usuario(@NotBlank @Email String login,
      @Valid @NotNull SenhaPlainText senhaPlainText) {
    Assert.hasLength(login, "Login não deve estar em branco");
    Assert.notNull(senhaPlainText, "O Objeto do tipo senha plain text não deve ser nulo");
    this.login = login;
    this.senha = senhaPlainText.hash();
  }

  @Override
  public String toString() {
    return "Usuario{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", dataHoraCriacao=" + dataHoraCriacao +
        '}';
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getSenha() {
    return senha;
  }

  public LocalDateTime getDataHoraCriacao() {
    return dataHoraCriacao;
  }
}
