package com.dany.dany.main.Utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 15)
    private String usuario;

    @NotBlank
    @Size(min = 6, max = 20)
    private String apellido;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String contrasenia;

    public String getNombre() {
        return nombre;
    }

    public SignUpRequest setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public SignUpRequest setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignUpRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public SignUpRequest setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public SignUpRequest setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
}
