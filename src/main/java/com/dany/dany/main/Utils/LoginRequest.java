package com.dany.dany.main.Utils;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String usuarioOEmail;

    @NotBlank
    private String contraysena;

    public String getUsuarioOEmail() {
        return usuarioOEmail;
    }

    public LoginRequest setUsuarioOEmail(String usuarioOEmail) {
        this.usuarioOEmail = usuarioOEmail;
        return this;
    }

    public String getContraysena() {
        return contraysena;
    }

    public LoginRequest setContraysena(String contraysena) {
        this.contraysena = contraysena;
        return this;
    }
}
