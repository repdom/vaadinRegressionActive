package com.dany.dany.repositorio;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.entidades.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entradas, Long> {

        List<Entradas> findAll();

        // Dispositivo> findByCodigo(Long codigo);
        List<Entradas> findAllByDispositivo(Dispositivo id);

        List<Entradas> findAllByIdDispositivo(Long id);

        @Query(value = "select Top 1 * from Entradas where DISPOSITIVO_ID_DISPOSITIVO =?1 order by fechageneracion desc", nativeQuery = true)
        List<Entradas> findLastByDeviceId(Long deviceId);
                // "select TOP 1 * from Entradas e JOIN e.dispositivo d where d.idDispositivo = ?1 order by e.fechageneracion desc"
                //"SELECT TOP 1 * FROM Entradas s WHERE s.idDispositivo = ?1 ORDER BY fechageneracion DESC"




}