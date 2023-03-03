package com.example.callbuslab.member.dto;

import com.example.callbuslab.member.entity.Client;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientSignupResponseDto {

    private Long clientId;

    private String clientNickname;

    private String clientRole;

    private String accountId;

    private String quit;

    private LocalDateTime creationDate;

    public ClientSignupResponseDto(Client client) {
        this.clientId = client.getId();
        this.clientNickname = client.getNickName();
        this.clientRole = client.getRole().getRoleName();
        this.accountId = client.getAccountId();
        this.quit = client.getQuit().getIsQuit();
        this.creationDate = client.getCreationDate();
    }
}
