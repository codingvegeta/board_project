package com.example.securityBoard.member.dto;

import com.example.securityBoard.global.security.jwt.dto.TokenDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientLoginResponseDto {

    private TokenDto tokenDto;

    private String clientNickName;

    private String accountId;

}
