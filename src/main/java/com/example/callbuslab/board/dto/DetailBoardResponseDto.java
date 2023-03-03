package com.example.callbuslab.board.dto;

import com.example.callbuslab.board.entity.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailBoardResponseDto {
    private Long id;

    private String content;

    private String client;

    private String elimination;

    private LocalDateTime creationDate;

    private LocalDateTime lastModification;

    private LocalDateTime deletedTime;

    public DetailBoardResponseDto(Board board) {
        this.id = board.getId();
        this.content = board.getContent();
        this.client = board.getClient().clientNicknameAndRole();
        this.elimination = board.getElimination().getBoardStatus();
        this.creationDate = board.getCreationDate();
        this.lastModification = board.getLastModification();
        this.deletedTime = board.getDeletedTime();
    }
}
