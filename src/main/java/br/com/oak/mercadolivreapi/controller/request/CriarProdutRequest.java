package br.com.oak.mercadolivreapi.controller.request;

import br.com.oak.mercadolivreapi.annotation.ExistsValue;
import br.com.oak.mercadolivreapi.annotation.UniqueValue;
import br.com.oak.mercadolivreapi.model.jpa.Categoria;
import br.com.oak.mercadolivreapi.model.jpa.Produto;
import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CriarProdutRequest {

  @NotBlank
  @UniqueValue(domainClass = Produto.class, fieldName = "nome")
  private String nome;

  @NotNull
  @Positive
  @Digits(integer = 3, fraction = 2)
  private BigDecimal valor;

  @NotNull
  @PositiveOrZero
  private Integer quantidade;

  @NotBlank
  @Size(min = 1, max = 1000)
  private String descricao;

  @NotNull
  @ExistsValue(domainClass = Categoria.class)
  private Long categoriaId;

  @Valid
  @Size(min = 3)
  private List<CriarCaracteristicaRequest> caracteristicas = new ArrayList<>();

  @Deprecated
  public CriarProdutRequest() {
  }

  public CriarProdutRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
      Long categoriaId, List<CriarCaracteristicaRequest> caracteristicas) {
    this.nome = nome;
    this.valor = valor;
    this.quantidade = quantidade;
    this.descricao = descricao;
    this.categoriaId = categoriaId;
    this.caracteristicas.addAll(caracteristicas);
  }

  public Produto toModel(Usuario usuarioCriacao) {
    return new Produto(nome,
        valor,
        quantidade,
        descricao,
        new Categoria(categoriaId),
        caracteristicas,
        usuarioCriacao);
  }

  public Set<String> buscaCaracteristicasIguais() {
    HashSet<String> nomesIguais = new HashSet<>();
    HashSet<String> resultado = new HashSet<>();
    for (CriarCaracteristicaRequest criarCaracteristicaRequest : caracteristicas){
      String nome = criarCaracteristicaRequest.getNome();
      if (!nomesIguais.add(nome)){
          resultado.add(nome);
        }
    }
    return resultado;
  }

  public List<CriarCaracteristicaRequest> getCaracteristicas() {
    return caracteristicas;
  }
}
