package com.example.securityBoard.favor.repository;

import com.example.securityBoard.favor.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorRepository extends JpaRepository<Favor, Long> {
    Favor findByClient_IdAndBoard_Id(Long clientId, Long boardId);

    List<Favor> findByClient_AccountId(String accountId);
}
