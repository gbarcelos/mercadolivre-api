package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.StatusRetornoPagSeguro;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import br.com.oak.mercadolivreapi.model.jpa.Transacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

  @NotBlank
  private String idTransacao;

  @NotNull
  private StatusRetornoPagSeguro status;

  public RetornoPagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
    this.idTransacao = idTransacao;
    this.status = status;
  }

  @Override
  public Transacao toTransacao(Compra compra) {
    return new Transacao(idTransacao, status.normaliza(), compra);
  }
}
