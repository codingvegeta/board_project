package com.example.callbuslab.member.service;

import com.example.callbuslab.global.exception.DuplicateClientException;
import com.example.callbuslab.global.security.jwt.JwtUtils;
import com.example.callbuslab.global.security.jwt.dto.TokenDto;
import com.example.callbuslab.member.dto.*;
import com.example.callbuslab.member.entity.Client;
import com.example.callbuslab.member.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Transactional
    public ClientSignupResponseDto signup(ClientSignupRequestDto signupRequest) {
        if (duplicatedClient(signupRequest.getAccountId())) {
            throw new DuplicateClientException("이미 존재하는 사용자 입니다.");
        }
        signupRequest.encodePassword(passwordEncoder);
        Client savedClient = clientRepository.save(signupRequest.toEntity());
        ClientSignupResponseDto clientResponse = new ClientSignupResponseDto(savedClient);
        return clientResponse;
    }


    public ClientLoginResponseDto login(ClientLoginRequestDto loginRequest) {
        Client client = clientRepository.findByAccountId(loginRequest.getAccountId()).orElseThrow(() -> new EntityNotFoundException());


        if (passwordMatch(loginRequest.getPassword(), client.getPassword())) {
            TokenDto tokenDto = issueToken(client);
            ClientLoginResponseDto clientLoginResponseDto = ClientLoginResponseDto.builder()
                    .clientNickName(client.clientNicknameAndRole())
                    .tokenDto(tokenDto)
                    .accountId(client.getAccountId())
                    .build();
            return clientLoginResponseDto;
        } else {
            throw new EntityNotFoundException("비밀번호가 다릅니다");
        }
    }

    private boolean passwordMatch(String clientLoginRequestPassword , String savedClientPassword) {
        return passwordEncoder.matches(clientLoginRequestPassword, savedClientPassword);
    }

    private boolean duplicatedClient(String clientAccountId) {
        Optional<Client> findClient = clientRepository.findByAccountId(clientAccountId);
        return findClient.isPresent();
    }

    private TokenDto issueToken(Client client) {
        Date date = new Date();
        String accessToken = jwtUtils.createToken(client);

        return TokenDto.builder()
                .accessToken(accessToken)
                .accessStartTime(String.valueOf(date))
                .accessExpirationTime(jwtUtils.getExpiration(accessToken))
                .build();
    }

    public GetClientResponseDto findClient(GetClientRequestDto clientRequest) {
        Client client = clientRepository.findByRoleAndId(clientRequest.roleConverter(clientRequest.getRole()), clientRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        GetClientResponseDto clientResponse = new GetClientResponseDto(client);
        return clientResponse;
    }
}
