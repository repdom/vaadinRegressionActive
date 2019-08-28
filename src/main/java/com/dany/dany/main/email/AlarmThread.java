package com.dany.dany.main.email;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import com.dany.dany.services.EntradasServices;
import com.dany.dany.services.DispositivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class AlarmThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmThread.class);

    @Autowired
    DispositivoRepository dispositivoRepository;

    @Autowired
    EntradasServices entradasServices;

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void run() {
        try {
            Map<String, Long> lastMessagesIds = new HashMap<>();
            while (true) {
                Thread.sleep(5000);
                List<Dispositivo> devices = dispositivoRepository.findAll();
                for (Dispositivo device : devices) {
                    try {
                        long seconds = device.getTiempoDeAlarma();
                        Long lastMessageId = lastMessagesIds.get(device.getNombre());
                        Long newLastMessageId = entradasServices.getLastMessageById(device.getIdDispositivo()).getIdDispositivo();
                        if (lastMessageId != null && newLastMessageId != null) {
                            Date newLastMessageDate = entradasServices.getLastMessageById(device.getIdDispositivo()).getFechageneracion();
                            if (new Timestamp(System.currentTimeMillis() - seconds * 1000).after(newLastMessageDate) && lastMessageId.equals(newLastMessageId)) {
                                String emailMsg = "El dispositivo" + device.getNombre() + " ha dejado de funcionar" + "\n\n";
                                emailMsg += "La ultima llamada recibida fue: " + newLastMessageDate + "\n";
                                emailMsg += "Se encendio: " + new Timestamp(System.currentTimeMillis() - seconds * 1000);
                                SimpleMailMessage message = new SimpleMailMessage();
                                message.setTo("20140047@ce.pucmm.edu.do");
                                message.setSubject("Alarma activada para el sensor:  " + device.getNombre());
                                message.setText(emailMsg);
                                emailSender.send(message);
                            }
                            lastMessagesIds.put(device.getNombre(), newLastMessageId);
                        } else {
                            //LOGGER.warn(" NULL");
                            lastMessagesIds.put(device.getNombre(), device.getIdDispositivo());
                        }

                    } catch (NullPointerException e) {
                        continue;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
