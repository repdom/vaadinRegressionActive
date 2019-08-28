package com.dany.dany.main.email;

import org.springframework.mail.SimpleMailMessage;

public interface CorreoService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String... templateArgs);
//    void sendMessageWithAttachment(String to,
//                                   String subject,
//                                   String text,
//                                   String pathToAttachment);
}
