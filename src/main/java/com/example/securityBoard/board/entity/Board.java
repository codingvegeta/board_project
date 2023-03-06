package com.example.securityBoard.board.entity;

import com.example.securityBoard.board.entity.enums.Elimination;
import com.example.securityBoard.member.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private Elimination elimination;

    private LocalDateTime creationDate;

    private LocalDateTime lastModification;

    private LocalDateTime deletedTime;

    public static Board createBoard(String content, Client client) {
        Board board = new Board();
        board.content = content;
        board.client = client;
        board.elimination = Elimination.Y;
        board.creationDate = LocalDateTime.now();
        return board;
    }

    public void editContent(String content) {
        this.content = content;
        this.lastModification = LocalDateTime.now();
    }

    public void deleteBoard() {
        this.elimination = Elimination.N;
        this.deletedTime = LocalDateTime.now();
    }

    public String boardEditTime() {
        return this.lastModification + "(편집됨)";
    }
}
