package com.example.securityserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }
}
