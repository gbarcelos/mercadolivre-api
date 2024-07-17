package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.PerguntaRequest;
import br.com.oak.mercadolivreapi.controller.response.PerguntaResponse;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.model.jpa.Pergunta;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.service.CommunicationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/{id}/pergunta")
public class PerguntasController {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private CommunicationService communicationService;

  @PostMapping
  @Transactional
  public ResponseEntity<PerguntaResponse> fazerPerguntaSobreProduto(@PathVariable("id") Long id,
      @RequestBody @Valid PerguntaRequest request,
      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
    Produto produto = entityManager.find(Produto.class, id);
    Pergunta pergunta = request.toModel(produto, usuarioLogado.get());
    entityManager.persist(pergunta);
    communicationService.enviarComunicacaoPerguntaProduto(pergunta);
    return ResponseEntity.ok().body(new PerguntaResponse(pergunta));
  }
}
