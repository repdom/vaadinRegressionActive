package com.dany.dany.main;

import com.dany.dany.services.EntradasServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Recibidor {

    @Autowired
    EntradasServices entradasServices;

    @RabbitListener(queues = "dispositivo")
    public void receive(Message in) throws Exception {
       final ObjectMapper mapper = new ObjectMapper();
       final JsonNode jsonNode = mapper.readTree(new String(in.getBody()));

       System.out.println(" [x] Received '" + jsonNode.toString() + "'");

        entradasServices.guardarDispositivo(jsonNode);
    }
}
