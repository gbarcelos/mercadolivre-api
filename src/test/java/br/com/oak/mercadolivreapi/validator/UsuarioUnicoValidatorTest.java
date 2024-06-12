package br.com.oak.mercadolivreapi.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.oak.mercadolivreapi.controller.request.CriarUsuarioRequest;
import br.com.oak.mercadolivreapi.model.Usuario;
import br.com.oak.mercadolivreapi.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;

@ExtendWith(MockitoExtension.class)
public class UsuarioUnicoValidatorTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @InjectMocks
  private UsuarioUnicoValidator usuarioUnicoValidator;

  @Test
  @DisplayName("Deve retornar erro quando o login já estiver cadastrado")
  void deveRetornarErroQuandoLoginJaCadastrado() {
    // Dado
    when(usuarioRepository.findByLogin(any()))
        .thenReturn(new Usuario());

    final var criarUsuarioRequest = new CriarUsuarioRequest("login", "senha");
    final var errors = new BeanPropertyBindingResult(criarUsuarioRequest, "criarUsuarioRequest");

    // Quando
    usuarioUnicoValidator.validate(criarUsuarioRequest, errors);

    // Entao
    assertTrue(errors.hasErrors());
  }

  @Test
  @DisplayName("Não deve retornar erro quando o login não está cadastrado")
  void naoDeveRetornarErroQuandoLoginNaoCadastrado() {
    // Dado
    when(usuarioRepository.findByLogin(any()))
        .thenReturn(null);

    final var criarUsuarioRequest = new CriarUsuarioRequest("login", "senha");
    final var errors = new BeanPropertyBindingResult(criarUsuarioRequest, "criarUsuarioRequest");

    // Quando
    usuarioUnicoValidator.validate(criarUsuarioRequest, errors);

    // Entao
    assertFalse(errors.hasErrors());
  }
}
