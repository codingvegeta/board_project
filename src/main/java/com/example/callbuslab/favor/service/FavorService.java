package com.example.callbuslab.favor.service;

import com.example.callbuslab.board.entity.Board;
import com.example.callbuslab.board.repository.BoardRepository;
import com.example.callbuslab.favor.dto.FavorClientResponseDto;
import com.example.callbuslab.favor.entity.Favor;
import com.example.callbuslab.favor.repository.FavorRepository;
import com.example.callbuslab.global.security.client.ClientAdapter;
import com.example.callbuslab.member.entity.Client;
import com.example.callbuslab.member.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavorService {
    private final ClientRepository clientRepository;
    private final BoardRepository boardRepository;
    private final FavorRepository favorRepository;

    public String addFavor(ClientAdapter clientAdapter, Long boardId) {
        Client client = clientRepository.findById(clientAdapter.getClient().getId()).orElseThrow(() -> new IllegalStateException("존재하지 않는 사용자 입니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 입니다."));

        Favor findFavor = favorRepository.findByClient_IdAndBoard_Id(client.getId(), boardId);
        if (findFavor != null) {
            return "하나의 게시글에 좋아요는 한번만 누를 수 있습니다.";
        }

        Favor favor = Favor.addFavor(client, board);
        favorRepository.save(favor);
        return "좋아요를 눌렀습니다.";
    }

    public List<FavorClientResponseDto> findClientFavor(String accountId) {
        return favorRepository.findByClient_AccountId(accountId)
                .stream().map(favor -> new FavorClientResponseDto(favor)).collect(Collectors.toList());
    }

    public List<FavorClientResponseDto> findAllFavor() {
        return favorRepository.findAll()
                .stream().map(favor -> new FavorClientResponseDto(favor)).collect(Collectors.toList());
    }
}
