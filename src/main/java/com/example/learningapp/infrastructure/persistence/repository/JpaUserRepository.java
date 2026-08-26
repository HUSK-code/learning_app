package com.example.learningapp.infrastructure.persistence.repository;

import com.example.learningapp.domain.model.user.Email;
import com.example.learningapp.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPAのリポジトリインターフェース（ユーザー）
 *
 */
public interface JpaUserRepository extends JpaRepository<UserJpaEntity, String> {

    // findByUsername → "SELECT * FROM users WHERE username = ?"
    Optional<UserJpaEntity> findByUsername(String username);

    // findByEmail → "SELECT * FROM users WHERE email = ?"
    Optional<UserJpaEntity> findByEmail(String email);

    // existsByUsername → "SELECT COUNT(*) > 0 FROM users WHERE username = ?"
    boolean existsByUsername(String username);

    // existsByEmail → "SELECT COUNT(*) > 0 FROM users WHERE email = ?"
    boolean existsByEmail(Email email);
}
