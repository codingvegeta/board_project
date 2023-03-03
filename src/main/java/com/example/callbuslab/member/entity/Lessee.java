package com.example.callbuslab.member.entity;

import com.example.callbuslab.member.enums.Quit;
import com.example.callbuslab.member.enums.Role;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("LESSEE")
@PrimaryKeyJoinColumn(name = "CLIENT_ID")
@NoArgsConstructor
public class Lessee extends Client{
    @Builder
    public Lessee(String nickName, String accountId, String password, Quit quit) {
        super(nickName, Role.LESSEE, accountId, password, quit, LocalDateTime.now());
    }
}