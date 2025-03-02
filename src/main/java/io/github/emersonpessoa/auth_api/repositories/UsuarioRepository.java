package io.github.emersonpessoa.auth_api.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.emersonpessoa.auth_api.models.Usuario;

@Repository //Annotation que indica que a classe é um repositório(opcional)
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // repositories → Interface que interage com o banco, estendendo JpaRepository ou CrudRepository
    Usuario findByLogin(String login);



    
}
