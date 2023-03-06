package com.example.securityBoard.favor.entity;

import com.example.securityBoard.board.entity.Board;
import com.example.securityBoard.member.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    LocalDateTime creationDate;

    public static Favor addFavor(Client client, Board board) {
        Favor favor = new Favor();
        favor.client = client;
        favor.board = board;
        favor.creationDate = LocalDateTime.now();
        return favor;
    }
}
