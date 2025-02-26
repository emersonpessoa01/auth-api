package io.github.emersonpessoa.auth_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.emersonpessoa.auth_api.dtos.UsuarioDto;
import io.github.emersonpessoa.auth_api.models.Usuario;
import io.github.emersonpessoa.auth_api.repositories.UsuarioRepository;
import io.github.emersonpessoa.auth_api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        // Para evitar que o usuário seja criado duas vezes, verificamos se o usuário já existe
        Usuario usuarioJaexiste = usuarioRepository.findByLogin(usuarioDto.login());
        if (usuarioJaexiste != null) {
            throw new RuntimeException("Usuário já existe");
        }
        // Criando um novo usuário
        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), usuarioDto.senha());
        Usuario novoUsuario = usuarioRepository.save(entity);
        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha());
    }

}
