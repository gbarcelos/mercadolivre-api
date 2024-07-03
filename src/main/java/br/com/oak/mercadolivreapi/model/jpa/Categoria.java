package br.com.oak.mercadolivreapi.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

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

  public void setCategoriaPai(Categoria categoriaPai) {
    this.categoriaPai = categoriaPai;
  }
}
