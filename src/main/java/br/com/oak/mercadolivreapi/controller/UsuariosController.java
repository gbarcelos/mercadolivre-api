package br.com.oak.mercadolivreapi.controller;

import br.com.oak.mercadolivreapi.controller.request.CriarUsuarioRequest;
import br.com.oak.mercadolivreapi.model.Usuario;
import br.com.oak.mercadolivreapi.repository.UsuarioRepository;
import br.com.oak.mercadolivreapi.validator.UsuarioUnicoValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private UsuarioUnicoValidator usuarioUnicoValidator;

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(usuarioUnicoValidator);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Usuario> criarUsuario(
      @RequestBody @Valid CriarUsuarioRequest criarUsuarioRequest) {
    Usuario usuario = criarUsuarioRequest.toModel();
    usuarioRepository.save(usuario);
    return ResponseEntity.ok().body(usuario);
  }
}
