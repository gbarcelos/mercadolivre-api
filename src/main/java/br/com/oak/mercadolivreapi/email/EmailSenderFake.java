package br.com.oak.mercadolivreapi.email;

import br.com.oak.mercadolivreapi.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderFake implements EmailSender {

  private static final Logger LOG = LoggerFactory.getLogger(EmailSenderFake.class);

  @Override
  public void enviar(Email email) {
    LOG.info("Email sendo enviado: {}", email.toString());
  }
}
