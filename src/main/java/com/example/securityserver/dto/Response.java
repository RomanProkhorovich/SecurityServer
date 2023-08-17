package com.example.securityserver.dto;

import com.example.securityserver.service.ReaderService;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;

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
