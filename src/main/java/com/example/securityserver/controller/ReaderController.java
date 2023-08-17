package com.example.securityserver.controller;

import com.example.securityserver.dto.Response;
import com.example.securityserver.model.Reader;
import com.example.securityserver.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/readers")
public class ReaderController {
    private final ReaderService readerService;
    private final PasswordEncoder passwordEncoder;

    public ReaderController(ReaderService readerService, PasswordEncoder passwordEncoder) {
        this.readerService = readerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<Response> getRoles(Principal principal){
        var reader=readerService.loadUserByUsername(principal.getName());
        var res=new Response(true,
                reader.getUsername(),
                reader.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get());
        return ResponseEntity.ok(res);
    }
    @PostMapping
    public ResponseEntity<Reader> create(@RequestBody Reader reader) {
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.save(reader));
    }

    @PutMapping
    public ResponseEntity<Reader> update(@RequestBody Reader reader) {
        return ResponseEntity.ok(readerService.update(reader,passwordEncoder));
    }
}
