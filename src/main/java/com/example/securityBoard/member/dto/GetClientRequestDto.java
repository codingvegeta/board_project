package com.example.securityBoard.member.dto;

import com.example.securityBoard.member.enums.Role;
import lombok.Data;

@Data
public class GetClientRequestDto {
    private Role role;
    private Long id;


}
