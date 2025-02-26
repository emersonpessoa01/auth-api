package io.github.emersonpessoa.auth_api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emersonpessoa.auth_api.dtos.UsuarioDto;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
    // Conceito: Camada que recebe as requisições HTTP e direciona para os serviços.
    // Exemplo: @RestController

    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
        return usuarioDto;
    }
}
