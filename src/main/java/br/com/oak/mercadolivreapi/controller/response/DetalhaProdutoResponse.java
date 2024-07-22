package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.Avaliacoes;
import br.com.oak.mercadolivreapi.model.jpa.Avaliacao;
import br.com.oak.mercadolivreapi.model.jpa.ImagemProduto;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

public class DetalhaProdutoResponse {

  private final Long id;
  private final String nome;
  private final String descricao;
  private final BigDecimal valor;
  private final Set<CaracteristicaResponse> caracteristicas;
  private final Set<String> imagens;
  private final Set<PerguntaResponse> perguntas;
  private final int totalAvaliacoes;
  private final Set<Map<String, String>> avaliacoes;
  private double mediaNotas;

  public DetalhaProdutoResponse(Produto produto) {
    this.id = produto.getId();
    this.nome = produto.getNome();
    this.descricao = produto.getDescricao();
    this.valor = produto.getValor();
    this.caracteristicas = produto.mapCaracteristicas(
        CaracteristicaResponse::new);
    this.imagens = produto.mapImagens(ImagemProduto::getLink);
    this.perguntas = produto.mapPerguntas(PerguntaResponse::new);

    Avaliacoes avaliacoes = produto.getAvaliacoes();
    this.avaliacoes = avaliacoes.mapAvaliacoes(
        avaliacao -> Map.of("descricao",
            avaliacao.getDescricao(), "titulo", avaliacao.getTitulo()));
    this.totalAvaliacoes = avaliacoes.totalAvaliacoes();
    this.mediaNotas = avaliacoes.mediaNotas();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public Set<CaracteristicaResponse> getCaracteristicas() {
    return caracteristicas;
  }

  public Set<String> getImagens() {
    return imagens;
  }

  public Set<PerguntaResponse> getPerguntas() {
    return perguntas;
  }

  public Set<Map<String, String>> getAvaliacoes() {
    return avaliacoes;
  }

  public int getTotalAvaliacoes() {
    return totalAvaliacoes;
  }

  public double getMediaNotas() {
    return mediaNotas;
  }
}
