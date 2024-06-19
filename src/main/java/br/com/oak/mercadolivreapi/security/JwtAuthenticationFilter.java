package br.com.oak.mercadolivreapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenManager tokenManager;

  private final UsersService usersService;

  public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
    this.tokenManager = tokenManager;
    this.usersService = usersService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    Optional<String> possibleToken = getTokenFromRequest(request);

    if (possibleToken.isPresent() && tokenManager.isValid(possibleToken.get())) {

      String userName = tokenManager.getUserName(possibleToken.get());
      UserDetails userDetails = usersService.loadUserByUsername(userName);

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }

  private Optional<String> getTokenFromRequest(HttpServletRequest request) {
    String authToken = request.getHeader("Authorization");
    return Optional.ofNullable(authToken);
  }
}
