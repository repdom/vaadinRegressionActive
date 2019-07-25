package com.dany.dany.services;

import com.dany.dany.entidades.Usuario;
import com.dany.dany.main.Utils.SignUpRequest;
import com.dany.dany.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario guardar(SignUpRequest signUpRequest) {
        if (usuarioRepository.existsByUsuario(signUpRequest.getUsuario())) {
            return null;
        }

        if(usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setApellido(signUpRequest.getApellido());
        usuario.setNombre(signUpRequest.getNombre());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setUsuario(signUpRequest.getUsuario());
        usuario.setContraisena(signUpRequest.getContrasenia());

        Usuario result =  usuarioRepository.save(usuario);

        return result;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
