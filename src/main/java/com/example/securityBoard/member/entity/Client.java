package com.example.securityBoard.member.entity;

import com.example.securityBoard.member.enums.Quit;
import com.example.securityBoard.member.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Client {
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

    @Builder
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
