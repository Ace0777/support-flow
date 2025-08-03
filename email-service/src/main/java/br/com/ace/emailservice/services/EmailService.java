package br.com.ace.emailservice.services;

import br.com.ace.emailservice.models.enums.OperationEnum;
import br.com.ace.emailservice.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.TicketCreatedMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendHtmlMail(
            final TicketCreatedMessage ticketDTO, OperationEnum operation
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        String process = getContext(ticketDTO, operation);

        EmailUtils.getMimeMessage(message, process, ticketDTO, "Ticket created successfully");

        mailSender.send(message);
    }

    private String getContext(TicketCreatedMessage ticketDTO, OperationEnum operation) {
        Context context = new Context();

        return switch (operation) {
            case TICKET_CREATED -> {
                log.info("Sending ticket creation email");
                context = EmailUtils.getContextToCreatedTicket(ticketDTO);
                yield templateEngine.process("email/ticket-created", context);
            }
            case TICKET_UPDATED -> {
                log.info("Sending ticket update email");
                yield templateEngine.process("email/ticket-updated", context);
            }
            case TICKET_DELETED -> {
                log.info("Sending ticket deletion email");
                yield templateEngine.process("email/ticket-deleted", context);
            }
        };

    }


}
