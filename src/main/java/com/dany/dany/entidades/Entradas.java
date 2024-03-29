package com.dany.dany.entidades;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Entradas implements Serializable {
    // ID, nombre y tiempo de alarma
    @Id
    @GeneratedValue
    private Long idDispositivo;
    private Date fechageneracion;
    private Long temperatura;
    private Long humedad;

    @ManyToOne (fetch = FetchType.EAGER)
    private Dispositivo dispositivo;

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Date getFechageneracion() {
        return fechageneracion;
    }

    public void setFechageneracion(Date fechageneracion) {
        this.fechageneracion = fechageneracion;
    }

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getHumedad() {
        return humedad;
    }

    public void setHumedad(long humedad) {
        this.humedad = humedad;
    }
    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
}
