package br.com.ace.supportflowbff.service;

import br.com.ace.supportflowbff.client.AuthFeingClient;
import lombok.RequiredArgsConstructor;
import models.requests.AuthenticateRequest;
import models.requests.RefreshTokenRequest;
import models.responses.AuthenticationResponse;
import models.responses.RefreshTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthFeingClient feignClient;


    public AuthenticationResponse authenticate(AuthenticateRequest request) throws Exception  {
        return feignClient.authenticate(request).getBody();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        return feignClient.refreshToken(request).getBody();
    }
}
