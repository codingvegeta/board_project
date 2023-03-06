package com.example.securityBoard.member.dto;

import com.example.securityBoard.member.entity.*;
import com.example.securityBoard.member.enums.Quit;
import com.example.securityBoard.member.enums.Role;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class ClientSignupRequestDto {

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String accountId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Quit quit;

    public Client toEntity() {
        return Client.builder()
                .nickName(nickName)
                .role(role)
                .accountId(accountId)
                .password(password)
                .quit(Quit.Y)
                .creationDate(LocalDateTime.now())
                .build();
    }


    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
