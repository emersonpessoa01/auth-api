package io.github.emersonpessoa.auth_api.dtos;

public record UsuarioDto(String nome, String login, String senha) {
    // Conceito: Objetos de transferência de dados, usados para evitar expor
    // diretamente as entidades do banco.

}
