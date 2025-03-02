package io.github.emersonpessoa.auth_api.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import io.github.emersonpessoa.auth_api.dtos.AuthDto;
import io.github.emersonpessoa.auth_api.models.Usuario;
import io.github.emersonpessoa.auth_api.repositories.UsuarioRepository;
import io.github.emersonpessoa.auth_api.services.AutenticacaoService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);

    }

    @Override
    public String obterToken(AuthDto authDto) {
        Usuario usuario = usuarioRepository.findByLogin(authDto.login());
        return gerarTokenJwt(usuario);
    }

    public String gerarTokenJwt(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT" + exception.getMessage());
        }
    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
