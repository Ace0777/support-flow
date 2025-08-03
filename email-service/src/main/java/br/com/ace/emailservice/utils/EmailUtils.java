package br.com.ace.emailservice.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.experimental.UtilityClass;
import models.dtos.TicketCreatedMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class EmailUtils {
    public static void getMimeMessage(
            MimeMessage message, String process, TicketCreatedMessage ticketDTO, String subject
    ) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(ticketDTO.getCustomer().email());
        helper.setFrom(new InternetAddress("luishhasantos@gmail.com"));
        helper.setSubject(subject);
        helper.setText(process, true);
    }

    public static Context getContextToCreatedTicket(TicketCreatedMessage ticketDTO) {
        Context context = new Context();

        context.setVariable("username", ticketDTO.getCustomer().name());
        context.setVariable("ticketId", ticketDTO.getTicket().id());
        context.setVariable("title", ticketDTO.getTicket().title());
        context.setVariable("description", ticketDTO.getTicket().description());
        context.setVariable("createdAt", ticketDTO.getTicket().createdAt());
        context.setVariable("technician", ticketDTO.getRequester().name());
        context.setVariable("status", ticketDTO.getTicket().status());


        return context;
    }
}
