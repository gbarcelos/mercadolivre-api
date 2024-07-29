package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.CriarCompraRequest;
import br.com.oak.mercadolivreapi.controller.response.CompraResponse;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.service.CommunicationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/compras")
public class ComprasController {

  private static final Logger LOG = LoggerFactory.getLogger(ComprasController.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private CommunicationService communicationService;

  @PostMapping
  @Transactional
  public ResponseEntity<CompraResponse> criarCompra(
      @RequestBody @Valid CriarCompraRequest criarCompraRequest,
      @AuthenticationPrincipal UsuarioLogado usuarioLogado,
      UriComponentsBuilder uriComponentsBuilder) throws BindException {

    var produto = entityManager.find(Produto.class, criarCompraRequest.getProdutoId());
    var estoqueAbatido = produto.abateEstoque(criarCompraRequest.getQuantidade());

    if (estoqueAbatido) {
      var compra = criarCompraRequest.toModel(usuarioLogado.get(), produto);
      entityManager.persist(compra);
      communicationService.enviarComunicacaoNovaCompra(compra);

      var url = compra.getUrlRedirecionamento(uriComponentsBuilder);
      LOG.info("Compra criada. Redirecionando para {}", url);

      return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

    var estoqueInsuficienteException = new BindException(criarCompraRequest,
        "criarCompraRequest");

    estoqueInsuficienteException.reject(null,
        "Não foi possível realizar a compra. Estoque insuficiente");

    throw estoqueInsuficienteException;
  }
}
