package br.com.oak.mercadolivreapi.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

  private String email;
  private String password;

  public UsernamePasswordAuthenticationToken build() {
    return new UsernamePasswordAuthenticationToken(this.email,
        this.password);
  }
}
