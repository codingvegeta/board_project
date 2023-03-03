package com.example.callbuslab.member.entity;

import com.example.callbuslab.member.enums.Quit;
import com.example.callbuslab.member.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CLIENT_TYPE")
public abstract class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String accountId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Quit quit;

    private LocalDateTime creationDate ;

//    @Builder
    public Client(String nickName, Role role, String accountId, String password, Quit quit, LocalDateTime creationDate) {
        this.nickName = nickName;
        this.role = role;
        this.accountId = accountId;
        this.password = password;
        this.quit = quit;
        this.creationDate = creationDate;
    }

    public String clientNicknameAndRole() {
        return nickName + "(" + role.getRoleName() + ")";
    }
}
