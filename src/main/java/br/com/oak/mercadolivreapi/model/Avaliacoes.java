package br.com.oak.mercadolivreapi.model;

import br.com.oak.mercadolivreapi.model.jpa.Avaliacao;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Avaliacoes {

  private final Set<Avaliacao> avaliacoes;

  public Avaliacoes(Set<Avaliacao> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  public  <T> Set<T> mapAvaliacoes(Function<Avaliacao, T> mapper) {
    return this.avaliacoes.stream().map(mapper).collect(Collectors.toSet());
  }

  public int totalAvaliacoes() {
    return this.avaliacoes.size();
  }

  public double mediaNotas() {
    Set<Integer> notas = mapAvaliacoes(
        Avaliacao::getNota);
    OptionalDouble average = notas.stream().mapToInt(nota -> nota).average();
    return average.orElse(0.0);
  }
}
