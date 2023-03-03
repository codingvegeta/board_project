package com.example.callbuslab.favor.controller;

import com.example.callbuslab.favor.dto.FavorClientResponseDto;
import com.example.callbuslab.favor.service.FavorService;
import com.example.callbuslab.global.globalResponse.BaseResponse;
import com.example.callbuslab.global.security.client.ClientAdapter;
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

    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE','USER')")
    @GetMapping("/api/v1/favor/{id}")
    public String addFavor(@AuthenticationPrincipal ClientAdapter clientAdapter, @PathVariable Long id) {
        return favorService.addFavor(clientAdapter, id);
    }

    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE')")
    @GetMapping("/api/v1/favor/client/{accountId}")
    public BaseResponse<List<FavorClientResponseDto>> findFavor(@PathVariable String accountId) {
        List<FavorClientResponseDto> clientFavor = favorService.findClientFavor(accountId);
        return BaseResponse.of(clientFavor);
    }

    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE')")
    @GetMapping("/api/v1/favor")
    public BaseResponse<List<FavorClientResponseDto>> findAllFavor() {
        List<FavorClientResponseDto> allFavor = favorService.findAllFavor();
        return BaseResponse.of(allFavor);
    }
}
