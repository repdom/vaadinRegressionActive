package com.dany.dany.entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Dispositivo implements Serializable {
    @Id
    @GeneratedValue
    private Long idDispositivo;
    private String nombre;
    private Long tiempoDeAlarma;

    @OneToMany(mappedBy = "dispositivo", fetch = FetchType.EAGER)
    private Set<Entradas> entradasDispositivos;

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTiempoDeAlarma() {
        return tiempoDeAlarma;
    }

    public void setTiempoDeAlarma(Long tiempoDeAlarma) {
        this.tiempoDeAlarma = tiempoDeAlarma;
    }

    public Set<Entradas> getEntradasDispositivos() {
        return entradasDispositivos;
    }

    public void setEntradasDispositivos(Set<Entradas> entradasDispositivos) {
        this.entradasDispositivos = entradasDispositivos;
    }
}
