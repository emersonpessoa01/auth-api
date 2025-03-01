package io.github.emersonpessoa.auth_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.emersonpessoa.auth_api.dtos.AuthDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


//Criando um endpoint responsável para autenticação
@RestController
@RequestMapping("/auth")
public class autenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDto authDto) {
        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        authenticationManager.authenticate(usuarioAutenticationToken);
        
        return "token...";
    }
    
    
}
