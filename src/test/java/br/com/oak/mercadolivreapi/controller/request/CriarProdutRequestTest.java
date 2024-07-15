package br.com.oak.mercadolivreapi.controller.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CriarProdutRequestTest {

  @ParameterizedTest
  @MethodSource("geradorCaracteristicas")
  @DisplayName("Deve criar produto com diversas características")
  public void deveCriarProdutoComDiversasCaracteristicas(int esperado,
      List<CriarCaracteristicaRequest> caracteristicas) {

    CriarProdutoRequest request = new CriarProdutoRequest("nome do produto",
        new BigDecimal("10.5"),
        10,
        "descricao produto",
        1L,
        caracteristicas);

    assertEquals(esperado, request.buscaCaracteristicasIguais().size());
  }

  private static Stream<Arguments> geradorCaracteristicas() {
    return Stream.of(
        Arguments.of(0, List.of()),
        Arguments.of(0, List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"))),
        Arguments.of(0, List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"),
            new CriarCaracteristicaRequest("nome 2", "descricao 2"))),
        Arguments.of(1, List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"),
            new CriarCaracteristicaRequest("nome 1", "descricao 1"))));
  }
}