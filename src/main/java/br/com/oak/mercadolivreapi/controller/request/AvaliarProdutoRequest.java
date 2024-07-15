package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.jpa.Avaliacao;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AvaliarProdutoRequest {

  @NotNull
  @Min(value = 1)
  @Max(value = 5)
  private Integer nota;

  @NotBlank
  private String titulo;

  @NotBlank
  @Size(min = 1, max = 500)
  private String descricao;

  public AvaliarProdutoRequest(Integer nota, String titulo, String descricao) {
    this.nota = nota;
    this.titulo = titulo;
    this.descricao = descricao;
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

  public Avaliacao toModel(Produto produto, Usuario usuario) {
    return new Avaliacao(nota, titulo, descricao, produto, usuario);
  }
}
