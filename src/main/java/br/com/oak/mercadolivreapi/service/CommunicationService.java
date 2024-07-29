package br.com.oak.mercadolivreapi.service;

import br.com.oak.mercadolivreapi.email.EmailSender;
import br.com.oak.mercadolivreapi.model.Email;
import br.com.oak.mercadolivreapi.model.jpa.Compra;
import br.com.oak.mercadolivreapi.model.jpa.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationService {

  @Autowired
  private EmailSender emailSender;

  public void enviarComunicacaoPerguntaProduto(Pergunta pergunta) {
    var destinatario = "contato@meliapi.com";
    var remetente = pergunta.getUsuario().getLogin();
    var assunto = "Nova pergunta para o produto [%s]".formatted(pergunta.getProduto().getNome());
    var texto = pergunta.getTitulo();

    emailSender.enviar(new Email(destinatario, remetente, assunto, texto));
  }

  public void enviarComunicacaoNovaCompra(Compra compra) {
    var destinatario = compra.getProduto().getUsuarioCriacao().getLogin();
    var remetente = "contato@meliapi.com";
    var assunto = "Nova compra realizada";
    var texto = "Nova compra realizada para o produto [%s]".formatted(compra.getProduto().getNome());

    emailSender.enviar(new Email(destinatario, remetente, assunto, texto));
  }
}
