package com.example.callbuslab.board.repository;

import com.example.callbuslab.board.entity.Board;
import com.example.callbuslab.board.entity.enums.Elimination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByElimination(Pageable pageable, Elimination elimination);

    Optional<Board> findByIdAndElimination(Long id, Elimination elimination);

}
