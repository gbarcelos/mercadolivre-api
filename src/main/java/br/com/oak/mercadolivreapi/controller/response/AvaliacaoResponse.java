package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.jpa.Avaliacao;

public class AvaliacaoResponse {

  private final Long id;
  private final Integer nota;
  private final String titulo;
  private final String descricao;
  private final String produto;
  private final String usuario;

  public AvaliacaoResponse(Avaliacao avaliacao) {
    this.id = avaliacao.getId();
    this.nota = avaliacao.getNota();
    this.titulo = avaliacao.getTitulo();
    this.descricao = avaliacao.getDescricao();
    this.produto = avaliacao.getProduto().getNome();
    this.usuario = avaliacao.getUsuario().getLogin();
  }

  public Long getId() {
    return id;
  }

  public Integer getNota() {
    return nota;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getProduto() {
    return produto;
  }

  public String getUsuario() {
    return usuario;
  }
}
