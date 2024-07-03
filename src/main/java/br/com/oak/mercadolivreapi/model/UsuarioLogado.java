package br.com.oak.mercadolivreapi.model;

import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioLogado implements UserDetails {

  private final Usuario usuario;

  private final User springUserDetails;

  public UsuarioLogado(@NotNull @Valid Usuario usuario) {
    this.usuario = usuario;
    springUserDetails = new User(usuario.getLogin(), usuario.getSenha(), List.of());
  }

  public Collection<GrantedAuthority> getAuthorities() {
    return springUserDetails.getAuthorities();
  }

  public String getPassword() {
    return springUserDetails.getPassword();
  }

  public String getUsername() {
    return springUserDetails.getUsername();
  }

  public boolean isEnabled() {
    return springUserDetails.isEnabled();
  }

  public boolean isAccountNonExpired() {
    return springUserDetails.isAccountNonExpired();
  }

  public boolean isAccountNonLocked() {
    return springUserDetails.isAccountNonLocked();
  }

  public boolean isCredentialsNonExpired() {
    return springUserDetails.isCredentialsNonExpired();
  }

  public Usuario get() {
    return usuario;
  }
}
