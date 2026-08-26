package com.example.learningapp.application.dto;

import com.example.learningplatform.domain.model.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * ユーザー情報の出力データ
 *
 */
@Getter
public class UserDto {

    private final String        id;
    private final String        username;
    private final String        email;
    private final String        displayName;
    private final String        bio;
    private final String        avatarUrl;
    private final LocalDateTime createdAt;

    private UserDto(
            String id,
            String username,
            String email,
            String displayName,
            String bio,
            String avatarUrl,
            LocalDateTime createdAt) {
        this.id          = id;
        this.username    = username;
        this.email       = email;
        this.displayName = displayName;
        this.bio         = bio;
        this.avatarUrl   = avatarUrl;
        this.createdAt   = createdAt;
    }

    /**
     * ドメインエンティティからDTOを生成する静的ファクトリメソッド
     *
     */
    public static UserDto from(User user) {
        return new UserDto(
            user.getId().getValue(),
            user.getUsername().getValue(),
            user.getEmail().getValue(),
            user.getDisplayName(),
            user.getBio(),
            user.getAvatarUrl(),
            user.getCreatedAt()
        );
    }
}