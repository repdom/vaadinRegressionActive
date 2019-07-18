package com.dany.dany;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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
			dispositivo.setHumedad(80L);
			dispositivo.setTemperatura(90L);
			dispositivo.setFechageneracion(hoy);

			Dispositivo dispositivo2 = new Dispositivo();
			dispositivo2.setHumedad(90L);
			dispositivo2.setTemperatura(10L);
			dispositivo2.setFechageneracion(hoy);

			Dispositivo dispositivo3 = new Dispositivo();
			dispositivo3.setHumedad(20L);
			dispositivo3.setTemperatura(30L);
			dispositivo3.setFechageneracion(hoy);

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
