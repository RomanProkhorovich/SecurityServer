package com.example.securityserver.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }
}
