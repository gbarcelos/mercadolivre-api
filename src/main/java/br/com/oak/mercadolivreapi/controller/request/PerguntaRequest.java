package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.model.jpa.Pergunta;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import jakarta.validation.constraints.NotBlank;

public class PerguntaRequest {

  @NotBlank
  private String titulo;

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Pergunta toModel(Produto produto, Usuario usuario) {
    return new Pergunta(titulo, produto, usuario);
  }
}
