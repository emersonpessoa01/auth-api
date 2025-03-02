package io.github.emersonpessoa.auth_api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.emersonpessoa.auth_api.models.Usuario;
import io.github.emersonpessoa.auth_api.repositories.UsuarioRepository;
import io.github.emersonpessoa.auth_api.services.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// Classe responsável por filtrar as requisições e verificar se o usuário está
// autenticado.
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extraiTokenHeader(request);
        // Verifica se o token é nulo
        if (token != null) {
            // Verifica se o token é inválido
            String login = autenticacaoService.validaTokenJwt(token);
            System.out.println("Login extraído do token: " + login);
            Usuario usuario = usuarioRepository.findByLogin(login);

            // Verifica se o usuário é nulo antes de prosseguir
            if (usuario != null) {
                // Cria um objeto de autenticação
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                // Define o usuário autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Usuário não encontrado, tratar como não autenticado
                // Log da situação para depuração
                System.out.println("Usuário não encontrado para o login: " + login);
            }

        }
        // Continua a requisição
        filterChain.doFilter(request, response);

    }

    // Método responsável por extrair o token da requisição
    public String extraiTokenHeader(HttpServletRequest request) {
        // Extrai o token da requisição
        var authHeader = request.getHeader("Authorization");
        // Verifica se o token é nulo ou se o token não começa com "Bearer "
        if (authHeader == null) {
            return null;
        }
        // Retorna o token sem o "Bearer "
        // Exemplodo formato: Bearer
        // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MzQ0MzIyMSwiaWF0IjoxNjgzNDQyNjIxLCJqdGkiOiIxIn0.19mOZ-2H3p69n24Zu4bQoHm711448ZJ4q0k3932y
        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return null;
        }

        return authHeader.split(" ")[1];
    }

}
