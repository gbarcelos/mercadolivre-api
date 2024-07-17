package br.com.oak.mercadolivreapi.model;

public class Email {

  private String destinatario;

  private String remetente;

  private String assunto;

  private String texto;

  public Email(String destinatario, String remetente, String assunto, String texto) {
    this.destinatario = destinatario;
    this.remetente = remetente;
    this.assunto = assunto;
    this.texto = texto;
  }

  @Override
  public String toString() {
    return "Email{" +
        "destinatario='" + destinatario + '\'' +
        ", remetente='" + remetente + '\'' +
        ", assunto='" + assunto + '\'' +
        ", texto='" + texto + '\'' +
        '}';
  }
}
