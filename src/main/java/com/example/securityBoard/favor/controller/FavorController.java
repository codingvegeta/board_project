package com.example.securityBoard.favor.controller;

import com.example.securityBoard.favor.dto.FavorClientResponseDto;
import com.example.securityBoard.favor.service.FavorService;
import com.example.securityBoard.global.globalResponse.BaseResponse;
import com.example.securityBoard.global.security.client.ClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavorController {
    private final FavorService favorService;

    @PreAuthorize("hasAnyRole('USER','SUPER_USER','MANAGER','ADMIN')")
    @GetMapping("/api/v1/favor/{id}")
    public String addFavor(@AuthenticationPrincipal ClientAdapter clientAdapter, @PathVariable Long id) {
        return favorService.addFavor(clientAdapter, id);
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/api/v1/favor/client/{accountId}")
    public BaseResponse<List<FavorClientResponseDto>> findFavor(@PathVariable String accountId) {
        List<FavorClientResponseDto> clientFavor = favorService.findClientFavor(accountId);
        return BaseResponse.of(clientFavor);
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/api/v1/favor")
    public BaseResponse<List<FavorClientResponseDto>> findAllFavor() {
        List<FavorClientResponseDto> allFavor = favorService.findAllFavor();
        return BaseResponse.of(allFavor);
    }
}
