package br.com.oak.mercadolivreapi.config;

import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import br.com.oak.mercadolivreapi.model.UsuarioLogado;
import br.com.oak.mercadolivreapi.security.UserDetailsMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class UserDetailsMapperImpl implements UserDetailsMapper {

  @Override
  public UserDetails map(Object shouldBeASystemUser) {
    return new UsuarioLogado((Usuario)shouldBeASystemUser);
  }
}
