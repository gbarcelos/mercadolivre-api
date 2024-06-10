package br.com.oak.mercadolivreapi.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SenhaPlainTextTest {

  @DisplayName("Deve aceitar sennha com pelo menos 6 caracteres")
  @ParameterizedTest
  @CsvSource({
      "123456", "1234567", "545454545454555454"
  })
  void deveAceitarSenhaCommaisDeSeisCaracteres(String senhalimpa) {
    new SenhaPlainText(senhalimpa);
  }

  @DisplayName("Não deve aceitar sennha com menos de 6 caracteres")
  @ParameterizedTest
  @CsvSource({
      "12345", "12"
  })
  void naoDeveAceitarSenhaComMenosCaracteres(String senhalimpa) {
    assertThrows(IllegalArgumentException.class, () -> new SenhaPlainText(senhalimpa));
  }

}