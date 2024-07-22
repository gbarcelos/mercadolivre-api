package br.com.oak.mercadolivreapi.controller.response;

import br.com.oak.mercadolivreapi.model.jpa.Pergunta;
import java.time.format.DateTimeFormatter;

public class PerguntaResponse {

  private final Long id;
  private final String titulo;
  private final String loginUsuario;
  private final String dataHoraCriacao;

  public PerguntaResponse(Pergunta pergunta) {
    this.id = pergunta.getId();
    this.titulo = pergunta.getTitulo();
    this.loginUsuario = pergunta.getUsuario().getLogin();
    this.dataHoraCriacao = pergunta.getDataHoraCriacao()
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getLoginUsuario() {
    return loginUsuario;
  }

  public String getDataHoraCriacao() {
    return dataHoraCriacao;
  }
}
