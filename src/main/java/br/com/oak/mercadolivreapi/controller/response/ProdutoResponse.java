package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.jpa.Produto;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoResponse {

  private final Long id;
  private final String nome;
  private final Set<String> imagens;

  public ProdutoResponse(Produto produto) {
    this.id = produto.getId();
    this.nome = produto.getNome();
    this.imagens = produto.getImagens().stream().map(imagemProduto -> imagemProduto.getLink()).collect(Collectors.toSet());
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Set<String> getImagens() {
    return imagens;
  }
}
