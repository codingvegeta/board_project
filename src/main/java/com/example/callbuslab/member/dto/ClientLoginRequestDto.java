package com.example.callbuslab.member.dto;

import lombok.Data;

@Data
public class ClientLoginRequestDto {
    private String accountId;
    private String password;
}
