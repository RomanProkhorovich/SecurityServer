package com.example.securityserver.controller;

import com.example.securityserver.dto.AuthDto;
import com.example.securityserver.dto.RegDto;
import com.example.securityserver.dto.Token;
import com.example.securityserver.service.ReaderService;
import com.example.securityserver.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ReaderService readerService;
    private final AuthUtil authUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(ReaderService readerService, AuthUtil authUtil, BCryptPasswordEncoder passwordEncoder) {
        this.readerService = readerService;
        this.authUtil = authUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
            public ResponseEntity<String> generateToken(@RequestBody AuthDto dto) {
        Token body = new Token(authUtil.auth(dto.getUsername(), dto.getPassword()));
        return ResponseEntity.ok(body.getToken());
    }


    @PostMapping("/registration")
    public ResponseEntity<AuthDto> doRegistration(@RequestBody RegDto regDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.saveAndMap(regDto,passwordEncoder));
    }



}
