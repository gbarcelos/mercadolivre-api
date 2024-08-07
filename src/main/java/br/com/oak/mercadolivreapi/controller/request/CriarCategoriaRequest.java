package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.annotation.ExistsValue;
import br.com.oak.mercadolivreapi.annotation.UniqueValue;
import br.com.oak.mercadolivreapi.model.jpa.Categoria;
import jakarta.validation.constraints.NotBlank;

public class CriarCategoriaRequest {

  @ExistsValue(domainClass = Categoria.class)
  private Long categoriaPaiId;

  @NotBlank
  @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
  private String nome;

  @Deprecated
  public CriarCategoriaRequest() {
  }

  public CriarCategoriaRequest(Long categoriaPaiId, @NotBlank String nome) {
    this.categoriaPaiId = categoriaPaiId;
    this.nome = nome;
  }

  public Long getCategoriaPaiId() {
    return categoriaPaiId;
  }

  public String getNome() {
    return nome;
  }

  public Categoria toModel() {
    Categoria categoria = new Categoria(this.nome);
    if (categoriaPaiId != null) {
      categoria.setCategoriaPai(new Categoria(this.categoriaPaiId));
    }
    return categoria;
  }
}
