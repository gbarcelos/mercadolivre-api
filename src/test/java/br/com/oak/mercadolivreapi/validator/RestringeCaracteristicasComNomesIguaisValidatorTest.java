package br.com.oak.mercadolivreapi.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.oak.mercadolivreapi.controller.request.CriarCaracteristicaRequest;
import br.com.oak.mercadolivreapi.controller.request.CriarProdutRequest;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;

class RestringeCaracteristicasComNomesIguaisValidatorTest {

  @Test
  @DisplayName("Deve retornar erro quando existe características iguais")
  public void deveRetornarErroQuandoExisteCaracteristicasIguais(){
    // Dado
    List<CriarCaracteristicaRequest> caracteristicas = List.of(
        new CriarCaracteristicaRequest("nome 1", "descricao 1"),
        new CriarCaracteristicaRequest("nome 1", "descricao 1"));

    CriarProdutRequest criarProdutRequest = new CriarProdutRequest("nome do produto",
        new BigDecimal("10.5"),
        10,
        "descricao produto",
        1L,
        caracteristicas);

    final var errors = new BeanPropertyBindingResult(criarProdutRequest, "criarProdutRequest");

    // Quando
    RestringeCaracteristicasComNomesIguaisValidator validator = new RestringeCaracteristicasComNomesIguaisValidator();
    validator.validate(criarProdutRequest, errors);

    // Entao
    assertTrue(errors.hasErrors());
  }

  @Test
  @DisplayName("Nao deve retornar erro quando existe características diferentes")
  public void naoDeveRetornarErroQuandoExisteCaracteristicasDiferente(){
    // Dado
    List<CriarCaracteristicaRequest> caracteristicas = List.of(
        new CriarCaracteristicaRequest("nome 1", "descricao 1"),
        new CriarCaracteristicaRequest("nome 2", "descricao 2"));

    CriarProdutRequest criarProdutRequest = new CriarProdutRequest("nome do produto",
        new BigDecimal("10.5"),
        10,
        "descricao produto",
        1L,
        caracteristicas);

    final var errors = new BeanPropertyBindingResult(criarProdutRequest, "criarProdutRequest");

    // Quando
    RestringeCaracteristicasComNomesIguaisValidator validator = new RestringeCaracteristicasComNomesIguaisValidator();
    validator.validate(criarProdutRequest, errors);

    // Entao
    assertFalse(errors.hasErrors());
  }

}