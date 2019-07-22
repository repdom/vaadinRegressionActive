package com.dany.dany.services;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.entidades.Entradas;
import com.dany.dany.repositorio.DispositivoRepository;
import com.dany.dany.repositorio.EntradaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DispositivoEntradasServices {
    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    DispositivoRepository dispositivoRepository;

    public void guardarDispositivo(JsonNode entrada) throws Exception {

        JsonNode idDispositivoNode = entrada.get("IdDispositivo");
        Long idDispositivo = idDispositivoNode.asLong();

        Dispositivo dispositivo = dispositivoRepository.findByIdDispositivo(idDispositivo);


        JsonNode temperaturaNode = entrada.get("temperatura");
        Long temperatura = temperaturaNode.asLong();

        JsonNode humedadNode = entrada.get("humedad");
        Long humedad = humedadNode.asLong();

        JsonNode fechaEnvioNode = entrada.get("fechaGeneracion");
        Date fecha =  new Date();

        Entradas dispositivoEntrada = new Entradas();
        dispositivoEntrada.setFechageneracion(fecha);
        dispositivoEntrada.setDispositivo(dispositivo);
        dispositivoEntrada.setHumedad(humedad);
        dispositivoEntrada.setTemperatura(temperatura);

        entradaRepository.save(dispositivoEntrada);
    }

    public List<Entradas> getAllEntradas(Dispositivo id) {
        return entradaRepository.findAllByDispositivo(id);
    }
}
