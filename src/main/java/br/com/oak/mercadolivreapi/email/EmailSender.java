package br.com.oak.mercadolivreapi.email;

import br.com.oak.mercadolivreapi.model.Email;

public interface EmailSender {
  void enviar(Email email);
}
