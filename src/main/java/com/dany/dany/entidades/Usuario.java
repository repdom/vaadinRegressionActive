package com.dany.dany.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements Serializable {
    @Id
    private String usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contraisena;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraisena() {
        return contraisena;
    }

    public void setContraisena(String contraisena) {
        this.contraisena = contraisena;
    }

}
