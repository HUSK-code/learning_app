package com.example.learningapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザーテーブルに対するJPAエンティティ
 * 
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserJpaEntity {
    @Id
    @Column(name = "id", length = 36, nullable =false)
    private String id;

        @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "display_name", length = 100)
    private String displayName;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

        public UserJpaEntity(
            String id,
            String username,
            String email,
            String passwordHash,
            String displayName,
            String bio,
            String avatarUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id           = id;
        this.username     = username;
        this.email        = email;
        this.passwordHash = passwordHash;
        this.displayName  = displayName;
        this.bio          = bio;
        this.avatarUrl    = avatarUrl;
        this.createdAt    = createdAt;
        this.updatedAt    = updatedAt;
    }
}