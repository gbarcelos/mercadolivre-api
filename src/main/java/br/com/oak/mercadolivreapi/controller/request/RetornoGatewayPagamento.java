package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.jpa.Compra;
import br.com.oak.mercadolivreapi.model.jpa.Transacao;

public interface RetornoGatewayPagamento {
  Transacao toTransacao(Compra compra);
}
