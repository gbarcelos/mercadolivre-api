package br.com.oak.mercadolivreapi.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Setter
@Getter
public class LoginInputDto {

  private String email;
  private String password;

  public UsernamePasswordAuthenticationToken build() {
    return new UsernamePasswordAuthenticationToken(this.email,
        this.password);
  }
}
