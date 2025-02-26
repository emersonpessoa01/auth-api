package io.github.emersonpessoa.auth_api.services;

import io.github.emersonpessoa.auth_api.dtos.UsuarioDto;

public interface UsuarioService {
    //Conceito: services → Contém a lógica de negócio, chamada pelos controllers. Exemplo: @Service. 
    public UsuarioDto salvar(UsuarioDto usuarioDto);
}
