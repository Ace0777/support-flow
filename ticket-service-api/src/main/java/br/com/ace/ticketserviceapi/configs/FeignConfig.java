package br.com.ace.ticketserviceapi.configs;

import br.com.ace.ticketserviceapi.decoders.RetrieveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new RetrieveMessageErrorDecoder(); //<- Override do errorDecoder e usa o meu.
    }

}
