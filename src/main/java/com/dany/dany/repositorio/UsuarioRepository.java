package com.dany.dany.repositorio;

import com.dany.dany.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findAll();
    Optional<Usuario> findByUsuarioOrEmail(String usuario, String email);
    Optional<Usuario> findByUsuarioAndContraisena(String usuario, String contrasenia);
    List<Usuario> findByUsuarioIn(List<String> usuarios);
    Usuario findByUsuario(String usuario);
    Boolean existsByUsuario(String username);
    Boolean existsByEmail(String email);

}


