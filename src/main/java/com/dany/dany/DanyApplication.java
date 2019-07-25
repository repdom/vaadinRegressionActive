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
			dispositivo3.setIdDispositivo(3L);
			dispositivo3.setTiempoDeAlarma(1L);

			Dispositivo dispositivo4 = new Dispositivo();
			dispositivo4.setNombre("ATX BUILD 4");
			dispositivo4.setIdDispositivo(4L);
			dispositivo4.setTiempoDeAlarma(1L);

			Dispositivo dispositivo5 = new Dispositivo();
			dispositivo5.setNombre("ATX BUILD 5");
			dispositivo5.setIdDispositivo(5L);
			dispositivo5.setTiempoDeAlarma(1L);

			Dispositivo dispositivo6 = new Dispositivo();
			dispositivo6.setNombre("ATX BUILD 6");
			dispositivo6.setIdDispositivo(6L);
			dispositivo6.setTiempoDeAlarma(1L);

			Dispositivo dispositivo7 = new Dispositivo();
			dispositivo7.setNombre("ATX BUILD 7");
			dispositivo7.setIdDispositivo(7L);
			dispositivo7.setTiempoDeAlarma(1L);

			Dispositivo dispositivo8 = new Dispositivo();
			dispositivo8.setNombre("ATX BUILD 8");
			dispositivo8.setIdDispositivo(8L);
			dispositivo8.setTiempoDeAlarma(1L);


			repository.save(dispositivo);
			repository.save(dispositivo2);
			repository.save(dispositivo4);
			repository.save(dispositivo5);
			repository.save(dispositivo3);
			repository.save(dispositivo6);
			repository.save(dispositivo7);
			repository.save(dispositivo8);

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Dispositivo dist : repository.findAll()) {
				log.info(dist.toString());
			}
			log.info("");
		});
	}
}
