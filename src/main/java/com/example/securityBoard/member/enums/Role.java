package com.example.securityBoard.member.enums;

public enum Role {
    USER("가입한 사용자"), SUPER_USER("우수 사용자"), MANAGER("매니저")
    ,ADMIN("관리자");
    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
