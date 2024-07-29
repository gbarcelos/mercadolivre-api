package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.annotation.ExistsValue;
import br.com.oak.mercadolivreapi.model.GatewayPagamento;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CriarCompraRequest {

  @NotNull
  @ExistsValue(domainClass = Produto.class)
  private Long produtoId;

  @NotNull
  @Positive
  private Integer quantidade;

  @NotNull
  private GatewayPagamento gatewayPagamento;

  public CriarCompraRequest(@NotNull Long produtoId, @NotNull Integer quantidade, @NotNull GatewayPagamento gatewayPagamento) {
    this.produtoId = produtoId;
    this.quantidade = quantidade;
    this.gatewayPagamento = gatewayPagamento;
  }

  public Long getProdutoId() {
    return produtoId;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public Compra toModel(Usuario cliente, Produto produto) {
    return new Compra(cliente, produto, quantidade, gatewayPagamento);
  }
}
