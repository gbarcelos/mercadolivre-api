package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.RetornoGatewayPagamento;
import br.com.oak.mercadolivreapi.controller.request.RetornoPagSeguroRequest;
import br.com.oak.mercadolivreapi.controller.request.RetornoPaypalRequest;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechamentoComprasController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @PostMapping(value = "/retorno-pagseguro/{id}")
  public void fechamentoPagSeguro(@PathVariable("id") Long id,
      @RequestBody @Valid RetornoPagSeguroRequest request) {
    processafechamentoCompra(id, request);
  }

  @Transactional
  @PostMapping(value = "/retorno-paypal/{id}")
  public void fechamentoPaypal(@PathVariable("id") Long id,
      @RequestBody @Valid RetornoPaypalRequest request) {
    processafechamentoCompra(id, request);
  }

  private void processafechamentoCompra(Long idCompra, RetornoGatewayPagamento request){
    var compra = entityManager.find(Compra.class, idCompra);
    compra.adicionaTransacao(request);

    if (compra.processadaComSucesso()){
      //TODO: Integrar com NF
      //TODO: Integrar com raking
      //TODO: Enviar email para comprador
    } else {
      //TODO: Enviar email para comprador informando falha
    }

    entityManager.merge(compra);
  }

}
