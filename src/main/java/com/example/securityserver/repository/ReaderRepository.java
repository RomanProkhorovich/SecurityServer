package com.example.securityserver.repository;

import com.example.securityserver.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {
    Optional<Reader> findByEmail(String email);
}
