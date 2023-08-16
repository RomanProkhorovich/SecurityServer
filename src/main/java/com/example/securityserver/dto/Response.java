package com.example.securityserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private boolean authenticated;
    private String role;
}
