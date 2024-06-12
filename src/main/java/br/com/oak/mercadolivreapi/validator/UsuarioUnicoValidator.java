package br.com.oak.mercadolivreapi.validator;

import br.com.oak.mercadolivreapi.controller.request.CriarUsuarioRequest;
import br.com.oak.mercadolivreapi.model.Usuario;
import br.com.oak.mercadolivreapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsuarioUnicoValidator implements Validator {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return CriarUsuarioRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CriarUsuarioRequest request = (CriarUsuarioRequest) target;

    if (!StringUtils.hasText(request.getLogin())) {
      return;
    }

    Usuario usuario = usuarioRepository.findByLogin(request.getLogin());
    if (usuario != null) {
      errors.rejectValue("login", "UniqueValue.criarUsuarioRequest.login");
    }
  }
}
