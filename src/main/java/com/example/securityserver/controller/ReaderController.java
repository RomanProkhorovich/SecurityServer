package com.example.securityserver.controller;

import com.example.securityserver.dto.Response;
import com.example.securityserver.dto.Token;
import com.example.securityserver.service.ReaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/readers")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<Response> getRoles(Principal principal){
        var reader=readerService.loadUserByUsername(principal.getName());
        var res=new Response(true,reader.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get());
        return ResponseEntity.ok(res);
    }
}
