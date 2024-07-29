package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.GatewayPagamento;
import br.com.oak.mercadolivreapi.model.Status;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import java.time.LocalDateTime;

public class CompraResponse {

  private Long id;

  private Long clienteId;

  private Long produtoId;

  private Integer quantidade;

  private GatewayPagamento gatewayPagamento;

  private Status status;

  private LocalDateTime dataHoraCriacao;

  public CompraResponse(Compra compra) {
    this.id = compra.getId();
    this.clienteId = compra.getCliente().getId();
    this.produtoId = compra.getProduto().getId();
    this.quantidade = compra.getQuantidade();
    this.gatewayPagamento = compra.getGatewayPagamento();
    this.status = compra.getStatus();
    this.dataHoraCriacao = compra.getDataHoraCriacao();
  }

  public Long getId() {
    return id;
  }

  public Long getClienteId() {
    return clienteId;
  }

  public Long getProdutoId() {
    return produtoId;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public GatewayPagamento getGatewayPagamento() {
    return gatewayPagamento;
  }

  public Status getStatus() {
    return status;
  }

  public LocalDateTime getDataHoraCriacao() {
    return dataHoraCriacao;
  }
}
