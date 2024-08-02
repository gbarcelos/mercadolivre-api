package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.StatusTransacao;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import br.com.oak.mercadolivreapi.model.jpa.Transacao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

  @NotBlank
  private String idTransacao;

  @Min(0)
  @Max(1)
  private int status;

  public RetornoPaypalRequest(@NotBlank String idTransacao, @Min(0) @Max(1) int status) {
    this.idTransacao = idTransacao;
    this.status = status;
  }

  @Override
  public Transacao toTransacao(Compra compra) {
    StatusTransacao statusTransacao = status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;
    return new Transacao(idTransacao, statusTransacao, compra);
  }
}
