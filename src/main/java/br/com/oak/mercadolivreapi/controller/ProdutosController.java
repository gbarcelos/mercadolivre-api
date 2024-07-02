package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.CriarProdutRequest;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.validator.RestringeNomesIguaisValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

  @PersistenceContext
  private EntityManager entityManager;

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(new RestringeNomesIguaisValidator());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Produto> criarProduto(
      @RequestBody @Valid CriarProdutRequest criarProdutRequest, @AuthenticationPrincipal
      UsuarioLogado usuarioLogado) {
    Produto produto = criarProdutRequest.toModel(usuarioLogado.get());
    entityManager.persist(produto);
    return ResponseEntity.ok().body(produto);
  }
}
