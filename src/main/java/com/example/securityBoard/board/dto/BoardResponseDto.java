package com.example.securityBoard.board.dto;

import com.example.securityBoard.board.entity.Board;
import lombok.Data;

@Data
public class BoardResponseDto {
    private Long id;

    private String content;

    private String clientName;

    private String creationDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.content = board.getContent();
        this.clientName = board.getClient().clientNicknameAndRole();
        this.creationDate = board.getCreationDate().toString();
        if (board.getLastModification() != null) {
            this.creationDate = board.boardEditTime();
        }
    }
}
