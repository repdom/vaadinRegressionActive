package com.dany.dany;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DanyApplication {
	private static final Logger log = LoggerFactory.getLogger(DanyApplication.class);
	// private static DispositivoRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(DanyApplication.class, args);
	}

	@Bean
	public CommandLineRunner cargarDatos(DispositivoRepository repository) {
		// this.repo = repository;
		// DispositivoRepository repository;
		return (args -> {
			Date hoy = new Date();
			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setNombre("ATX BUILD");
			dispositivo.setIdDispositivo(1L);
			dispositivo.setTiempoDeAlarma(1L);

			Dispositivo dispositivo2 = new Dispositivo();
			dispositivo2.setNombre("ATX BUILD 2");
			dispositivo2.setIdDispositivo(2L);
			dispositivo2.setTiempoDeAlarma(1L);

			Dispositivo dispositivo3 = new Dispositivo();
			dispositivo3.setNombre("ATX BUILD 3");
			dispositivo3.setIdDispositivo(2L);
			dispositivo3.setTiempoDeAlarma(1L);

			repository.save(dispositivo);
			repository.save(dispositivo2);
			repository.save(dispositivo3);

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Dispositivo dist : repository.findAll()) {
				log.info(dist.toString());
			}
			log.info("");
		});
	}
}
