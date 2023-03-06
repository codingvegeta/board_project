package com.example.securityBoard.member.controller;

import com.example.securityBoard.member.dto.*;
import com.example.securityBoard.member.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/api/v1/signup")
    public ClientSignupResponseDto signup(@RequestBody ClientSignupRequestDto signupRequest) {
        log.info("signup ={}", signupRequest);
        ClientSignupResponseDto signupClient = clientService.signup(signupRequest);
        return signupClient;
    }

    @PostMapping("/api/v1/login")
    public ClientLoginResponseDto login(@RequestBody ClientLoginRequestDto loginRequest) {
        ClientLoginResponseDto loginClient = clientService.login(loginRequest);
        return loginClient;
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PostMapping("/api/v1/getClient")
    public GetClientResponseDto client(@RequestBody GetClientRequestDto clientRequest) {
        GetClientResponseDto findClient = clientService.findClient(clientRequest);
        return findClient;
    }
}
