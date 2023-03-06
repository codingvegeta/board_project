package com.example.securityBoard.member.service;

import com.example.securityBoard.global.security.client.ClientAdapter;
import com.example.securityBoard.member.entity.Client;
import com.example.securityBoard.member.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailService implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByAccountId(username)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 사용자 입니다."));

        return new ClientAdapter(client);
    }
}
