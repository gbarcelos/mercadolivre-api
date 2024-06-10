package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.CriarUsuarioRequest;
import br.com.oak.mercadolivreapi.model.Usuario;
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
@RequestMapping("/usuarios")
public class UsuariosController {

  @PersistenceContext
  private EntityManager entityManager;

  @PostMapping
  @Transactional
  public ResponseEntity<Usuario> criarUsuario(
      @RequestBody @Valid CriarUsuarioRequest criarUsuarioRequest) {
    Usuario usuario = criarUsuarioRequest.toModel();
    entityManager.persist(usuario);
    return ResponseEntity.ok().body(usuario);
  }
}
