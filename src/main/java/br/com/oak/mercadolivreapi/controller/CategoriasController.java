package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.CriarCategoriaRequest;
import br.com.oak.mercadolivreapi.model.jpa.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

  @PersistenceContext
  private EntityManager entityManager;

  @PostMapping
  @Transactional
  public ResponseEntity<Categoria> criarCategoria(
      @RequestBody @Valid CriarCategoriaRequest criarCategoriaRequest) {
    Categoria categoria = criarCategoriaRequest.toModel();
    entityManager.persist(categoria);
    return ResponseEntity.ok().body(categoria);
  }
}
