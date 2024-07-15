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
import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Valid
  @NotNull
  @ManyToOne
  private Produto produto;

  @URL
  @NotBlank
  private String link;

  @Deprecated
  public ImagemProduto() {
  }

  public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
    this.produto = produto;
    this.link = link;
  }

  public Long getId() {
    return id;
  }

  public Produto getProduto() {
    return produto;
  }

  public String getLink() {
    return link;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImagemProduto that = (ImagemProduto) o;
    return Objects.equals(produto, that.produto) && Objects.equals(link,
        that.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(produto, link);
  }

  @Override
  public String toString() {
    return "ImagemProduto{" +
        "id=" + id +
        ", link='" + link + '\'' +
        '}';
  }
}
