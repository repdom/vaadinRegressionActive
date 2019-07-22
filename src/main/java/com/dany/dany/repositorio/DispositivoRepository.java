package com.dany.dany.repositorio;

import com.dany.dany.entidades.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    List<Dispositivo> findAll();

    Dispositivo findByIdDispositivo(Long codigo);

    Dispositivo findByNombre(String nombre);
}
