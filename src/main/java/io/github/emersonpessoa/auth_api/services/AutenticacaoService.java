package io.github.emersonpessoa.auth_api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.github.emersonpessoa.auth_api.dtos.AuthDto;
import io.github.emersonpessoa.auth_api.models.Usuario;

public interface AutenticacaoService extends UserDetailsService {
    // Interface responsável por gerar o token
    String obterToken(AuthDto authDto);

}
