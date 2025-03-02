package io.github.emersonpessoa.auth_api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emersonpessoa.auth_api.dtos.UsuarioDto;
import io.github.emersonpessoa.auth_api.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
    // Conceito: Camada que recebe as requisições HTTP e direciona para os serviços.
    // Exemplo: @
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.salvar(usuarioDto);
    }
    @GetMapping("/admin")
    public String getAdmin() {
        return "Permissão de administrador!";
    }
    @GetMapping("/user")
    public String getUser() {
        return "Permissão de usuário!";
    }
    
}
