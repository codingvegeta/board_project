package com.example.callbuslab.member.enums;

public enum Role {
    LESSOR("임대인"), REALTOR("공인 중개사"), LESSEE("임차인")
    ,USER("외부사용자");
    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
