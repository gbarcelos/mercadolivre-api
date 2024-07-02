package br.com.oak.mercadolivreapi.model.jpa;

import br.com.oak.mercadolivreapi.controller.request.CriarCaracteristicaRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Entity
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
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

  @NotNull  @Valid
  @ManyToOne
  private Categoria categoria;

  @NotNull  @Valid
  @ManyToOne
  private Usuario usuarioCriacao;

  @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
  private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  @Deprecated
  public Produto() {
  }

  public Produto(@NotBlank String nome,
      @NotNull @Positive @Digits(integer = 3, fraction = 2) BigDecimal valor,
      @NotNull @PositiveOrZero Integer quantidade,
      @NotBlank @Size(min = 1, max = 1000) String descricao, @NotNull @Valid Categoria categoria,
      @Valid @Size(min = 3) Collection<CriarCaracteristicaRequest> criarCaracteristicaRequest,
      @NotNull @Valid Usuario usuarioCriacao) {
    this.nome = nome;
    this.valor = valor;
    this.quantidade = quantidade;
    this.descricao = descricao;
    this.categoria = categoria;
    this.usuarioCriacao = usuarioCriacao;
    this.caracteristicas.addAll(criarCaracteristicaRequest.stream().map(caracteristica -> caracteristica.toModel(this))
        .collect(Collectors.toSet()));
    Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 caracteristicas");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Produto produto = (Produto) o;
    return Objects.equals(nome, produto.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome);
  }

  @Override
  public String toString() {
    return "Produto{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", valor=" + valor +
        ", quantidade=" + quantidade +
        ", descricao='" + descricao + '\'' +
        ", categoria=" + categoria +
        ", usuarioCriacao=" + usuarioCriacao +
        ", caracteristicas=" + caracteristicas +
        ", dataHoraCriacao=" + dataHoraCriacao +
        '}';
  }
}
