package br.com.ace.emailservice.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.TicketCreatedMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;



@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${text-created-ticket-confirmation}")
    private String textCreatedTicketConfirmation;


    public void sendEmail(final TicketCreatedMessage ticket){

        log.info("Sending email for client: {}", ticket.getCustomer().email());

        SimpleMailMessage message = getSimpleMailMessage(ticket);

        try {
            mailSender.send(message);
            log.info("Email sent successfully to: {}", ticket.getCustomer().email());
        }catch (MailException e){
            switch (e.getClass().getSimpleName()){
                case "MailAuthenticationException" -> log.error("Error authenticating with email server: {}", e.getMessage());
                case "MailSendException" -> log.error("Error sending email: {}", e.getMessage());
                case "MailParseException" -> log.error("Error parsing email: {}", e.getMessage());
                default -> log.error("An unexpected error occurred while sending email: {}", e.getMessage());
            }
        }
    }

    private SimpleMailMessage getSimpleMailMessage(TicketCreatedMessage ticket) {
        String subject = "Ticket Created with success";
        String text = String.format(textCreatedTicketConfirmation,
                ticket.getCustomer().name(),
                ticket.getTicket().id(),
                ticket.getTicket().title(),
                ticket.getTicket().description(),
                ticket.getTicket().createdAt(),
                ticket.getRequester().name(),
                ticket.getTicket().status());


        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(ticket.getCustomer().email());
        message.setText(text);
        return message;
    }

}
