package com.example.securityserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Response {
    private boolean authenticated;
    private String username;
    private String role;

    public Response(boolean authenticated, String username, String role) {
        this.authenticated = authenticated;
        this.username = username;
        this.role = role;
    }
}
