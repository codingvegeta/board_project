package com.example.securityBoard.favor.dto;

import com.example.securityBoard.favor.entity.Favor;
import lombok.Data;

@Data
public class FavorClientResponseDto {
    private String clientId;
    private Long boardId;

    public FavorClientResponseDto(Favor favor) {
        this.clientId = favor.getClient().clientNicknameAndRole();
        this.boardId = favor.getBoard().getId();
    }
}
