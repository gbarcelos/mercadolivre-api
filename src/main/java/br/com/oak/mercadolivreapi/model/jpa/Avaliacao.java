package br.com.oak.mercadolivreapi.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Avaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Min(value = 1)
  @Max(value = 5)
  private Integer nota;

  @NotBlank
  private String titulo;

  @NotBlank
  @Size(min = 1, max = 500)
  private String descricao;

  @Valid
  @NotNull
  @ManyToOne
  private Produto produto;

  @Valid
  @NotNull
  @ManyToOne
  private Usuario usuario;

  @Deprecated
  public Avaliacao(){}

  public Avaliacao(Integer nota, String titulo, String descricao,
      Produto produto, Usuario usuario) {
    this.nota = nota;
    this.titulo = titulo;
    this.descricao = descricao;
    this.produto = produto;
    this.usuario = usuario;
  }

  public Long getId() {
    return id;
  }

  public Integer getNota() {
    return nota;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Produto getProduto() {
    return produto;
  }

  public Usuario getUsuario() {
    return usuario;
  }
}
