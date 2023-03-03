package com.example.callbuslab.global.security.jwt.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {
    private String accessToken;
    private String accessStartTime;
    private String accessExpirationTime;

    @Builder
    public TokenDto(String accessToken, String accessStartTime, String accessExpirationTime) {
        this.accessToken = accessToken;
        this.accessStartTime = accessStartTime;
        this.accessExpirationTime = accessExpirationTime;
    }


}
