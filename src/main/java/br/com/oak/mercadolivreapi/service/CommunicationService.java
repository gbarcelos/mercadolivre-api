package br.com.oak.mercadolivreapi.service;

import br.com.oak.mercadolivreapi.email.EmailSender;
import br.com.oak.mercadolivreapi.model.Email;
import br.com.oak.mercadolivreapi.model.jpa.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationService {

  @Autowired
  private EmailSender emailSender;

  public void enviarComunicacaoPerguntaProduto(Pergunta pergunta) {
    String destinatario = "contato@meliapi.com";
    String remetente = pergunta.getUsuario().getLogin();
    String assunto = "Nova pergunta para o produto [%s]".formatted(pergunta.getProduto().getNome());
    String texto = pergunta.getTitulo();

    emailSender.enviar(new Email(destinatario, remetente, assunto, texto));
  }
}
