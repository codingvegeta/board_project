package com.example.callbuslab.global.security.client;

import com.example.callbuslab.member.entity.Client;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class ClientAdapter extends User {
    private Client client;

    public ClientAdapter(Client client) {
        super(client.getAccountId(), client.getPassword(), Collections.singleton(new SimpleGrantedAuthority(client.getRole().toString())));
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
