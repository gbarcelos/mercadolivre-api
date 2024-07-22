package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.jpa.CaracteristicaProduto;

public class CaracteristicaResponse {

  private final String nome;
  private final String descricao;

  public CaracteristicaResponse(CaracteristicaProduto caracteristicaProduto) {
    this.nome = caracteristicaProduto.getNome();
    this.descricao = caracteristicaProduto.getDescricao();
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }
}
