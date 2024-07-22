package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.jpa.ImagemProduto;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import java.math.BigDecimal;
import java.util.Set;

public class ProdutoResponse {

  private final Long id;
  private final String nome;
  private final String descricao;
  private final BigDecimal valor;
  private final Set<CaracteristicaResponse> caracteristicas;
  private final Set<String> imagens;

  public ProdutoResponse(Produto produto) {
    this.id = produto.getId();
    this.nome = produto.getNome();
    this.descricao = produto.getDescricao();
    this.valor = produto.getValor();
    this.caracteristicas = produto.mapCaracteristicas(
        CaracteristicaResponse::new);
    this.imagens = produto.mapImagens(ImagemProduto::getLink);
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
}
