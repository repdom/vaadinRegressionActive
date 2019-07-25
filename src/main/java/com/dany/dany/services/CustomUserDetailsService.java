package com.dany.dany.services;

import com.dany.dany.entidades.Usuario;
import com.dany.dany.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuarioOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado o email: " + usernameOrEmail));

        return UserPrincipal.create(usuario);
    }

    @Transactional
    public UserDetails loadUserById(String username) {
        Usuario usuario = usuarioRepository.findByUsuario(username);

        return UserPrincipal.create(usuario);
    }
}
