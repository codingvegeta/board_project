package com.example.securityBoard.member.repository;

import com.example.securityBoard.member.entity.Client;
import com.example.securityBoard.member.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByAccountId(String accountId);

    Optional<Client> findByRoleAndId(Role role, Long clientId);

}
