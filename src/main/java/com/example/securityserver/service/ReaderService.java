package com.example.securityserver.service;

import com.example.securityserver.dto.AuthDto;
import com.example.securityserver.dto.RegDto;
import com.example.securityserver.exception.DeletedUserException;
import com.example.securityserver.exception.UserAlreadyExistException;
import com.example.securityserver.model.Reader;
import com.example.securityserver.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReaderService implements UserDetailsService {

    private final ReaderRepository readerRepository;
    public Reader save(Reader reader) {

        if (readerRepository.findByEmail(reader.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(
                    String.format("User with email: '%s' already exist", reader.getEmail()));
        }
        return readerRepository.save(reader);
    }
    public Reader save(RegDto regDto, PasswordEncoder passwordEncoder){
        var password = regDto.getPassword();
        Reader reader = Reader.builder()
                .email(regDto.getEmail())
                .firstname(regDto.getFirstname())
                .lastname(regDto.getLastname())
                .surname(regDto.getSurname())
                .password(passwordEncoder.encode(password))
                .build();
        return save(reader);
    }
    public AuthDto saveAndMap(RegDto regDto, PasswordEncoder passwordEncoder){
        Reader saved = save(regDto, passwordEncoder);
        return new AuthDto(saved.getEmail(),saved.getPassword());
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Reader reader=readerRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                String.format("User with email: '%s' not found", email)));
        if (!reader.getIsActive()){
            throw new DeletedUserException(String.format("user with email %s was deleted", email));
        }

        return new User(email,
                reader.getPassword(),
                Set.of(new SimpleGrantedAuthority(reader.getRole().name())));
    }
}
