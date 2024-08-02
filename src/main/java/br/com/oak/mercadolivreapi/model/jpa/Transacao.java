package br.com.oak.mercadolivreapi.model.jpa;

import br.com.oak.mercadolivreapi.model.StatusTransacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String idTransacaoGateway;

  @NotNull
  private StatusTransacao statusTransacao;

  @Valid
  @NotNull
  @ManyToOne
  private Compra compra;

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @Deprecated
  public Transacao() {
  }

  public Transacao(@NotBlank String idTransacao, @NotNull StatusTransacao statusTransacao,
      @NotNull @Valid Compra compra) {
    this.idTransacaoGateway = idTransacao;
    this.statusTransacao = statusTransacao;
    this.compra = compra;
  }

  public boolean concluidaComSucesso() {
    return this.statusTransacao.equals(StatusTransacao.SUCESSO);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transacao transacao = (Transacao) o;
    return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTransacaoGateway);
  }
}
