package io.github.emersonpessoa.auth_api.models;

import jakarta.persistence.GeneratedValue;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.emersonpessoa.auth_api.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private RoleEnum role;

    public Usuario(String nome, String login, String senha, RoleEnum role) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    @Override
    //Conceito: Uma coleção de objetos que representam as permissões do usuário.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Tanto o ADMIN como o USER terão acesso ao recursos, porém o ADMIN terá acesso a mais recursos
        if (this.role == RoleEnum.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"), 
                new SimpleGrantedAuthority("ROLE_USER")
                );
            
        }
        return List.of(
            new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
