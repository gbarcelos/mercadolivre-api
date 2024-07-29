package br.com.oak.mercadolivreapi.model.jpa;

import br.com.oak.mercadolivreapi.model.GatewayPagamento;
import br.com.oak.mercadolivreapi.model.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.web.util.UriComponentsBuilder;

@Entity
public class Compra {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Valid
  @NotNull
  @ManyToOne
  private Usuario cliente;

  @Valid
  @NotNull
  @ManyToOne
  private Produto produto;

  @NotNull
  @PositiveOrZero
  private Integer quantidade;

  @NotNull
  private GatewayPagamento gatewayPagamento;

  @Enumerated(EnumType.STRING)
  private Status status = Status.INICIADA;

  @NotBlank
  private String identificador;

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @Deprecated
  public Compra() {
  }

  public Compra(@NotNull Usuario cliente, @NotNull Produto produto, @NotNull
  @PositiveOrZero Integer quantidade,
      @NotNull GatewayPagamento gatewayPagamento) {
    this.cliente = cliente;
    this.produto = produto;
    this.quantidade = quantidade;
    this.gatewayPagamento = gatewayPagamento;
    this.identificador = UUID.randomUUID().toString();
  }

  public Long getId() {
    return id;
  }

  public Usuario getCliente() {
    return cliente;
  }

  public Produto getProduto() {
    return produto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public GatewayPagamento getGatewayPagamento() {
    return gatewayPagamento;
  }

  public Status getStatus() {
    return status;
  }

  public LocalDateTime getDataHoraCriacao() {
    return dataHoraCriacao;
  }

  public String getIdentificador() {
    return identificador;
  }

  public String getUrlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
    return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
  }
}
