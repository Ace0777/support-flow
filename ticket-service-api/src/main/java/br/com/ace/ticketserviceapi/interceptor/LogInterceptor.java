package br.com.ace.ticketserviceapi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import models.dtos.LogEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final RabbitTemplate rabbitTemplate;
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>(); //medir tempo de execucao


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTime.set(System.currentTimeMillis());
        return true; //continua a execucao da requisicao
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long duration = System.currentTimeMillis() - startTime.get();
        startTime.remove();


        LogEvent event = new LogEvent();
        event.setHttpMethod(request.getMethod());
        event.setHttpRoute(request.getRequestURI());
        event.setHttpStatusCode(response.getStatus());
        event.setHttpStatus(HttpStatus.valueOf(response.getStatus()).name());
        event.setExecutionTimeMs(duration);
        event.setClientIp(request.getRemoteAddr());
        event.setUserAgent(request.getHeader("User-Agent"));

        rabbitTemplate.convertAndSend(
                "supportflowlogs", // sua exchange
                "rk.logs",
                event
        );
    }



}
