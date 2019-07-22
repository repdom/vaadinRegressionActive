package com.dany.dany.repositorio;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.entidades.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entradas, Long> {

        List<Entradas> findAll();

        // Dispositivo> findByCodigo(Long codigo);
        List<Entradas> findAllByDispositivo(Dispositivo id);
}