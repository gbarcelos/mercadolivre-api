package br.com.oak.mercadolivreapi.repository;

import br.com.oak.mercadolivreapi.model.jpa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByLogin(String login);
}
