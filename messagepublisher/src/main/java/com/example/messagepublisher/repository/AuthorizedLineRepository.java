package com.example.messagepublisher.repository;

import com.example.messagepublisher.model.AuthorizedLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedLineRepository extends JpaRepository<AuthorizedLine, Long> {
    
    Optional<AuthorizedLine> findByOrigin(String origin);
    
    boolean existsByOriginAndIsActiveTrue(String origin);
}