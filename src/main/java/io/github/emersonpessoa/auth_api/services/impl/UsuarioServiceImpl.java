package io.github.emersonpessoa.auth_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.emersonpessoa.auth_api.dtos.UsuarioDto;
import io.github.emersonpessoa.auth_api.models.Usuario;
import io.github.emersonpessoa.auth_api.repositories.UsuarioRepository;
import io.github.emersonpessoa.auth_api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        // Para evitar que o usuário seja criado duas vezes, verificamos se o usuário já existe
        Usuario usuarioJaexiste = usuarioRepository.findByLogin(usuarioDto.login());
        if (usuarioJaexiste != null) {
            throw new RuntimeException("Usuário já existe");
        }

        // Encriptando a senha - convertendo em hash
        var passwordHash = passwordEncoder.encode(usuarioDto.senha());
        // Criando um novo usuário
        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordHash, usuarioDto.role());
        Usuario novoUsuario = usuarioRepository.save(entity);
        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha(),novoUsuario.getRole());
    }

}
