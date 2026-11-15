package com.example.learningapp.infrastructure.persistence.mapper;

import com.example.learningapp.domain.model.user.*;
import com.example.learningapp.infrastructure.persistence.entity.UserJpaEntity;

import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * UserドメインエンティティとUserJpaEntityを相互変換するMapper
 *
 */
@Component
public class UserMapper {

    /**
     * JPAエンティティをドメインエンティティに変換する
     */
    @NotNull
    public User toDomainEntity(UserJpaEntity jpa) {
        return User.reconstruct(new UserId(jpa.getId()), new Username(jpa.getUsername()),
                new Email(jpa.getEmail()), new HashedPassword(jpa.getPasswordHash()),
                jpa.getDisplayName(), jpa.getBio(), jpa.getAvatarUrl(), jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }

    /**
     * ドメインエンティティをJPAエンティティに変換する
     */
    @NotNull
    public UserJpaEntity toJpaEntity(User user) {
        return new UserJpaEntity(user.getId().getValue(), user.getUsername().getValue(),
                user.getEmail().getValue(), user.getPassword().getValue(), user.getDisplayName(),
                user.getBio(), user.getAvatarUrl(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
