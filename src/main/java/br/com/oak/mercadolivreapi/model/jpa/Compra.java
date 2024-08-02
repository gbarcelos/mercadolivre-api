package br.com.oak.mercadolivreapi.model.jpa;

import br.com.oak.mercadolivreapi.controller.request.RetornoGatewayPagamento;
import br.com.oak.mercadolivreapi.controller.request.RetornoPagSeguroRequest;
import br.com.oak.mercadolivreapi.model.GatewayPagamento;
import br.com.oak.mercadolivreapi.model.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.util.Assert;
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

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
  private Set<Transacao> transacoes = new HashSet<>();

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

  public String getUrlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
    return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
  }

  public void adicionaTransacao(@Valid RetornoGatewayPagamento retornoPagSeguroRequest) {
    Transacao novaTransacao = retornoPagSeguroRequest.toTransacao(this);

    Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada");

    Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Compra já foi concluída");

    this.transacoes.add(novaTransacao);
  }

  public boolean processadaComSucesso(){
    return !transacoesConcluidasComSucesso().isEmpty();
  }

  private Set<Transacao> transacoesConcluidasComSucesso() {
    return this.transacoes.stream().filter(Transacao::concluidaComSucesso)
        .collect(Collectors.toSet());
  }
}
