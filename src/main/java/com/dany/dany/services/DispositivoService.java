package com.dany.dany.services;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DispositivoService {
    @Autowired
    DispositivoRepository dispositivoRepository;

    public List<Dispositivo> getAllDispositivos() {
        return dispositivoRepository.findAll();
    }

    public Collection<String> getAllDispositivosCodigoYNombre() {
        return getAllDispositivos().stream().map(e -> {return e.getIdDispositivo() + " - " + e.getNombre();}).collect(Collectors.toList());
    }

    public Dispositivo find(Long id) {return  dispositivoRepository.findByIdDispositivo(id); }

}
