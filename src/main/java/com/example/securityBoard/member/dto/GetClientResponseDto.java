package com.example.securityBoard.member.dto;

import com.example.securityBoard.member.entity.Client;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetClientResponseDto {
    private Long clientId;

    private String clientNickname;

    private String clientRole;

    private String accountId;

    private String quit;

    private LocalDateTime creationDate;

    public GetClientResponseDto(Client client) {
        this.clientId = client.getId();
        this.clientNickname = client.getNickName();
        this.clientRole = client.getRole().getRoleName();
        this.accountId = client.getAccountId();
        this.quit = client.getQuit().getIsQuit();
        this.creationDate = client.getCreationDate();
    }
}
