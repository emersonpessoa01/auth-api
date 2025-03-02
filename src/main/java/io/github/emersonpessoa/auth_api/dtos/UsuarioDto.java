package io.github.emersonpessoa.auth_api.dtos;

import io.github.emersonpessoa.auth_api.enums.RoleEnum;

public record UsuarioDto(String nome,String login,String senha,RoleEnum role) {
    // Conceito: Objetos de transferência de dados, usados para evitar expor
    // diretamente as entidades do banco.

}
