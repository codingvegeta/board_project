package com.example.callbuslab.member.dto;

import com.example.callbuslab.member.entity.*;
import com.example.callbuslab.member.enums.Quit;
import com.example.callbuslab.member.enums.Role;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
        return getClient();
    }

    private Client getClient() {
        if (role == Role.LESSEE) {
            return lesseeBuilder();
        }
        if (role == Role.LESSOR) {
            return lessorBuilder();
        }
        if (role == Role.REALTOR) {
            return realtorBuilder();
        }
        if(role == Role.USER) {
            return userBuilder();
        }

        return null;
    }

    private user userBuilder() {
        return user.builder()
                .nickName(nickName)
                .accountId(accountId)
                .password(password)
                .quit(Quit.Y)
                .build();
    }

    private Realtor realtorBuilder() {
        return Realtor.builder()
                .nickName(nickName)
                .accountId(accountId)
                .password(password)
                .quit(Quit.Y)
                .build();
    }

    private Lessor lessorBuilder() {
        return Lessor.builder()
                .nickName(nickName)
                .accountId(accountId)
                .password(password)
                .quit(Quit.Y)
                .build();
    }

    private Lessee lesseeBuilder() {
        return Lessee.builder()
                .nickName(nickName)
                .accountId(accountId)
                .password(password)
                .quit(Quit.Y)
                .build();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
