package br.com.oak.mercadolivreapi.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String titulo;

  @Valid
  @NotNull
  @ManyToOne
  private Produto produto;

  @NotNull  @Valid
  @ManyToOne
  private Usuario usuario;

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @Deprecated
  public Pergunta() {
  }

  public Pergunta(String titulo, Produto produto, Usuario usuario) {
    this.titulo = titulo;
    this.produto = produto;
    this.usuario = usuario;
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public Produto getProduto() {
    return produto;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public LocalDateTime getDataHoraCriacao() {
    return dataHoraCriacao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pergunta pergunta = (Pergunta) o;
    return Objects.equals(titulo, pergunta.titulo) && Objects.equals(produto,
        pergunta.produto) && Objects.equals(usuario, pergunta.usuario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titulo, produto, usuario);
  }
}
