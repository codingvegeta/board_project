package com.example.callbuslab.member.dto;

import com.example.callbuslab.global.security.jwt.dto.TokenDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientLoginResponseDto {

    private TokenDto tokenDto;

    private String clientNickName;

    private String accountId;

}
