package com.dany.dany.repositorio;

import com.dany.dany.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findAll();
    Optional<Usuario> findByUsuarioOrEmail(String usuario, String email);
    List<Usuario> findByUsuarioIn(List<String> usuarios);
    Usuario findByUsuario(String usuario);
    Boolean existsByUsuario(String username);
    Boolean existsByEmail(String email);
}
