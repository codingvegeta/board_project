package com.example.callbuslab.member.dto;

import com.example.callbuslab.member.enums.Role;
import lombok.Data;

@Data
public class GetClientRequestDto {
    private String role;
    private Long id;

    public Role roleConverter(String role) {
        if (role.equals("Realtor")) {
            return Role.REALTOR;
        }
        if (role.equals("Lessor")) {
            return Role.LESSOR;
        }
        if (role.equals("Lessee")) {
            return Role.LESSEE;
        }
        return null;
    }
}
