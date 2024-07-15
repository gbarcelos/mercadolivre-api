package br.com.oak.mercadolivreapi.validator;

import br.com.oak.mercadolivreapi.controller.request.CriarProdutoRequest;
import java.util.Set;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestringeCaracteristicasComNomesIguaisValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return CriarProdutoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CriarProdutoRequest request = (CriarProdutoRequest) target;

    Set<String> nomesIguais = request.buscaCaracteristicasIguais();
    if (!nomesIguais.isEmpty()){
      errors.rejectValue("caracteristicas", null,"Possui caracteristica igual: " + nomesIguais);
    }
  }
}
