package br.com.oak.mercadolivreapi.storage;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake implements Uploader {

  public Set<String> enviar(List<MultipartFile> imagens) {
    return imagens.stream()
        .map(arquivo -> configurarUrl(gerarNomeUnico(arquivo.getOriginalFilename())))
        .collect(Collectors.toSet());
  }

  private String gerarNomeUnico(String originalFilename) {
    return UUID.randomUUID().toString() + "_" + originalFilename;
  }

  private String configurarUrl(String objeto) {
    return "https://mybucket.s3.amazonaws.com/".concat(objeto);
  }
}
