package br.com.oak.mercadolivreapi.model;

import br.com.oak.mercadolivreapi.model.jpa.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
  PAYPAL {
    @Override
    public String criaUrlRetorno(Compra compra,
        UriComponentsBuilder uriComponentsBuilder) {
      var urlRetornoPagseguro = uriComponentsBuilder
          .path("/retorno-pagseguro/{id}")
          .buildAndExpand(compra.getIdentificador()).toString();

      return "pagseguro.com/" + compra.getIdentificador() + "?redirectUrl="
          + urlRetornoPagseguro;
    }
  },
  PAGSEGURO {
    @Override
    public String criaUrlRetorno(Compra compra,
        UriComponentsBuilder uriComponentsBuilder) {
      var urlRetornoPaypal = uriComponentsBuilder
          .path("/retorno-paypal/{id}").buildAndExpand(compra.getIdentificador())
          .toString();

      return "paypal.com/" + compra.getIdentificador() + "?redirectUrl=" + urlRetornoPaypal;
    }
  };

  public abstract String criaUrlRetorno(Compra compra,
      UriComponentsBuilder uriComponentsBuilder);
}
