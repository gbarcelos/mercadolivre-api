package br.com.oak.mercadolivreapi.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class AdicionaImagensRequest {

  @Size(min = 1, max = 10)
  private List<MultipartFile> imagens = new ArrayList<>();

  public void setImagens(List<MultipartFile> imagens) {
    this.imagens = imagens;
  }

  public List<MultipartFile> getImagens() {
    return imagens;
  }
}
