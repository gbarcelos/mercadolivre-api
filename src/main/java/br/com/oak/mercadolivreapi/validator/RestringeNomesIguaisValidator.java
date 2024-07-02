package br.com.oak.mercadolivreapi.validator;

import br.com.oak.mercadolivreapi.controller.request.CriarProdutRequest;
import java.util.Set;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestringeNomesIguaisValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return CriarProdutRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CriarProdutRequest request = (CriarProdutRequest) target;

    Set<String> nomesIguais = request.buscaCaracteristicasIguais();
    if (!nomesIguais.isEmpty()){
      errors.rejectValue("caracteristicas", null,"Possui caracteristica igual: " + nomesIguais);
    }
  }
}
