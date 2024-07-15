package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.AvaliarProdutoRequest;
import br.com.oak.mercadolivreapi.controller.response.AvaliacaoResponse;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.model.jpa.Avaliacao;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/{id}/avaliacao")
public class AvaliacoesController {

  @PersistenceContext
  private EntityManager entityManager;

  @PostMapping
  @Transactional
  public ResponseEntity<AvaliacaoResponse> avaliarProduto(@PathVariable("id") Long id,
      @RequestBody @Valid AvaliarProdutoRequest request,
      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
    Produto produto = entityManager.find(Produto.class, id);
    Avaliacao avaliacao = request.toModel(produto, usuarioLogado.get());

    entityManager.persist(avaliacao);

    return ResponseEntity.ok().body(new AvaliacaoResponse(avaliacao));
  }
}
