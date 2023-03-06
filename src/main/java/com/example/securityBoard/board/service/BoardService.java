package com.example.securityBoard.board.service;

import com.example.securityBoard.board.dto.BoardEditRequestDto;
import com.example.securityBoard.board.dto.BoardResponseDto;
import com.example.securityBoard.board.dto.DetailBoardResponseDto;
import com.example.securityBoard.board.dto.GenerateBoardRequestDto;
import com.example.securityBoard.board.entity.Board;
import com.example.securityBoard.board.entity.enums.Elimination;
import com.example.securityBoard.board.repository.BoardRepository;
import com.example.securityBoard.global.security.client.ClientAdapter;
import com.example.securityBoard.member.entity.Client;
import com.example.securityBoard.member.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public String generateBoard(ClientAdapter clientAdapter, GenerateBoardRequestDto generateBoardRequest) {
        Client client = clientRepository.findById(clientAdapter.getClient().getId()).orElseThrow(() -> new IllegalStateException("없는 사용자 입니다."));
        Board board = Board.createBoard(generateBoardRequest.getContent(), client);
        boardRepository.save(board);
        return "성공적으로 게시 되었습니다.";

    }

    public Page<BoardResponseDto> findAllBoard(Pageable pageable) {
        return boardRepository.findByElimination(pageable, Elimination.Y).map(board -> new BoardResponseDto(board));

    }

    public DetailBoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findByIdAndElimination(id, Elimination.Y).orElseThrow(()-> new IllegalStateException("존재하지 않는 게시글 입니다."));
        return new DetailBoardResponseDto(board);
    }

    @Transactional
    public String editBoard(ClientAdapter clientAdapter, BoardEditRequestDto boardEditRequest) {
        Board board = boardRepository.findById(boardEditRequest.getId()).orElseThrow(() -> new IllegalStateException("게시글이 존재하지 않습니다."));
        if (board.getClient().getId() != clientAdapter.getClient().getId()) {
            throw new IllegalStateException("게시글은 본인 이외에는 수정할 수 없습니다.");
        }
        board.editContent(boardEditRequest.getContent());
        return "성공적으로 수정 되었습니다.";
    }

    @Transactional
    public String deleteBoard(ClientAdapter clientAdapter, Long id) {
        Board board = boardRepository.findByIdAndElimination(id, Elimination.Y).orElseThrow(() -> new IllegalStateException("이미 삭제되었거나 없는 게시글 입니다."));
        if (board.getClient().getId() != clientAdapter.getClient().getId()) {
            throw new IllegalStateException("본인이 작성하지 않은 게시물은 삭제할 수 없습니다.");
        }
        board.deleteBoard();
        return "성공적으로 삭제 되었습니다.";
    }
}
