package com.example.securityBoard.board.entity.enums;

public enum Elimination {
    Y("게시중 입니다."), N("삭제되었습니다.");

    private String boardStatus;

    Elimination(String boardStatus) {
        this.boardStatus = boardStatus;
    }

    public String getBoardStatus() {
        return boardStatus;
    }
}
