package io.github.emersonpessoa.auth_api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import io.github.emersonpessoa.auth_api.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    // repositories → Interface que interage com o banco, estendendo JpaRepository ou CrudRepository
    
}
