package br.com.oak.mercadolivreapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Categoria {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @NotBlank
  private String nome;

  @Setter
  @ManyToOne
  private Categoria categoriaPai;

  @Deprecated
  public Categoria() {
  }

  public Categoria(String nome) {
    this.nome = nome;
  }

  public Categoria(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Categoria{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", categoriaPai=" + categoriaPai +
        '}';
  }
}
