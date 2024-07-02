package br.com.oak.mercadolivreapi.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Getter;

@Getter
@Entity
public class CaracteristicaProduto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  private String descricao;

  @Valid
  @NotNull
  @ManyToOne
  private Produto produto;

  public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao,
      @NotNull @Valid Produto produto) {
    this.nome = nome;
    this.descricao = descricao;
    this.produto = produto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaracteristicaProduto that = (CaracteristicaProduto) o;
    return Objects.equals(nome, that.nome) && Objects.equals(produto,
        that.produto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, produto);
  }

  @Override
  public String toString() {
    return "CaracteristicaProduto{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", descricao='" + descricao + '\'' +
        '}';
  }
}
