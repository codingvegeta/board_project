package com.example.securityBoard.member.enums;

public enum Quit {
    Y("회원입니다."), N("탈퇴한 회원입니다.");
    private String isQuit;

    Quit(String isQuit) {
        this.isQuit = isQuit;
    }

    public String getIsQuit() {
        return isQuit;
    }
}
