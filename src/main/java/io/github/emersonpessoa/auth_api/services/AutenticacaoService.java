package io.github.emersonpessoa.auth_api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.github.emersonpessoa.auth_api.dtos.AuthDto;

public interface AutenticacaoService extends UserDetailsService {
    // Interface responsável por gerar o token
    public String obterToken(AuthDto authDto);
    public String validaTokenJwt(String token);

}
