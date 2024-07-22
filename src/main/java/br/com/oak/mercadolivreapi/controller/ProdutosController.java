package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.AdicionaImagensRequest;
import br.com.oak.mercadolivreapi.controller.request.CriarProdutoRequest;
import br.com.oak.mercadolivreapi.controller.response.DetalhaProdutoResponse;
import br.com.oak.mercadolivreapi.controller.response.ProdutoResponse;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.storage.Uploader;
import br.com.oak.mercadolivreapi.validator.RestringeCaracteristicasComNomesIguaisValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private Uploader uploader;

  @InitBinder(value = "criarProdutRequest")
  public void init(WebDataBinder binder) {
    binder.addValidators(new RestringeCaracteristicasComNomesIguaisValidator());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProdutoResponse> criarProduto(
      @RequestBody @Valid CriarProdutoRequest criarProdutRequest, @AuthenticationPrincipal
  UsuarioLogado usuarioLogado) {
    Produto produto = criarProdutRequest.toModel(usuarioLogado.get());
    entityManager.persist(produto);
    return ResponseEntity.ok().body(new ProdutoResponse(produto));
  }

  @Transactional
  @PostMapping(value = "/{id}/imagens")
  public ResponseEntity<ProdutoResponse> adicionaImagens(@PathVariable("id") Long id,
      @Valid AdicionaImagensRequest request,
      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
    Produto produto = entityManager.find(Produto.class, id);

    if (!produto.pertenceAoUsuario(usuarioLogado.get())){
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    Set<String> links = uploader.enviar(request.getImagens());
    produto.adicionaImagens(links);
    entityManager.merge(produto);

    return ResponseEntity.ok().body(new ProdutoResponse(produto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhaProdutoResponse> detalhaProduto(@PathVariable Long id) {
    Produto produto = entityManager.find(Produto.class, id);
    return ResponseEntity.ok().body(new DetalhaProdutoResponse(produto));
  }
}
