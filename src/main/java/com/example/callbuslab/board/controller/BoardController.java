package com.example.callbuslab.board.controller;

import com.example.callbuslab.board.dto.BoardEditRequestDto;
import com.example.callbuslab.board.dto.BoardResponseDto;
import com.example.callbuslab.board.dto.DetailBoardResponseDto;
import com.example.callbuslab.board.dto.GenerateBoardRequestDto;
import com.example.callbuslab.board.service.BoardService;
import com.example.callbuslab.global.globalResponse.BaseResponse;
import com.example.callbuslab.global.security.client.ClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //전체 게시글 조회
    @GetMapping("/api/v1/board")
    public BaseResponse<Page<BoardResponseDto>> findAllBoard(Pageable pageable) {
        Page<BoardResponseDto> allBoardPageable = boardService.findAllBoard(pageable);
        return BaseResponse.of(allBoardPageable);
    }

    //단건 게시글 조회
    @GetMapping("/api/v1/board/{id}")
    public BaseResponse<DetailBoardResponseDto> findBoard(@PathVariable Long id) {
        DetailBoardResponseDto board = boardService.findBoard(id);
        return BaseResponse.of(board);
    }

    //게시글 쓰기
    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE')")
    @PostMapping("/api/v1/board")
    public String generateBoard(@AuthenticationPrincipal ClientAdapter clientAdapter, @RequestBody GenerateBoardRequestDto generateBoardRequest) {
        return boardService.generateBoard(clientAdapter, generateBoardRequest);
    }

    //게시글 수정
    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE')")
    @PutMapping("/api/v1/board")
    public String editBoard(@AuthenticationPrincipal ClientAdapter clientAdapter, @RequestBody BoardEditRequestDto boardEditRequest) {
        return boardService.editBoard(clientAdapter, boardEditRequest);
    }

    //게시글 삭제
    @PreAuthorize("hasAnyRole('LESSOR','REALTOR','LESSEE')")
    @DeleteMapping("/api/v1/board/{id}")
    public String deleteBoard(@AuthenticationPrincipal ClientAdapter clientAdapter, @PathVariable Long id) {
        return boardService.deleteBoard(clientAdapter, id);
    }
}
