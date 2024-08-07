package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.jpa.CaracteristicaProduto;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarCaracteristicaRequest {

  @NotBlank
  private String nome;

  @NotBlank
  private String descricao;

  public CriarCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
    return new CaracteristicaProduto(nome, descricao, produto);
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }
}
