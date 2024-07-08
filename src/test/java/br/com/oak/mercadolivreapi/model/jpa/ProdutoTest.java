package br.com.oak.mercadolivreapi.model.jpa;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.oak.mercadolivreapi.controller.request.CriarCaracteristicaRequest;
import br.com.oak.mercadolivreapi.model.SenhaPlainText;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProdutoTest {

  @ParameterizedTest
  @MethodSource("geradorDeTresOuMaisCaracteristicas")
  @DisplayName("Deve criar produto com pelo menos três características")
  public void deveCriarProdutoComTresOuMaisCaracteristicas(Collection<CriarCaracteristicaRequest> caracteristicas) {
    Categoria categoriaTeste = new Categoria("Categoria teste");
    Usuario usuario = new Usuario("email@test.com", new SenhaPlainText("senha123456"));

    assertDoesNotThrow(
        () -> {
          new Produto("nome do produto",
              new BigDecimal("10.5"),
              10,
              "descricao produto",
              categoriaTeste,
              caracteristicas,
              usuario);
        });
  }

  @ParameterizedTest
  @MethodSource("geradorDeMenosDeTresCaracteristicas")
  @DisplayName("Deve recusar criação de produto com menos de três características")
  public void deveRecusarProdutoComMenosDeTresCaracteristicas(Collection<CriarCaracteristicaRequest> caracteristicas) {
    Categoria categoriaTeste = new Categoria("Categoria teste");
    Usuario usuario = new Usuario("email@test.com", new SenhaPlainText("senha123456"));

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new Produto("nome do produto",
              new BigDecimal("10.5"),
              10,
              "descricao produto",
              categoriaTeste,
              caracteristicas,
              usuario);
        });
  }

  private static Stream<Arguments> geradorDeTresOuMaisCaracteristicas() {
    return Stream.of(
        Arguments.of(List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"),
            new CriarCaracteristicaRequest("nome 2", "descricao 2"),
            new CriarCaracteristicaRequest("nome 3", "descricao 3"))),
        Arguments.of(List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"),
            new CriarCaracteristicaRequest("nome 2", "descricao 2"),
            new CriarCaracteristicaRequest("nome 3", "descricao 3"),
            new CriarCaracteristicaRequest("nome 4", "descricao 4"))));
  }

  private static Stream<Arguments> geradorDeMenosDeTresCaracteristicas() {
    return Stream.of(
        Arguments.of(List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"),
            new CriarCaracteristicaRequest("nome 2", "descricao 2"))),
        Arguments.of(List.of(new CriarCaracteristicaRequest("nome 1", "descricao 1"))));
  }

}